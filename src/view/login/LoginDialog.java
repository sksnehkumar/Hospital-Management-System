
package view.login;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.apache.commons.codec.digest.DigestUtils;

public class LoginDialog extends JDialog {
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
    private JButton cancelButton;
    private JPanel detailsPanel;
    private JPanel buttonsPanel;
    private LoginListener listener;
    
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
                String username = userField.getText();
                String password = DigestUtils.sha256Hex(new String(passField.getPassword()));
                
                LoginEvent ev = new LoginEvent(this, username, password);
                if(listener != null) {
                    listener.loginPerformed(ev);
                }
            }
            
        });
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.dispose();
            }
            
        });
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                parent.dispose();
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
        GridBagConstraints gbc = new GridBagConstraints();
        
        //First Row
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(new JLabel("User: "),gbc);
        
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        detailsPanel.add(userField, gbc);
        
        //Second Row
        gbc.gridy++;
        
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, 0, 0, 5);
        detailsPanel.add(new JLabel("Password: "), gbc);
        
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.LINE_START;
        detailsPanel.add(passField, gbc);
        
        //buttonsPanel
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(loginButton, gbc);
        buttonsPanel.add(cancelButton, gbc);
    }

    public void setListener(LoginListener listener) {
        this.listener = listener;
    }
    
}
