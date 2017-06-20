
package view;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
    
    private ImageIcon image;
    private JLabel imageLabel;
    
    public BackgroundPanel() {
        image = new ImageIcon(getClass().getResource("/image/hospital.jpg"));
        imageLabel = new JLabel(image);
        
        setLayout(new BorderLayout());
        add(imageLabel, BorderLayout.CENTER);
    }
}
