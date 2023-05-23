package game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class statsController {

    private Main main;

    private String w ="No Wins";
    private String l ="No Loses";

    @FXML
    private Button winExpand;

    @FXML
    private TextField winText;

    @FXML
    private Button lossExpand;

    @FXML
    private Button backButton;

    @FXML
    private TextField wlText;

    @FXML
    private TextField lossText;

    public void setMain(Main main) {
        this.main = main;
    }

    public void wExAction(javafx.event.ActionEvent actionEvent) {
        main.client.readInfo();
        if (main.client.getWins()!=null) w=main.client.getWins();
        try {
            main.showWinExpanded(w);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lExAction(javafx.event.ActionEvent actionEvent) {
        main.client.readInfo();
        if (main.client.getLoses()!=null) l=main.client.getLoses();
        try {
            main.showLossExpanded(l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backAction(javafx.event.ActionEvent actionEvent) {
        try {
            main.showMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setStats(){
        main.client.readInfo();
        int counter1 = 0;
        if (main.client.getWins()!=null) {
            w=main.client.getWins();
            for( int i=0; i<w.length(); i++ ) {
                if( w.charAt(i) == ',' ) {
                    counter1++;
                }
            }
            if (w.length()>0) counter1+=1;
        }
        winText.setText(Integer.toString(counter1));
        int counter2=0;
        if (main.client.getLoses()!=null) {
            l=main.client.getLoses();
            for( int i=0; i<l.length(); i++ ) {
                if( l.charAt(i) == ',' ) {
                    counter2++;
                }
            }
            if (l.length()>0) counter2+=1;
        }
        lossText.setText(Integer.toString(counter2));
        int t,a,b;
        a=counter1;
        b=counter2;
        while (b>0) {
            t=b;
            b=a%b;
            a=t;
        }
        if (a>0 && counter1!=0 && counter2!=0) {
            if (counter1%a==0) counter1/=a;
            if (counter2%a==0) counter2/=a;
        }
        wlText.setText(Integer.toString(counter1)+" : "+Integer.toString(counter2));
    }
}

