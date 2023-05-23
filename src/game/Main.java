package game;

import client.Player;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import static java.lang.Math.abs;
import javax.swing.*;

public class Main extends Application implements EventHandler<ActionEvent> {
    Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        showLoginorSignupMenu();
    }

    public void showLoginorSignupMenu() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FirstScreen.fxml"));
        Parent root = loader.load();

        // Loading the controller
        loginorsignup controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login/Sign Up Page ");
        stage.setScene(new Scene(root, 652.0, 470.0));
        stage.setResizable(false);
        stage.show();


        stage.setOnCloseRequest(event -> {
            event.consume();
            try {
                closeProgram();
            } catch (Exception e) {
            }
        });
    }


    public void loginMenu() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        loginController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 693.0, 534.0));
        stage.setResizable(false);
        stage.show();


        stage.setOnCloseRequest(event -> {
            event.consume();
            try {
                closeProgram();
            } catch (Exception e) {

            }
        });
    }

    public void signupMenu() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("register.fxml"));
        Parent root = loader.load();

        // Loading the controller
        signupController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Sign Up");
        stage.setScene(new Scene(root, 693.0, 534.0));
        stage.setResizable(false);
        stage.show();


        stage.setOnCloseRequest(event -> {
            event.consume();
            try {
                closeProgram();
            } catch (Exception e) {

            }
        });
    }

    public void showMainMenu() throws Exception{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("mainmenu.fxml"));
            Parent root = loader.load();

            // Loading the controller
            mainmenucontroller controller = loader.getController();
            controller.setMain(this);

            // Set the primary stage
            stage.setTitle("Nine Men's Morris");
            stage.setScene(new Scene(root, 756.0, 567.0));
            stage.setResizable(false);
            stage.show();


            stage.setOnCloseRequest(event -> {
                event.consume();
                try {
                    closeProgram();
                } catch (Exception e) {

                }
            });
    }

    public void showStatMenu() throws Exception
    {
        System.out.println("stat");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("statmenu.fxml"));
        Parent root = loader.load();

        // Loading the controller
        statsController controller = loader.getController();
        controller.setMain(this);
        controller.setStats();

        // Set the primary stage
        stage.setTitle("statistics");
        stage.setScene(new Scene(root, 674.0, 489.0));
        stage.setResizable(false);
        stage.show();


        stage.setOnCloseRequest(event -> {
            event.consume();
            try {
                closeProgram();
            } catch (Exception e) {

            }
        });
    }

    public void showWinExpanded(String w) throws Exception
    {
        String s= new String();
        s=w;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("winstats.fxml"));
        Parent root = loader.load();

        // Loading the controller
        winStatsController controller = loader.getController();
        controller.setMain(this);
        controller.show(s);

        // Set the primary stage
        stage.setTitle("statistics");
        stage.setScene(new Scene(root, 674.0, 489.0));
        stage.setResizable(false);
        stage.show();


        stage.setOnCloseRequest(event -> {
            event.consume();
            try {
                closeProgram();
            } catch (Exception e) {

            }
        });
    }

    public void showLossExpanded(String l) throws Exception
    {
        String s= new String();
        s=l;


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("lossstats.fxml"));
        Parent root = loader.load();

        // Loading the controller
        lossStatsController controller = loader.getController();
        controller.setMain(this);
        controller.show(s);

        // Set the primary stage
        stage.setTitle("statistics");
        stage.setScene(new Scene(root, 674.0, 489.0));
        stage.setResizable(false);
        stage.show();


        stage.setOnCloseRequest(event -> {
            event.consume();
            try {
                closeProgram();
            } catch (Exception e) {

            }
        });
    }

    public void showhelpMenu() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("helpmenu.fxml"));
        Parent root = loader.load();

        // Loading the controller
        helpController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Help");
        stage.setScene(new Scene(root, 761.0, 614.0));
        stage.setResizable(false);
        stage.show();


        stage.setOnCloseRequest(event -> {
            event.consume();
            try {
                closeProgram();
            } catch (Exception e) {

            }
        });
    }


    /// GameWindow starts

    boolean moveGave=false;
    Player client=null;
    public GameInfo gameInfo;
    private int myGuti;
    Image lalGuti;
    Image nilGuti;
    Image board;
    ImageView lalGutiView[];
    ImageView nilGutiView[];
    ImageView boardView;
    Button backFromGame;
    Button exitGame;
    Label status1,status2,status3,status4;

    public void startGame () throws Exception
    {
        myGuti=9;
        gameInfo = new GameInfo();
        board = new Image(Main.class.getResourceAsStream("board.png"));
        boardView = new ImageView(board);
        boardView.setTranslateX(0);
        boardView.setTranslateY(0);
        boardView.setFitHeight(522);
        boardView.setFitWidth(850);

        lalGuti = new Image(Main.class.getResourceAsStream("lalGuti.png"));
        nilGuti = new Image(Main.class.getResourceAsStream("nilGuti.png"));
        lalGutiView = new ImageView[9];
        nilGutiView = new ImageView[9];
        for (int i=0;i<9;i++) {
            lalGutiView[i] = new ImageView(lalGuti);
            nilGutiView[i] = new ImageView(nilGuti);
            lalGutiView[i].setCursor(Cursor.HAND);
            nilGutiView[i].setCursor(Cursor.HAND);

            lalGutiView[i].setFitHeight(37);
            lalGutiView[i].setFitWidth(37);
            nilGutiView[i].setFitHeight(35);
            nilGutiView[i].setFitWidth(35);
            lalGutiView[i].setPreserveRatio(true);
            nilGutiView[i].setPreserveRatio(true);

            lalGutiView[i].setOnMousePressed(circleOnMousePressedEventHandler);
            lalGutiView[i].setOnMouseDragged(circleOnMouseDraggedEventHandler);
            nilGutiView[i].setOnMousePressed(circleOnMousePressedEventHandler);
            nilGutiView[i].setOnMouseDragged(circleOnMouseDraggedEventHandler);

            lalGutiView[i].setOnMouseReleased(released);
            nilGutiView[i].setOnMouseReleased(released);
        }


        for (int i=0;i<9;i++) {
            gameInfo.setLalX(i,8);
            gameInfo.setLalY(i,i);
            gameInfo.setNilX(i,8);
            gameInfo.setNilY(i,i);
        }
        load();

        backFromGame = new Button("Back");
        backFromGame.getStyleClass().add("my-button");
        backFromGame.setTranslateX(-317);
        backFromGame.setTranslateY(150);
        backFromGame.setOnAction(this);

        exitGame = new Button("Exit");
        exitGame.getStyleClass().add("my-button");
        exitGame.setTranslateX(-317);
        exitGame.setTranslateY(60);
        exitGame.setOnAction(this);

        status1 = new Label("Waiting");
        status1.getStyleClass().add("one-label");
        status1.setTranslateX(-290);
        status1.setTranslateY(-220);

        status2 = new Label("for");
        status2.getStyleClass().add("two-label");
        status2.setTranslateX(-290);
        status2.setTranslateY(-160);

        status3 = new Label("players");
        status3.getStyleClass().add("three-label");
        status3.setTranslateX(-290);
        status3.setTranslateY(-100);

        status4 = new Label("......");
        status4.getStyleClass().add("three-label");
        status4.setTranslateX(-290);
        status4.setTranslateY(-40);

        StackPane stackPane = new StackPane();
        ObservableList list = stackPane.getChildren();
        list.addAll(boardView,backFromGame,exitGame,status1,status2,status3,status4);
        for (int i=0;i<9;i++) list.addAll(lalGutiView[i],nilGutiView[i]);
        stackPane.setPrefSize(850, 522);
        Scene scene = new Scene(stackPane);
        scene.getStylesheets().add("MyStyle.css");

        stage.setTitle("Nine Men's Morris");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            event.consume();
            if (client.isCanPlay()) {
                System.out.println("in closing");
                gameInfo.setFalseWin(true);
                client.playerGiveMove=true;
            }
            try {
                closeProgram();
            } catch (Exception e) {

            }
        });
        stage.show();
    }

    public void play () {
        Platform.runLater(() -> {
            status1.setText(client.getUserName());
            status2.setText("vs");
            status3.setText(client.getOpponent());
            if (client.getMyColor()==1) {
                status4.getStyleClass().add("red-label");
                status4.setText("RED");
            }
            else {
                status4.getStyleClass().add("blue-label");
                status4.setText("BLUE");
            }
        });
    }

    final int rmv=-185;
    public void load () throws Exception
    {
        int lalX[] = gameInfo.getLalX();
        int lalY[] = gameInfo.getLalY();
        int nilX[] = gameInfo.getNilX();
        int nilY[] = gameInfo.getNilY();
        for (int i=0;i<9;i++) {
            if (lalX[i]<=7 && lalY[i]<=7) {
                lalGutiView[i].setTranslateX(gameInfo.getGridX(lalX[i],lalY[i]));
                lalGutiView[i].setTranslateY(gameInfo.getGridY(lalX[i],lalY[i]));
            }
            if (nilX[i]<=7 && nilY[i]<=7) {
                if (nilX[i]<=7) nilGutiView[i].setTranslateX(gameInfo.getGridX(nilX[i],nilY[i]));
                if (nilY[i]<=7) nilGutiView[i].setTranslateY(gameInfo.getGridY(nilX[i],nilY[i]));
            }
            if (lalX[i]==8) {
                lalGutiView[i].setTranslateX(400);
                lalGutiView[i].setTranslateY(gameInfo.getSideLineLal(i));
            }
            if (lalX[i]==9) {
                lalGutiView[i].setTranslateX(rmv);
                lalGutiView[i].setTranslateY(gameInfo.getSideLineLal(i));
            }
            if (nilX[i]==8) {
                nilGutiView[i].setTranslateX(400);
                nilGutiView[i].setTranslateY(gameInfo.getSideLineNil(i));
            }
            if (nilX[i]==9) {
                nilGutiView[i].setTranslateX(rmv);
                nilGutiView[i].setTranslateY(gameInfo.getSideLineNil(i));
            }
        }
    }

    @Override
    public void handle (ActionEvent event) {
        ///System.out.println(client.getMyColor());
        if (event.getSource()==backFromGame) {
            if (client.getMyColor()==1 || client.getMyColor()==2) {
                gameInfo.setFalseWin(true);
                client.playerGiveMove=true;
                System.out.println("here");
                try {
                    System.out.println(client.getMyColor());
                    showMainMenu();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("return");
                return;
            }
        }
        if (event.getSource()==exitGame) {
            if (client.getMyColor()==1 || client.getMyColor()==2) {
                System.out.println("here");
                gameInfo.setFalseWin(true);
                client.playerGiveMove=true;
                try {
                    System.out.println(client.getMyColor());
                    closeProgram();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("return");
                return;
            }
        }
    }

    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    int orgX, orgY, gutiNum;
    EventHandler<MouseEvent> circleOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                    orgTranslateX = ((ImageView)(t.getSource())).getTranslateX();
                    orgTranslateY = ((ImageView)(t.getSource())).getTranslateY();
                    orgX=orgY=gutiNum=-1;
                    int lalX[] = gameInfo.getLalX();
                    int lalY[] = gameInfo.getLalY();
                    int nilX[] = gameInfo.getNilX();
                    int nilY[] = gameInfo.getNilY();
                    for (int i=0;i<9;i++) {
                        double tempX=50;
                        double tempY=50;
                        if (client.getMyColor()==1) {
                            if (!gameInfo.isMillFormed()) {
                                if (lalX[i]<=7 && lalY[i]<=7) {
                                    tempX = abs(gameInfo.getGridX(lalX[i], lalY[i]) - orgTranslateX);
                                    tempY = abs(gameInfo.getGridY(lalX[i], lalY[i]) - orgTranslateY);
                                }
                                if (lalX[i]==8) {
                                    tempX = abs(400 - orgTranslateX);
                                    tempY = abs(gameInfo.getSideLineLal(i) - orgTranslateY);
                                }
                            }
                            else {
                                if (nilX[i]<=7 && nilY[i]<=7) {
                                    tempX = abs(gameInfo.getGridX(nilX[i],nilY[i]) - orgTranslateX);
                                    tempY = abs(gameInfo.getGridY(nilX[i],nilY[i]) - orgTranslateY);
                                }
                            }
                        }
                        else {
                            if (!gameInfo.isMillFormed()) {
                                if (nilX[i]<=7 && nilY[i]<=7) {
                                    tempX = abs(gameInfo.getGridX(nilX[i],nilY[i]) - orgTranslateX);
                                    tempY = abs(gameInfo.getGridY(nilX[i],nilY[i]) - orgTranslateY);
                                }
                                if (nilX[i]==8) {
                                    tempX = abs(400 - orgTranslateX);
                                    tempY = abs(gameInfo.getSideLineNil(i) - orgTranslateY);
                                }
                            }
                            else {
                                if (lalX[i]<=7 && lalY[i]<=7) {
                                    tempX = abs(gameInfo.getGridX(lalX[i], lalY[i]) - orgTranslateX);
                                    tempY = abs(gameInfo.getGridY(lalX[i], lalY[i]) - orgTranslateY);
                                }
                            }
                        }
                        if (tempX <= 1 && tempY<=1) {
                            gutiNum=i;
                            if (client.getMyColor()==1) {
                                if (!gameInfo.isMillFormed()) {
                                    orgX=lalX[i];
                                    orgY=lalY[i];
                                }
                                else {
                                    orgX=nilX[i];
                                    orgY=nilY[i];
                                }
                            }
                            else {
                                if (!gameInfo.isMillFormed()) {
                                    orgX=nilX[i];
                                    orgY=nilY[i];
                                }
                                else {
                                    orgX=lalX[i];
                                    orgY=lalY[i];
                                }
                            }
                            break;
                        }
                    }
                }
            };



    EventHandler<MouseEvent> circleOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;

                    if (gutiNum!=-1) {
                        ((ImageView)(t.getSource())).setTranslateX(newTranslateX);
                        ((ImageView)(t.getSource())).setTranslateY(newTranslateY);
                    }
                }
            };

    void remove (ImageView IV)
    {
        gameInfo.setIsThere(orgX,orgY,0);
        if (client.getMyColor()==1) {
            IV.setTranslateX(rmv);
            IV.setTranslateY(55+gutiNum*20);
            gameInfo.setNilX(gutiNum,9);
            gameInfo.setNilY(gutiNum,gutiNum);
        }
        else if (client.getMyColor()==2) {
            IV.setTranslateX(rmv);
            IV.setTranslateY(-220+gutiNum*20);
            gameInfo.setLalX(gutiNum,9);
            gameInfo.setLalY(gutiNum,gutiNum);
        }
        gameInfo.setMillFormed(false);
    }


    void reset (int k,int l)
    {
        gameInfo.setIsThere(k,l,client.getMyColor());
        if (orgX!=-1 && orgX<=7) gameInfo.setIsThere(orgX,orgY,0);
        if (client.getMyColor()==1) {
            gameInfo.setLalX(gutiNum,k);
            gameInfo.setLalY(gutiNum,l);
        }
        else {
            gameInfo.setNilX(gutiNum,k);
            gameInfo.setNilY(gutiNum,l);
        }
    }

    void invalidMove ()
    {
        Platform.runLater(() -> {
            showMessage("Invalid!","Invalid Move!");
        });
    }

    EventHandler<MouseEvent> released =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                        if (gutiNum==-1 || !client.playerMove) {
                            System.out.println("Invalid Move!");
                            invalidMove();
                            ((ImageView) (t.getSource())).setTranslateX(orgTranslateX);
                            ((ImageView) (t.getSource())).setTranslateY(orgTranslateY);
                            return;
                        }

                        if (client.playerMove && gameInfo.isMillFormed()) {
                            System.out.println("now");
                            remove ((ImageView)t.getSource());
                            System.out.println("Yesbjhbj");
                            client.playerGiveMove=true;
                            client.playerMove=false;
                            return;
                        }

                        double offsetX = t.getSceneX() - orgSceneX;
                        double offsetY = t.getSceneY() - orgSceneY;
                        double newTranslateX = orgTranslateX + offsetX;
                        double newTranslateY = orgTranslateY + offsetY;

                        boolean found=false;
                        for (int i=0;i<7;i++) {
                            if (found) break;
                            for (int j=0;j<7;j++) if (gameInfo.getIsThere(i,j)==0){
                                double tempX = abs(gameInfo.getGridX(i,j) - newTranslateX);
                                double tempY = abs(gameInfo.getGridY(i,j) - newTranslateY);
                                if (tempX <= 10 && tempY <= 10) {
                                    if (!checkMove(i,j,orgX,orgY)) continue;
                                    found = true;
                                    reset(i,j);
                                    checkMill(i,j);
                                    ((ImageView) (t.getSource())).setTranslateX(gameInfo.getGridX(i,j));
                                    ((ImageView) (t.getSource())).setTranslateY(gameInfo.getGridY(i,j));
                                    break;
                                }
                            }
                        }

                        if (!found) {
                            System.out.println("Invalid Move!");
                            invalidMove();
                            ((ImageView) (t.getSource())).setTranslateX(orgTranslateX);
                            ((ImageView) (t.getSource())).setTranslateY(orgTranslateY);
                        }
                        else {
                            System.out.println("Yesbhjhk");
                            ////////////////////////////////////////////
                            client.playerMove=false;
                            client.playerGiveMove = true;//move
                            ///////////////
                        }

                }
            };


    boolean checkMove (int i,int j,int orgX,int orgY)
    {
        System.out.println("cheking move   "+  i+"    "+j+"     "+orgX+"     "+orgY+"     "+client.getCntGuti());
        if (orgX!=8 && client.getCntGuti()<9) return false;
        if (orgX==8) {
            System.out.println("taken from" + gutiNum);
            client.setCntGuti(client.getCntGuti()+1);
            return true;
        }
        if (i!=orgX && j!=orgY) return false;
        if (i==orgX) {
            int p = Math.min(j,orgY);
            int q = Math.max(j,orgY);
            if (i==3) {
                if (orgY<3) q = Math.min(q,2);
                else p = Math.max(p,4);
            }
            for (int k=p+1;k<q;k++) if (gameInfo.getIsThere(i,k)!=-1) return false;
        }
        if (j==orgY) {
            int p = Math.min(i,orgX);
            int q = Math.max(i,orgX);
            if (i==3) {
                if (orgX<3) q = Math.min(q,2);
                else p = Math.max(p,4);
            }
            for (int k=p+1;k<q;k++) if (gameInfo.getIsThere(k,j)!=-1) return false;
        }
        return true;
    }

    void checkMill (int i,int j)
    {
        ///System.out.println("in checkMill     " + i +"   "+j+"    color =  " +client.getMyColor());
        int k,cnt=0;
        if (i==3) {
            ///System.out.println("here");
            if (j<3) {
                for (k=0;k<3;k++) if (gameInfo.getIsThere(i,k)==client.getMyColor()) ++cnt;
                //System.out.println("     " +  gameInfo.getIsThere(i,k));
            }
            else {
                for (k=4;k<7;k++) if (gameInfo.getIsThere(i,k)==client.getMyColor()) ++cnt;
                    ///System.out.println("     " +  gameInfo.getIsThere(i,k));
            }
        }
        else {
            ///System.out.println("not there");
            for (k=0;k<7;k++) if (gameInfo.getIsThere(i,k)==client.getMyColor()) ++cnt;
        }
        if (cnt==3) {
            gameInfo.setMillFormed(true);
            Platform.runLater(() -> {
                showMessage("Mill formed!","You formed a meal!");
            });
            return;
        }
        System.out.println("cnt = " + cnt);

        cnt=0;
        if (j==3) {
            if (i<3) {
                for (k = 0; k < 3; k++) if (gameInfo.getIsThere(k, j) == client.getMyColor()) ++cnt;
            }
            else {
                for (k=4;k<7;k++) if (gameInfo.getIsThere(k,j)==client.getMyColor()) ++cnt;
            }
        }
        else {
            for (k=0;k<7;k++) if (gameInfo.getIsThere(k,j)==client.getMyColor()) ++cnt;
        }
        if (cnt==3) {
            gameInfo.setMillFormed(true);
            Platform.runLater(() -> {
                showMessage("Mill formed!","You formed a meal!");
            });
        }
        System.out.println("cnt = " + cnt);
    }

    boolean isNoMove ()
    {
        if (client.getCntGuti()<9) return false;
        if (myGuti<3) return true;
        int myX[],myY[];
        if (client.getMyColor()==1) {
            myX=gameInfo.getLalX();
            myY=gameInfo.getLalY();
        }
        else {
            myX=gameInfo.getNilX();
            myY=gameInfo.getNilY();
        }
        int cnt=0;
        for (int i=0;i<9;i++) {
            if (myX[i]<7 && myY[i]<7) {
                int x=myX[i];
                int y=myY[i];
                for (int j=0;j<7;j++) for (int k=0;k<7;k++) if (gameInfo.getIsThere(j,k)==0) {
                    if (checkMove(x,y,j,k)) {cnt++;
                        System.out.println(x+"   "+y+"   "+j+"   "+k);}
                }
            }
        }
        System.out.println("isnomove    " + cnt);
        if (cnt==0) return true;
        else return false;
    }

    void lost ()
    {
        client.addLose();
        System.out.println("lost");
        client.playerMove=false;
        client.setCanPlay(false);
        Platform.runLater(() -> {
            showMessage("Loser!","Sorry! You lost the Game!");
        });
    }

    void win ()
    {
        client.addWin();
        System.out.println("win");
        client.playerMove=false;
        client.setCanPlay(false);
        Platform.runLater(() -> {
            showMessage("Winner!","Congratulations! You won the Game!");
        });
    }

    void falseWin () {
        System.out.println("flaseWin");
        client.playerMove=false;
        client.setCanPlay(false);
        Platform.runLater(() -> {
            showMessage("Winner!","Other player has exited! You won the Game!");
        });
    }

    //// GameWindow ends


    ////Alert Box
    public static void showMessage (String title, String message) {
        Stage alert = new Stage();
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setWidth(350);
        alert.setHeight(150);

        Label msg = new Label();
        msg.setText(message);
        Button close = new Button("OK");
        close.getStyleClass().add("ok-button");
        close.setOnAction(e -> alert.close());
        VBox layout = new VBox(10);
        layout.getChildren().addAll(msg,close);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("MyStyle.css");
        alert.setScene(scene);
        alert.showAndWait();
    }
    //// AlertBox


    ////Setting up Client
    public void setClient(Object data){
        client=new Player(data,this) {
            @Override
            public void initialize() {
                return;
            }

            @Override
            public <T> T move() {
                System.out.println("in move " + gameInfo);//
                return (T) gameInfo;///
            }

            @Override
            public <T> void performMove(T data) {

                System.out.println("receiving move "+ data);//
                gameInfo=(GameInfo) data;
                client.playerGiveMove=false;
                try {//
                    load();//
                } catch (Exception e) {//
                    e.printStackTrace();//
                }//
                if (gameInfo.isWin()) {
                    win();
                    return;
                }
                if (gameInfo.isFalseWin()) {
                    System.out.println("in false");
                    falseWin();
                    return;
                }
                if (!gameInfo.isMillFormed()) {
                    if (gameInfo.isNullMove()) {
                        gameInfo.setMillFormed(true);
                        gameInfo.setNullMove(false);
                        client.playerMove=true;
                    }
                    if (isNoMove()) {
                        gameInfo.setWin(true);
                        client.playerGiveMove=true;
                        lost();
                    }
                    else client.playerMove=true;////
                }
                else {
                    myGuti--;
                    Platform.runLater(() -> {
                        showMessage("Mill formed!","Your opponent formed a meal!");
                    });
                    gameInfo.setMillFormed(false);
                    gameInfo.setNullMove(true);
                    client.playerGiveMove=true;
                }
            }

            @Override
            public void setIp() {
                ipAdress="localhost";
            }
        };
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void closeProgram() throws Exception {
        System.out.println("close");
        int confirmed = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to exit the game?", "Confirm Exit",
                JOptionPane.YES_NO_OPTION);


        if (confirmed == JOptionPane.YES_OPTION) {
            System.out.println("closing");
            System.exit(0);}
    }
}
