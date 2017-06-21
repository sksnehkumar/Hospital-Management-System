
package view.patient;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import model.BloodGroup;
import model.Company;

public class patientRegPanel extends JPanel {
    
    private JLabel idLabel;
    private JTextField idField;
    private JLabel nameLabel;
    private JTextField nameField;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private ButtonGroup genderGroup;
    private JSpinner ageSpinner;
    private JRadioButton unmarriedRadio;
    private JRadioButton marriedRadio;
    private JRadioButton othersRadio;
    private ButtonGroup statusGroup;
    private JComboBox bloodCombo;
    private JTextArea identifiersArea;
    private JTextArea addressArea;
    private JTextField contactField;
    private JTextField mailField;
    private JCheckBox insuranceCheck;
    private JComboBox compCombo;
    private JTextField insNoField;
    private final JLabel genderLabel;
    private final JLabel ageLabel;
    private final JLabel statusLabel;
    private final JLabel bloodLabel;
    private final JLabel identifiersLabel;
    private final JLabel addressLabel;
    private final JLabel contactLabel;
    private final JLabel mailLabel;
    private final JLabel insuranceLabel;
    private final JLabel compLabel;
    private final JLabel insNoLabel;
    private final JScrollPane identifiersScroll;
    private final JScrollPane addressScroll;
    private final JLabel attachmentLabel;
    private final JPanel attachmentPanel;

    public patientRegPanel() {
        
        idLabel = new JLabel("Patient ID: ");
        idField = new JTextField(15);
        nameLabel = new JLabel("Name: ");
        nameField = new JTextField(15);
        genderLabel = new JLabel("Gender: ");
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        ageLabel = new JLabel("Age: ");
        ageSpinner = new JSpinner();
        statusLabel = new JLabel("Marital Status: ");
        unmarriedRadio = new JRadioButton("Unmarried");
        marriedRadio = new JRadioButton("Married");
        othersRadio = new JRadioButton("Others");
        statusGroup = new ButtonGroup();
        bloodLabel = new JLabel("Blood Group: ");
        bloodCombo = new JComboBox();
        identifiersLabel = new JLabel("Identifiers: ");
        identifiersArea = new JTextArea(3, 15);
        identifiersScroll = new JScrollPane(identifiersArea);
        addressLabel = new JLabel("Address: ");
        addressArea = new JTextArea(5, 15);
        addressScroll = new JScrollPane(addressArea);
        contactLabel = new JLabel("Contact:");
        contactField = new JTextField(15);
        mailLabel = new JLabel("E-mail: ");
        mailField = new JTextField(15);
        insuranceLabel = new JLabel("Insurance Status: ");
        insuranceCheck = new JCheckBox("Insured");
        compLabel = new JLabel("Insurance Company: ");
        compCombo = new JComboBox();
        insNoLabel = new JLabel("Insurance Number: ");
        insNoField = new JTextField(15);
        attachmentPanel = new JPanel();
        attachmentLabel = new JLabel("Attachments: ");
        
        
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        
        statusGroup.add(marriedRadio);
        statusGroup.add(unmarriedRadio);
        statusGroup.add(othersRadio);
        
        identifiersArea.setFont(idField.getFont());
        addressArea.setFont(idField.getFont());
        
        DefaultComboBoxModel bloodModel = new DefaultComboBoxModel();
        for(BloodGroup bg : BloodGroup.values()) {
            bloodModel.addElement(bg);
        }
        bloodCombo.setModel(bloodModel);
        
        DefaultComboBoxModel compModel = new DefaultComboBoxModel();
        for(Company comp : Company.values()) {
            compModel.addElement(comp);
        }
        compCombo.setModel(compModel);
        
        SpinnerModel ageModel = ageSpinner.getModel();
        ageModel.setValue(25);
        
        insuranceCheck.setSelected(true);
        
        
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(idLabel)
                        .addComponent(nameLabel)
                        .addComponent(genderLabel)
                        .addComponent(ageLabel)
                        .addComponent(statusLabel)
                        .addComponent(bloodLabel)
                        .addComponent(identifiersLabel)
                        .addComponent(addressLabel)
                        .addComponent(contactLabel)
                        .addComponent(mailLabel)
                        .addComponent(insuranceLabel)
                        .addComponent(compLabel)
                        .addComponent(insNoLabel)
                    )
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)         
                        .addComponent(idField)
                        .addComponent(nameField)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(maleRadio)
                            .addComponent(femaleRadio))
                        .addComponent(ageSpinner)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(marriedRadio)
                            .addComponent(unmarriedRadio)
                            .addComponent(othersRadio))
                        .addComponent(bloodCombo)
                        .addComponent(identifiersScroll)
                        .addComponent(addressScroll)
                        .addComponent(contactField)
                        .addComponent(mailField)
                        .addComponent(insuranceCheck)
                        .addComponent(compCombo)
                        .addComponent(insNoField)
                    )
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING) 
                        .addComponent(attachmentPanel)
                    )
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                    
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(idLabel)
                        .addComponent(idField)
                    )
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(nameField))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(genderLabel)
                        .addComponent(maleRadio)
                        .addComponent(femaleRadio))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(ageLabel)
                        .addComponent(ageSpinner))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(statusLabel)
                        .addComponent(marriedRadio)
                        .addComponent(unmarriedRadio)
                        .addComponent(othersRadio))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(bloodLabel)
                        .addComponent(bloodCombo))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(identifiersLabel)
                        .addComponent(identifiersScroll))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(addressLabel)
                        .addComponent(addressScroll))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(contactLabel)
                        .addComponent(contactField))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(mailLabel)
                        .addComponent(mailField))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(insuranceLabel)
                        .addComponent(insuranceCheck))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(compLabel)
                        .addComponent(compCombo))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(insNoLabel)
                        .addComponent(insNoField))
                    
        );
        
        
        
        insuranceCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isInsured = insuranceCheck.isSelected();
                compLabel.setEnabled(isInsured);
                compCombo.setEnabled(isInsured);
                insNoLabel.setEnabled(isInsured);
                insNoField.setEnabled(isInsured);
            }
            
        });
    }
    
    

    
    
}
