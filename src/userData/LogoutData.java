package userData;

import java.io.Serializable;

public class LogoutData implements Serializable {
    private String userName;

    public LogoutData (String userName) {
        this.userName=userName;
    }

    public String getUserName() {
        return userName;
    }

}
