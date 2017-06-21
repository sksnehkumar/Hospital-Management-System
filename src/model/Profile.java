
package model;

import java.sql.Date;

public class Profile {
    private String name;
    private String profileImage;
    private String gender;
    private Date dob;
    private String address;
    private String contact;
    private String mail;
    private String qualifications;
    private String id;
    private StaffType type;
    private Department dept;

    
    public Profile(String name, String profileImage, String gender, Date dob, String address, String contact, String mail, String qualifications, String id, StaffType type, Department dept) {
        this.name = name;
        this.profileImage = profileImage;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.contact = contact;
        this.mail = mail;
        this.qualifications = qualifications;
        this.id = id;
        this.type = type;
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getGender() {
        return gender;
    }

    public Date getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public String getMail() {
        return mail;
    }

    public String getQualifications() {
        return qualifications;
    }

    public String getId() {
        return id;
    }

    public StaffType getType() {
        return type;
    }

    public Department getDept() {
        return dept;
    }
    
    
}
