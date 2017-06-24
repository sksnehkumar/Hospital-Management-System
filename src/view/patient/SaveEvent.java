
package view.patient;

import java.util.EventObject;
import model.Patient;

public class SaveEvent extends EventObject {
    
    private Patient patient;

    public SaveEvent(Object source, Patient patient) {
        super(source);
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }
    
}
