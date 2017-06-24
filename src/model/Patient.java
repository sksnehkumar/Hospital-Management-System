
package model;

import java.sql.Date;

public class Patient {
    private int patNo;
    private String name;
    private String gender;
    private int age;
    private String status;
    private String group;
    private String identifiers;
    private String address;
    private String contact;
    private String mail;
    private boolean insured;
    private String comp;
    private String insNo;
    private Date regDate;

    public Patient(int patNo, String name, String gender, int age, String status, String group, String identifiers, String address, 
            String contact, String mail, boolean insured, String comp, String insNo, Date regDate) {
        this.patNo = patNo;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.status = status;
        this.group = group;
        this.identifiers = identifiers;
        this.address = address;
        this.contact = contact;
        this.mail = mail;
        this.insured = insured;
        this.comp = comp;
        this.insNo = insNo;
        this.regDate = regDate;
    }

    public int getPatNo() {
        return patNo;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getStatus() {
        return status;
    }

    public String getGroup() {
        return group;
    }

    public String getIdentifiers() {
        return identifiers;
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

    public boolean isInsured() {
        return insured;
    }

    public String getComp() {
        return comp;
    }

    public String getInsNo() {
        return insNo;
    }

    public Date getRegDate() {
        return regDate;
    }
    
    
    
    
}
