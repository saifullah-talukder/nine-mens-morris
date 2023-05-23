package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class helpController {

    private Main main;

    @FXML
    private Button backButton;

    @FXML
    void clickBck(ActionEvent event) {
        try {
            main.showMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    void setMain(Main main) {
        this.main = main;
    }

}
