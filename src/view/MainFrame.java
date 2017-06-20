
package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class MainFrame extends JFrame {
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
        
        loginDialog = new LoginDialog(this);
        bgPanel = new BackgroundPanel();
        profilePanel = new ProfilePanel();
        
        
        
        setLayout(new BorderLayout());
        add(profilePanel, BorderLayout.CENTER);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        
        loginDialog.setVisible(true);
    }
}
