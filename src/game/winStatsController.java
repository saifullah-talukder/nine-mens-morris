package game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class winStatsController {

    private Main main;

    @FXML
    private Button backButton;

    @FXML
    private TextArea textArea;

    @FXML
    void clickBack(javafx.event.ActionEvent event) {
        try {
            main.showStatMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void show(String s)
    {
        String x=s.replace(",","\n");

        textArea.setText(x);

    }
}
