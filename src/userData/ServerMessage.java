package userData;

import java.io.Serializable;

public class ServerMessage implements Serializable {
    String message,player;
    int id;
    public ServerMessage (String message,String player,int id) {
        this.message=message;
        this.player=player;
        this.id=id;
    }

    public String getMessage() {
        return message;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
