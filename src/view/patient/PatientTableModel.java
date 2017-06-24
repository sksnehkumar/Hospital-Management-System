
package view.patient;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Patient;

class PatientTableModel extends AbstractTableModel {

    private String[] columns = {"Patient No", "Patient Name", "Gender", "Age", "Contact", "Date of Registration"};
    private List<Patient> records;

    public void setRecords(List<Patient> records) {
        this.records = records;
    }
    
    public int getRowCount() {
        return records.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    public String getColumnName(int column) {
        return columns[column];
    }
    
    

    public Object getValueAt(int rowIndex, int columnIndex) {
        Patient patient = records.get(rowIndex);
        
        switch(columnIndex) {
            case 0:
                return patient.getPatNo();
            case 1:
                return patient.getName();
            case 2:
                return patient.getGender();
            case 3:
                return patient.getAge();
            case 4:
                return patient.getContact();
            case 5:
                return patient.getRegDate();
        }
        return null;
    }
    
}
