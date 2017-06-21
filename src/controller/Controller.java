
package controller;

import model.Database;
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
}
