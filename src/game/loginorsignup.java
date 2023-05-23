package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javax.swing.*;

public class loginorsignup {


    private Main main;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    void loginAction(ActionEvent event) {
        try {
            main.loginMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void registerAction(ActionEvent event) {
        try {
            main.signupMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setMain(Main main)
    {
        this.main=main;
    }

    private void closeProgram() throws Exception {
        int confirmed = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to exit the program?", "Exit Program Message Box",
                JOptionPane.YES_NO_OPTION);


        if (confirmed == JOptionPane.YES_OPTION) {
            System.exit(0);}
    }

}
