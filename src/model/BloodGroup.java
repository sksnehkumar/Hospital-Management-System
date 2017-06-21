
package model;

public enum BloodGroup {
    Oplus("O+"),
    Aplus("A+"),
    Bplus("B+"),
    ABplus("AB+"),
    Ominus("O-"),
    Aminus("A-"),
    Bminus("B-"),
    ABminus("AB-");
    
    private String value;
    
    BloodGroup(String value) {
        this.value = value;
    }
    
    public String toString() {
        return value;
    }
}
