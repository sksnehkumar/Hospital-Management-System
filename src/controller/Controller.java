
package controller;

import java.util.List;
import model.Database;
import model.Patient;
import model.Profile;

public class Controller {
    private Database master = new Database();
    private String user;
    private String pass;
    private int port;

    public Controller(String user, String pass, int port) {
        this.user = user;
        this.pass = pass;
        this.port = port;
        
        connect(user, pass, port);
    }
    
    
    
    public void connect(String user, String pass, int port) {
        master.connect(user, pass, port);
    }
    
    public void disconnect() {
        master.disconnect();
    }

    public boolean validateUser(String username, String password) {
        return master.validateUser(username, password);
    }
    
    public void saveProfile(Profile profile) {
        master.saveProfile(profile);
    }

    public Profile loadProfile(String username) {
        return master.loadProfile(username);
    }
    
    public void savePatient(Patient patient) {
        master.savePatient(patient);
    }

    public List<Patient> loadPatientRecords() {
        return master.loadPatientRecords();
    }
    
    public int getNextPatNo() {
        return master.getNextPatNo();
    }
}
