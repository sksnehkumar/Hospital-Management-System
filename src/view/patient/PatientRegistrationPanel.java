
package view.patient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import model.BloodGroup;
import model.Company;
import model.Patient;

public class PatientRegistrationPanel extends JPanel {
    
    private PatientListener listener;
    private JLabel patNoLabel;
    private JTextField patNoField;
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
    private final JButton saveButton;
    private final JButton cancelButton;
    private final SpinnerNumberModel ageModel;
    

    public PatientRegistrationPanel() {
        patNoLabel = new JLabel("Patient No: ");
        patNoField = new JTextField(15);
        nameLabel = new JLabel("Name: ");
        nameField = new JTextField(15);
        genderLabel = new JLabel("Gender: ");
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        ageLabel = new JLabel("Age: ");
        ageModel = new SpinnerNumberModel(25, 1, 120, 1);
        ageSpinner = new JSpinner(ageModel);
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
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        
        
        
        //Set up genderGroup
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        maleRadio.setSelected(true);
        maleRadio.setActionCommand("Male");
        femaleRadio.setActionCommand("Female");
        
        //Set up statusGroup
        statusGroup.add(marriedRadio);
        statusGroup.add(unmarriedRadio);
        statusGroup.add(othersRadio);
        marriedRadio.setSelected(true);
        marriedRadio.setActionCommand("Married");
        unmarriedRadio.setActionCommand("Unmarried");
        othersRadio.setActionCommand("Others");
        
        //Set font for JTextArea controls
        identifiersArea.setFont(patNoField.getFont());
        addressArea.setFont(patNoField.getFont());
        
        //Set up bloodCombo
        DefaultComboBoxModel bloodModel = new DefaultComboBoxModel();
        for(BloodGroup bg : BloodGroup.values()) {
            bloodModel.addElement(bg);
        }
        bloodCombo.setModel(bloodModel);
        
        
        //Set up compCombo
        DefaultComboBoxModel compModel = new DefaultComboBoxModel();
        for(Company comp : Company.values()) {
            compModel.addElement(comp);
        }
        compCombo.setModel(compModel);
        
        //Set up insuranceCheck
        insuranceCheck.setSelected(true);
        
        insuranceCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isInsured = insuranceCheck.isSelected();
                compLabel.setEnabled(isInsured);
                compCombo.setEnabled(isInsured);
                insNoLabel.setEnabled(isInsured);
                insNoField.setEnabled(isInsured);
            }
        });
        
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int patNo = Integer.parseInt(patNoField.getText());
                String name = nameField.getText();
                String gender = genderGroup.getSelection().getActionCommand();
                int age = (Integer)ageSpinner.getValue();
                String status = statusGroup.getSelection().getActionCommand();
                String group = bloodCombo.getSelectedItem().toString();
                String identifiers = identifiersArea.getText();
                String address = addressArea.getText();
                String contact = contactField.getText();
                String mail = mailField.getText();
                boolean isInsured = insuranceCheck.isSelected();
                String comp = null;
                String insNo = null;
                if(isInsured) {
                    comp = compCombo.getSelectedItem().toString();
                    insNo = insNoField.getText();
                }
                
                Patient newPatient = new Patient(patNo, name, gender, age, status, group, identifiers, address, contact, mail, 
                        isInsured, comp, insNo, new Date(System.currentTimeMillis()));
                
                if(listener != null) {
                    SaveEvent ev = new SaveEvent(this, newPatient);
                    listener.patientSaved(ev);
                }
                clearForm();
            }
            
        });
        
        cancelButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
             
        });
         
        
        layoutComponents();
    }
    
    private void clearForm() {
        
        nameField.setText("");
        maleRadio.setSelected(true);
        ageSpinner.setValue(25);
        marriedRadio.setSelected(true);
        bloodCombo.setSelectedIndex(0);
        identifiersArea.setText("");
        addressArea.setText("");
        contactField.setText("");
        mailField.setText("");
        insuranceCheck.setSelected(true);
        compLabel.setEnabled(true);
        compCombo.setEnabled(true);
        compCombo.setSelectedIndex(0);
        insNoLabel.setEnabled(true);
        insNoField.setEnabled(true);
        insNoField.setText("");
    }

    private void layoutComponents() {
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(patNoLabel)
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
                        .addComponent(insNoLabel))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)         
                        .addComponent(patNoField)
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
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(insNoField)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(saveButton)
                                .addComponent(cancelButton)))
                        )
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(patNoLabel)
                        .addComponent(patNoField))
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
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(saveButton)
                        .addComponent(cancelButton))
                    
        );
    }

    public void setListener(PatientListener listener) {
        this.listener = listener;
    }
    
    public void setVisible(boolean b, boolean editable) {
        super.setVisible(b);
        if(editable) {
            patNoLabel.setEnabled(false);
            patNoField.setEnabled(false);
            nameField.setEnabled(true);
            maleRadio.setEnabled(true);
            femaleRadio.setEnabled(true);
            ageSpinner.setEnabled(true);
            marriedRadio.setEnabled(true);
            unmarriedRadio.setEnabled(true);
            othersRadio.setEnabled(true);
            bloodCombo.setEnabled(true);
            identifiersArea.setEnabled(true);
            addressArea.setEnabled(true);
            contactField.setEnabled(true);
            mailField.setEnabled(true);
            insuranceCheck.setEnabled(true);
            compCombo.setEnabled(true);
            insNoField.setEnabled(true);
            nameLabel.setEnabled(true);
            genderLabel.setEnabled(true);
            ageLabel.setEnabled(true);
            statusLabel.setEnabled(true);
            bloodLabel.setEnabled(true);
            identifiersLabel.setEnabled(true);
            addressLabel.setEnabled(true);
            contactLabel.setEnabled(true);
            mailLabel.setEnabled(true);
            insuranceLabel.setEnabled(true);
            compLabel.setEnabled(true);
            insNoLabel.setEnabled(true);
        }
        else
        {
            
            patNoLabel.setEnabled(false);
            patNoField.setEnabled(false);
            nameField.setEnabled(false);
            maleRadio.setEnabled(false);
            femaleRadio.setEnabled(false);
            ageSpinner.setEnabled(false);
            marriedRadio.setEnabled(true);
            unmarriedRadio.setEnabled(true);
            othersRadio.setEnabled(true);
            bloodCombo.setEnabled(false);
            identifiersArea.setEnabled(true);
            addressArea.setEnabled(true);
            contactField.setEnabled(true);
            mailField.setEnabled(true);
            insuranceCheck.setEnabled(true);
            compCombo.setEnabled(true);
            insNoField.setEnabled(true);
            nameLabel.setEnabled(false);
            genderLabel.setEnabled(false);
            ageLabel.setEnabled(false);
            statusLabel.setEnabled(true);
            bloodLabel.setEnabled(false);
            identifiersLabel.setEnabled(false);
            addressLabel.setEnabled(true);
            contactLabel.setEnabled(true);
            mailLabel.setEnabled(true);
            insuranceLabel.setEnabled(true);
            compLabel.setEnabled(true);
            insNoLabel.setEnabled(true);
        }
    }

    public void setNextPatNo(int nextPatNo) {
        patNoField.setText("" + nextPatNo);
    }
    
    
}
