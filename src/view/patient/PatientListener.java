
package view.patient;

import java.util.EventListener;

public interface PatientListener extends EventListener{
    public void patientSaved(SaveEvent e);
}
