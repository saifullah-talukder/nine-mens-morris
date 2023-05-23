package userData;

import java.io.Serializable;

public class RegisterData implements Serializable {
    private String userName;
    private String password;

    public RegisterData (String userName,String password) {
        this.userName=userName;
        this.password=password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
