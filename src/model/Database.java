
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private Connection conn;
    
    public void connect(String user, String pass, int port) {
        if(conn != null)
            return;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found.");
        }
        
        String connectionURL = "jdbc:mysql://localhost:" + port + "/hms";
        try {
            conn = DriverManager.getConnection(connectionURL, user, pass);
        } catch (SQLException ex) {
            System.out.println("Connection Failed.");
            return;
        }
        System.out.println("Connection Established: " + conn);
    }
    
    public void disconnect() {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Disconnection Failed");
            }
        }
    }

    public boolean validateUser(String username, String password) {
        try {
            String query = "Select count(*) as count from users where username = ? and password = ?";
            PreparedStatement validateStmt = conn.prepareStatement(query);
            validateStmt.setString(1, username);
            validateStmt.setString(2, password);
            
            ResultSet result = validateStmt.executeQuery();
            
            if(result.next()) {
                if(result.getInt("count") == 1)
                    return true;
            }
            return false;
            
        } catch (SQLException ex) {
            System.out.println("User Validation Failed.");
        }
        return false;
    }
}
