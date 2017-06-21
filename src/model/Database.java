
package model;

import java.sql.Connection;
import java.sql.Date;
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
                if(result.getInt("count") == 1) {
                    validateStmt.close();
                    return true;
                }
                    
            }
            validateStmt.close();
            return false;
            
        } catch (SQLException ex) {
            System.out.println("User Validation Failed.");
        }
        return false;
    }
    
    public void saveProfile(Profile profile) {
        try {
            String save = "insert into profiles values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement saveStmt = conn.prepareStatement(save);
            saveStmt.setString(1, profile.getId());
            saveStmt.setString(2, "" + profile.getType());
            saveStmt.setString(3, "" + profile.getDept());
            saveStmt.setString(4, profile.getName());
            saveStmt.setString(5, profile.getGender());
            saveStmt.setDate(6, profile.getDob());
            saveStmt.setString(7, profile.getAddress());
            saveStmt.setString(8, profile.getContact());
            saveStmt.setString(9, profile.getMail());
            saveStmt.setString(10, profile.getQualifications());
            saveStmt.setString(11, profile.getProfileImage());
            
            saveStmt.executeUpdate();
            saveStmt.close();
        } catch (SQLException ex) {
            System.out.println("Profile Save Failed.");
        }
        
        
    }

    public Profile loadProfile(String username) {
        Profile profile = new Profile("", "", "", new Date(System.currentTimeMillis()), "", "", "", "", "", StaffType.Medical, Department.Administraion);
        try {
            String load = "Select * from profiles where staff_id = ?";
            PreparedStatement loadStmt = conn.prepareStatement(load);
            loadStmt.setString(1, username);
            
            ResultSet rs = loadStmt.executeQuery();
            if(rs.next()) {
                String name = rs.getString("name");
                String profileImage = rs.getString("image");
                String gender = rs.getString("gender");
                Date dob = rs.getDate("dob");
                String address = rs.getString("address");
                String contact = rs.getString("contact");
                String mail = rs.getString("mail");
                String qualifications = rs.getString("qual");
                String id = rs.getString("staff_id");
                StaffType type = StaffType.valueOf(rs.getString("staff_type"));
                Department dept = Department.valueOf(rs.getString("dept"));
                
                profile = new Profile(name, profileImage, gender, dob, address, contact, mail, qualifications, id, type, dept);
            }
            
        } catch (SQLException ex) {
            System.out.println("Profile Load Failed.");
        }
        return profile;
    }
}
