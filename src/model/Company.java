
package model;

public enum Company {
    MB("Max Bupa"),
    BA("Bajaj Allianz"),
    UHG("United Health Group"),
    AM("Apollo Munich"),
    IL("ICICI Lombard"),
    NI("National Insurance"),
    BX("Bharti AXA");
    
    private String value;
    
    Company(String value) {
        this.value = value;
    }
    
    public String toString() {
        return value;
    }
}
