
package view;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.Department;
import model.StaffType;

public class ProfilePanel extends JPanel {
    
    private JTextField nameField;
    private ImageIcon profileImage;
    private JLabel profileLabel;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private ButtonGroup genderGroup;
    private JPanel genderPanel;
    private JDateChooser dobChooser;
    private JTextArea addressField;
    private JTextField contactField;
    private JTextField mailField;
    private JTextArea qualificationsField;
    private JTextField idField;
    private JComboBox typeCombo;
    private JComboBox deptCombo;
    private JButton dummyButton;
    private JPopupMenu profileMenu;
    private JFileChooser fileChooser;
    
    public ProfilePanel() {
        nameField = new JTextField(15);
        profileImage = new ImageIcon(getClass().getResource("/image/empty-profile.png"));
        profileLabel = new JLabel();
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderPanel = new JPanel();
        dobChooser = new JDateChooser();
        addressField = new JTextArea(5, 15);
        contactField = new JTextField(15);
        mailField = new JTextField(15);
        qualificationsField = new JTextArea(3, 15);
        idField = new JTextField(15);
        typeCombo = new JComboBox();
        deptCombo = new JComboBox();
        profileMenu = createProfileMenu();
        fileChooser = new JFileChooser();
        
        dummyButton = new JButton("Dummy!");
        
        addressField.setFont(nameField.getFont());
        qualificationsField.setFont(nameField.getFont());
        
        
        profileImage = resizeImage(profileImage);
        profileLabel.setBorder(BorderFactory.createEtchedBorder());
        profileLabel.setIcon(profileImage);
        profileLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton() == MouseEvent.BUTTON3) {
                    profileMenu.show(profileLabel, e.getX(), e.getY());
                }
            }
        });
       
        
        
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        
        DefaultComboBoxModel typeModel = new DefaultComboBoxModel();
        for(StaffType type : StaffType.values()) {
            typeModel.addElement(type);
        }
        typeCombo.setModel(typeModel);
        
        DefaultComboBoxModel deptModel = new DefaultComboBoxModel();
        for(Department dept : Department.values()) {
            deptModel.addElement(dept);
        }
        deptCombo.setModel(deptModel);
        
        setLayout(new BorderLayout());
        layoutComponents();
    }
    private void resetConstraints(GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1;
        gbc.weighty = 1;
    }
    
    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Insets labelInsets = new Insets(0, 0, 0, 5);
        Insets noInsets = new Insets(0, 0, 0, 0);
        
        //Name Row
        gbc.gridy = 0;
        gbc.weighty = 0.25;
        
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.insets = labelInsets;
        add(new JLabel("Name: "),gbc);
        
        gbc.gridx++;
        gbc.weightx = 0.25;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets = noInsets;
        add(nameField,gbc);
        
        gbc.gridx++;
        gbc.gridheight = 4;
        gbc.weightx = 0.5;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(profileLabel,gbc);
        
        //Gender Row
        gbc.gridheight = 1;
        gbc.weighty = 0.05;
        
        gbc.gridy++;
        
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = labelInsets;
        add(new JLabel("Gender: "),gbc);
        
        gbc.gridx++;
        gbc.weightx = 0.25;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = noInsets;
        genderPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        add(genderPanel,gbc);
        
        //DOB Row
        gbc.gridy++;
        
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = labelInsets;
        add(new JLabel("Date of Birth: "),gbc);
        
        gbc.gridx++;
        gbc.weightx = 0.25;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = noInsets;
        add(dobChooser,gbc);
        
        
        
        //Address Row
        gbc.gridy++;
        
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = labelInsets;
        add(new JLabel("Adress: "),gbc);
        
        gbc.gridx++;
        gbc.weightx = 0.25;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = noInsets;
        add(new JScrollPane(addressField),gbc);
        
        //Contact Row
        gbc.gridheight = 1;
        gbc.gridy++;
        
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = labelInsets;
        add(new JLabel("Contact: "),gbc);
        
        gbc.gridx++;
        gbc.weightx = 0.25;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = noInsets;
        add(contactField,gbc);
        
        //Mail Row
        gbc.gridy++;
        
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = labelInsets;
        add(new JLabel("E-mail: "),gbc);
        
        gbc.gridx++;
        gbc.weightx = 0.25;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = noInsets;
        add(mailField,gbc);
        
        //Qualification Row
        gbc.gridy++;
        
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = labelInsets;
        add(new JLabel("Qualifications: "),gbc);
        
        gbc.gridx++;
        gbc.weightx = 0.25;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = noInsets;
        add(new JScrollPane(qualificationsField),gbc);
        
        //ID Row
        gbc.gridy++;
        
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = labelInsets;
        add(new JLabel("Staff ID: "),gbc);
        
        gbc.gridx++;
        gbc.weightx = 0.25;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = noInsets;
        add(idField,gbc);
        
        //Type Row
        gbc.gridy++;
        
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = labelInsets;
        add(new JLabel("Staff Type: "),gbc);
        
        gbc.gridx++;
        gbc.weightx = 0.25;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = noInsets;
        add(typeCombo,gbc);
        
        //Department Row
        gbc.gridy++;
        
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = labelInsets;
        add(new JLabel("Department: "),gbc);
        
        gbc.gridx++;
        gbc.weightx = 0.25;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = noInsets;
        add(deptCombo,gbc);
        
        //Last Row
        gbc.gridy++;
        gbc.weighty = 1;
        
        gbc.gridx = 1;
        gbc.weightx = 0.25;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = noInsets;
        add(dummyButton,gbc);
        
    }

    private JPopupMenu createProfileMenu() {
        JPopupMenu menu = new JPopupMenu();
        
        JMenuItem removePictureItem = new JMenuItem("Remove Picture");
        JMenuItem changePictureItem = new JMenuItem("Change Picture");
        menu.add(changePictureItem);
        menu.add(removePictureItem);
        
        removePictureItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                profileLabel.setIcon(profileImage);
            }
        });
        
        changePictureItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(fileChooser.showOpenDialog(ProfilePanel.this) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    ImageIcon image = new ImageIcon(file.getAbsolutePath());
                    profileLabel.setIcon(resizeImage(image));
                }
            }
        });
        return menu;
    }

    private ImageIcon resizeImage(ImageIcon profileImage) {
        Image tmpImage = profileImage.getImage();
        Image scaledImage = tmpImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
