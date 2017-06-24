
package view.patient;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.Patient;

public class PatientTablePanel extends JPanel{
    private final JTable patientTable;
    private PatientTableModel patientModel;
    private JPopupMenu popup;
    public PatientTablePanel() {
        patientModel = new PatientTableModel();
        patientTable = new JTable(patientModel);
        popup = new JPopupMenu();
        
        JMenuItem viewItem = new JMenuItem("View Details");
        JMenuItem deleteItem = new JMenuItem("Delete Patient");
        popup.add(viewItem);
        popup.add(deleteItem);
        
        patientTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); 
                int row = patientTable.rowAtPoint(e.getPoint());
                patientTable.getSelectionModel().setSelectionInterval(row, row);
                
                if(e.getButton() == MouseEvent.BUTTON3) {
                    popup.show(patientTable, e.getX(), e.getY());
                }
            }
            
        });
        
        setLayout(new BorderLayout());
        add(new JScrollPane(patientTable), BorderLayout.CENTER);
    }

    public void refresh() {
        patientModel.fireTableDataChanged();
    }
    
    public void setData(List<Patient> records) {
        patientModel.setRecords(records);
    }
}
