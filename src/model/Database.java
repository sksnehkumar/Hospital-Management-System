
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private Connection conn;
    private List<Patient> patientRecords;

    public Database() {
        patientRecords = new LinkedList<Patient>();
    }
    
    
    
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

    public void savePatient(Patient patient) {
        try {
            
            boolean user = false;
            
            String check = "Select * from patient where patno = ?";
            PreparedStatement checkStmt = conn.prepareStatement(check);
            checkStmt.setInt(1, patient.getPatNo());
            ResultSet rs = checkStmt.executeQuery();
            if(rs.next())
                user = true;
            
            if(!user) {
                int col = 1;
                String save = "insert into patient values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement saveStmt = conn.prepareStatement(save);
                saveStmt.setInt(col++, patient.getPatNo());
                saveStmt.setString(col++, patient.getName());
                saveStmt.setString(col++, patient.getGender());
                saveStmt.setInt(col++, patient.getAge());
                saveStmt.setString(col++, patient.getStatus());
                saveStmt.setString(col++, patient.getGroup());
                saveStmt.setString(col++, patient.getIdentifiers());
                saveStmt.setString(col++, patient.getAddress());
                saveStmt.setString(col++, patient.getContact());
                saveStmt.setString(col++, patient.getMail());
                saveStmt.setBoolean(col++, patient.isInsured());
                saveStmt.setString(col++, patient.getComp());
                saveStmt.setString(col++, patient.getInsNo());
                saveStmt.setDate(col++, patient.getRegDate());
                saveStmt.executeUpdate();
                saveStmt.close(); 
            } 
            else {
                int col = 1;
                String update = "update patient set status = ?, identifiers = ?, address = ?, contact = ?, mail = ?, insurance = ?,"
                        + "inscomp = ?, insno = ? where patno = ?";
                PreparedStatement updateStmt = conn.prepareStatement(update);
                
                updateStmt.setString(col++, patient.getStatus());
                updateStmt.setString(col++, patient.getIdentifiers());
                updateStmt.setString(col++, patient.getAddress());
                updateStmt.setString(col++, patient.getContact());
                updateStmt.setString(col++, patient.getMail());
                updateStmt.setBoolean(col++, patient.isInsured());
                updateStmt.setString(col++, patient.getComp());
                updateStmt.setString(col++, patient.getInsNo());
                updateStmt.setInt(col++, patient.getPatNo());
                updateStmt.executeUpdate();
                updateStmt.close(); 
            }
            checkStmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public List<Patient> loadPatientRecords() {
        try {
            patientRecords.clear();
            
            String fetch = "Select * from patient";
            Statement fetchStmt = conn.createStatement();
            
            ResultSet rs = fetchStmt.executeQuery(fetch);
            while(rs.next()) {
                int patNo = rs.getInt("patno");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String status = rs.getString("status");
                String group = rs.getString("group");
                String identifiers = rs.getString("identifiers");
                String address = rs.getString("address");
                String contact = rs.getString("contact");
                String mail = rs.getString("mail");
                boolean isInsured = rs.getBoolean("insurance");
                String comp = rs.getString("inscomp");
                String insNo = rs.getString("insno");
                
                Patient newPatient = new Patient(patNo, name, gender, age, status, group, identifiers, address, contact, mail, 
                        isInsured, comp, insNo, new Date(System.currentTimeMillis()));
                
                patientRecords.add(newPatient);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return patientRecords;
    }
    
    public int getNextPatNo() {
        if(patientRecords.size() != 0) {
            Patient patient = patientRecords.get(patientRecords.size() - 1);
            return patient.getPatNo() + 1;      
        }
        return 1001;
    }
}
