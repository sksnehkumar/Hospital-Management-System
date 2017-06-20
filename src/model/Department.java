
package model;

public enum Department {
    Casualty,
    Anaesthetics,
    Cardiology,
    CriticalCare("Critical Care"),
    ENT("Ear, Nose and Throat"),
    Geriatrics,
    Gastroenterology,
    GeneralSurgery("General Surgery"),
    Gynaecology,
    Haematology,
    Maternity,
    Neurology,
    Oncology,
    Ophthalmology,
    Orthopedics,
    Urology,
    Psychiatry,
    OPD("Out Patient"),
    Sterlization("Central Sterlization Unit"),
    Housekeeping,
    Catering,
    Physiotherapy,
    Pharmacy,
    Microbiology,
    DiagonosticImaging("Diagonostic Imaging"),
    MedicalRecords("Medical Records"),
    Maintenance,
    IT("Information Technology"),
    HR("Human Resources"),
    Finance,
    Administraion;
    
    private String value;
    
    Department() {
        
    }
    
    Department(String value) {
        this.value = value;
    }
    
    public String toString() {
        if(value == null) {
            return super.toString();
        }
        else
            return value;
    }
}
