package client;

import javafx.application.Platform;
import game.Main;

public interface Message{

    default void serverNotConnected() {
        /*Platform.runLater(() -> {
            Main.showMessage("Error!","Error connecting server!");
        });*/
    }

    default void otherPlayerDisconnected(){
        /*Platform.runLater(() -> {
            Main.showMessage("Error!","Other player disconnected!");
        });*/
    }
}
