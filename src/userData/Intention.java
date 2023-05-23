package userData;

import java.io.Serializable;

public class Intention implements Serializable {
    boolean wantToPlay;
    public Intention (boolean wantToPlay) {
        this.wantToPlay=wantToPlay;
    }

    public boolean isWantToPlay() {
        return wantToPlay;
    }
}
