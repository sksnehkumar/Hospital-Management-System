
package view;

import view.login.LoginDialog;
import controller.Controller;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import view.login.LoginEvent;
import view.login.LoginListener;
import view.patient.patientRegPanel;

public class MainFrame extends JFrame {
    
    private Controller controller;
    private LoginDialog loginDialog;
    private BackgroundPanel bgPanel;
    private patientRegPanel patientPanel;
    
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
        controller = new Controller("root", "5111", 3306);
        loginDialog = new LoginDialog(this);
        bgPanel = new BackgroundPanel();
        patientPanel = new patientRegPanel();
        
        
        
        //Login Action
        loginDialog.setListener(new LoginListener() {
            public void loginPerformed(LoginEvent e) {
                if(controller.validateUser(e.getUsername(), e.getPassword()) == false) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Incorrect Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    loginDialog.dispose();
                    setVisible(true);
                }
                    
            }
            
        });
        
        //Set Frame Properties
        setLayout(new BorderLayout());
        add(patientPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        //setSize(800, 600);
        //setResizable(false);
        setLocationRelativeTo(null);
        
        //Show Login Dialog at Startup
        //loginDialog.setVisible(true);
        setVisible(true);
    }
}
