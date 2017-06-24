
package view;

import view.login.LoginDialog;
import controller.Controller;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import model.Patient;
import view.login.LoginEvent;
import view.login.LoginListener;
import view.patient.PatientListener;
import view.patient.PatientTablePanel;
import view.patient.PatientRegistrationPanel;
import view.patient.SaveEvent;

public class MainFrame extends JFrame {
    
    private ImageIcon image;
    private JLabel imageLabel;
    
    private Controller controller;
    private LoginDialog loginDialog;
    private PatientRegistrationPanel patientPanel;
    private PatientTablePanel patientRecordsPanel;
    
    public MainFrame() {
        super("Hospital Management System");
        
        //Set Look and Feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
            }
        }
        } catch (Exception e) {
            System.out.println("Can't set LookAndFeel.");
        }
        
        //Initialize Components
        image = new ImageIcon(getClass().getResource("/image/hospital.jpg"));
        imageLabel = new JLabel(image);
        controller = new Controller("root", "5111", 3306);
        loginDialog = new LoginDialog(this);
        patientPanel = new PatientRegistrationPanel();
        patientRecordsPanel = new PatientTablePanel();
        
        patientRecordsPanel.setData(controller.loadPatientRecords());
        
        //Reset Windpw
        resetWindow();
        
        //Login Action
        loginDialog.setListener(new LoginListener() {
            public void loginPerformed(LoginEvent e) {
                if(controller.validateUser(e.getUsername(), e.getPassword()) == false) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Incorrect Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    loginDialog.dispose();
                    
                }
            }
            
        });
        
        patientPanel.setListener(new PatientListener() {
            public void patientSaved(SaveEvent e) {
                Patient patient = e.getPatient();
                controller.savePatient(patient);
                controller.getNextPatNo();
                
            }
            
        });
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                controller.disconnect();
                dispose();
            }
            
        });
        
        
        
        //Set Frame Properties
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        
        //Set Layout
        setContentPane(imageLabel);
        setLayout(new BorderLayout());
        add(patientPanel, BorderLayout.CENTER);
        //add(patientRecordsPanel, BorderLayout.CENTER);
        
        //Set up Menu Bar
        setJMenuBar(createMainMenu());
        
        
        //Show Login Dialog at Startup
        setVisible(true);
        loginDialog.setVisible(true);
        
    }
    
    private JMenuBar createMainMenu() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu patientMenu = new JMenu("Patient");
        JMenuItem addPatientItem = new JMenuItem("Add New Patient");
        JMenuItem viewPatientsItem = new JMenuItem("View All Patients");
        patientMenu.add(addPatientItem);
        patientMenu.add(viewPatientsItem);
        menuBar.add(patientMenu);
        
        addPatientItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetWindow();
                patientPanel.setNextPatNo(controller.getNextPatNo());
                patientPanel.setVisible(true, true);
            }
        });
        
        viewPatientsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetWindow();
                patientRecordsPanel.setVisible(true);
            }
        });
                
        return menuBar;
    }

    private void resetWindow() {
        patientPanel.setVisible(false);
        patientRecordsPanel.setVisible(false);
    }
}
