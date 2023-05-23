package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import userData.*;

public class signupController {

    private Main main;


    @FXML
    private TextField usenameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button resetButton;

    @FXML
    private Button registerButton;

    @FXML
    private Button backButton;

    @FXML
    void backAction(ActionEvent event) {
        try {
            main.showLoginorSignupMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void registerAction(ActionEvent event) {
        String username= usenameField.getText();
        String password= passwordField.getText();
        RegisterData registerData = new RegisterData(username,password);
        main.setClient(registerData);
    }

    @FXML
    void resetAction(ActionEvent event) {
        usenameField.setText(null);
        passwordField.setText(null);
    }

    public void setMain(Main main) {
        this.main = main;
    }
}

