
package view;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class MainFrame extends JFrame {
    private LoginDialog loginDialog;
    private ImageIcon image;
    private JLabel imageLabel;
    
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
        image = new ImageIcon(getClass().getResource("/image/hospital.jpg"));
        imageLabel = new JLabel(image);
        
        
        
        
        setLayout(new BorderLayout());
        add(imageLabel, BorderLayout.CENTER);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        
        loginDialog.setVisible(true);
    }
}
