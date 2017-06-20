
package controller;

import model.Database;

public class Controller {
    private Database master = new Database();
    
    public void connect(String user, String pass, int port) {
        master.connect(user, pass, port);
    }
    
    public void disconnect() {
        master.disconnect();
    }

    public boolean validateUser(String username, String password) {
        return master.validateUser(username, password);
    }
}
