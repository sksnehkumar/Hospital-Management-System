
package view.profile;

import java.util.EventListener;

public interface ProfileListener extends EventListener{
    public void profileEdited(ProfileEvent e);
}
