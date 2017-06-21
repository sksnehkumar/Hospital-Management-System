
package view.profile;

import java.sql.Date;
import java.util.EventObject;
import model.Department;
import model.Profile;
import model.StaffType;

public class ProfileEvent extends EventObject{
    private Profile profile;

    public ProfileEvent(Object source, Profile profile) {
        super(source);
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }
    
    
}
