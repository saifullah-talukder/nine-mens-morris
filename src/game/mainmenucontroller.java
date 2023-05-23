package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;


public class mainmenucontroller {

    private Main main;

    @FXML
    private Button logoutButton;

    @FXML
    private Button statisticsButton;

    @FXML
    private AnchorPane backview;

    @FXML
    private Button playButton;

    @FXML
    private Button helpButton;

    @FXML
    private Button exitButton;

    @FXML
    void clickExit(ActionEvent event) {
        try {
            closeProgram();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void clickHelp(ActionEvent event) {
        try {
            main.showhelpMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clickPlay(ActionEvent event) {
        try {
            main.startGame();
            boolean flag = main.client.getSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ///System.out.println("Playing");
    }

    @FXML
    void clickStatistics(ActionEvent event) {
        try {
            main.showStatMenu();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void clickLogout(ActionEvent event) {
        try {
            main.client.close();
            main.client=null;
            main.showLoginorSignupMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setMain(Main main) {
        this.main = main;
    }

    private void closeProgram() throws Exception {
        int confirmed = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to exit the program?", "Exit Program Message Box",
                JOptionPane.YES_NO_OPTION);


        if (confirmed == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
