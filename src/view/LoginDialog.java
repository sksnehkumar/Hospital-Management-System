
package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginDialog extends JDialog {
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
    private JButton cancelButton;
    private JPanel detailsPanel;
    private JPanel buttonsPanel;
    
    public LoginDialog(JFrame parent) {
        super(parent, "Login", true);
        
        userField = new JTextField(12);
        passField = new JPasswordField(12);
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");
        detailsPanel = new JPanel();
        buttonsPanel = new JPanel();
        
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
            
        });
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
            
        });
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        setSize(250, 180);
        setResizable(false);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        setLayout(new BorderLayout());
        layoutComponents();
        add(detailsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
        
    }
    
    private void layoutComponents() {
        
        //detailsPanel
        detailsPanel.setLayout(new GridBagLayout());
        GridBagConstraints pc = new GridBagConstraints();
        
        //First Row
        pc.gridy = 0;
        pc.weightx = 1;
        pc.weighty = 1;
        
        pc.gridx = 0;
        pc.anchor = GridBagConstraints.LAST_LINE_END;
        pc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(new JLabel("User: "),pc);
        
        pc.gridx++;
        pc.anchor = GridBagConstraints.LAST_LINE_START;
        detailsPanel.add(userField, pc);
        
        //Second Row
        pc.gridy++;
        
        pc.gridx = 0;
        pc.anchor = GridBagConstraints.LINE_END;
        pc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(new JLabel("Password: "), pc);
        
        pc.gridx++;
        pc.anchor = GridBagConstraints.LINE_START;
        detailsPanel.add(passField, pc);
        
        //buttonsPanel
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(loginButton, pc);
        buttonsPanel.add(cancelButton, pc);
    }
    
}
