
package view;

import view.login.LoginDialog;
import controller.Controller;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import view.login.LoginEvent;
import view.login.LoginListener;

public class MainFrame extends JFrame {
    
    private Controller controller;
    private LoginDialog loginDialog;
    private BackgroundPanel bgPanel;
    private ProfilePanel profilePanel;
    
    
    public MainFrame() {
        super("Hospital Management System");
        
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
        
        controller = new Controller();
        
        loginDialog = new LoginDialog(this);
        bgPanel = new BackgroundPanel();
        profilePanel = new ProfilePanel();
        
        
        
        controller.connect("root", "5111", 3306);
        
        setLayout(new BorderLayout());
        add(profilePanel, BorderLayout.CENTER);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        
        
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
        loginDialog.setVisible(true);
        
    }
}
