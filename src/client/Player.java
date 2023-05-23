package client;

import game.GameInfo;
import game.Main;
import javafx.application.Platform;
import userData.*;
import utilClient.NetworkUtilClient;

import java.io.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public abstract class  Player implements Message{
    public boolean playerGiveMove=true;
    public boolean playerMove=false;
    private NetworkUtilClient networkUtil;
    private boolean myMove;
    private Object receiveData;
    private Thread thread;
    public String ipAdress="localhost";
    private int port=56789;
    private int myColor=3;
    private int cntGuti;
    private String opponent;
    private String userName;
    private boolean loginSuccessful=false;
    private boolean canPlay=false;
    private String wins;
    private String loses;
    private Main main;
    Object data;

    public boolean isCanPlay() {
        return canPlay;
    }

    public void setCanPlay(boolean canPlay) {
        this.canPlay = canPlay;
    }

    public String getWins() {
        return wins;
    }

    public String getLoses() {
        return loses;
    }

    public int getMyColor() {
        return myColor;
    }

    public void setMyColor(int myColor) {
        this.myColor = myColor;
    }

    public int getCntGuti() {
        return cntGuti;
    }

    public void setCntGuti(int cntGuti) {
        this.cntGuti = cntGuti;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }

    public void setLoginSuccessful(boolean loginSuccessful) {
        this.loginSuccessful = loginSuccessful;
    }


    ////Constructor
    public Player(Object data,Main main) {
        this.main=main;
        this.data=data;
        connectToServer();
    }

    public void addWin () {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(userName+"wins.txt",true));
            bw.write(opponent);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in writing winner");
        }
    }
    public void addLose () {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(userName+"loses.txt",true));
            bw.write(opponent);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in writing loser");
        }
    }

    public void connectToServer(){
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                setIp();
                networkUtil =new NetworkUtilClient(ipAdress,port);
                if (data instanceof RegisterData) {
                    RegisterData registerData = (RegisterData) data;
                    register(registerData);
                }
                if (data instanceof LoginData) {
                    LoginData loginData = (LoginData) data;
                    logIN(loginData);
                }
                if(networkUtil.isAvaiable()){
                    while (true) {
                        ///System.out.print("");
                        try {
                            thread.sleep(100);
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (loginSuccessful) {
                            Platform.runLater(() -> {
                                try {
                                    main.showMainMenu();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                            break;
                        }
                    }
                    System.out.println("logged in");
                    while (true) {
                        //System.out.print("");
                        try {
                            thread.sleep(100);
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (canPlay) {
                            main.play();
                            break;
                        }
                    }
                    System.out.println("mycolor  "+myColor);
                    if(myColor==1) {
                        initialize();
                        cntGuti = 0;
                        playerMove = myMove = true;
                        playerGiveMove = false;
                    }
                    else {
                        cntGuti=0;
                        playerMove=myMove=false;
                    }
                    startGame();
                }
                else{
                    serverNotConnected();
                }
            }
        };
        thread= new Thread(runnable);
        thread.start();
    }

    public void readInfo () {
        String line;
        wins=loses=null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(userName+"wins.txt"));
            while (true) {
                line = br.readLine();
                if (line == null) break;
                if (wins==null) wins=line;
                else wins=wins+","+line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in reading file");
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(userName+"loses.txt"));
            while (true) {
                line = br.readLine();
                if (line == null) break;
                if (loses==null) loses=line;
                else loses=loses+","+line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in reading file");
        }
        System.out.println(wins);
        System.out.println(loses);
    }

    public boolean logIN (LoginData loginData) {
        networkUtil.write(loginData);
        Object SM;
        while (true) {
            SM = networkUtil.read();
            if (SM instanceof ServerMessage) break;
        }
        ServerMessage serverMessage = (ServerMessage) SM;
        if (serverMessage.getMessage().equals("already")) {
            Platform.runLater(() -> {
                Main.showMessage("Error!","User already logged in!");
            });
            return false;
        }
        else if (serverMessage.getMessage().equals("notexist")) {
            Platform.runLater(() -> {
                Main.showMessage("Error!","Username doesn't exist!");
            });
            return false;
        }
        else if (serverMessage.getMessage().equals("wrong")) {
            Platform.runLater(() -> {
                Main.showMessage("Error!","Username and password don't match!");
            });
            return false;
        }
        else if (serverMessage.getMessage().equals("login")) {
            userName=loginData.getUserName();
            loginSuccessful=true;
            return true;
        }
        return false;
    }

    public boolean register (RegisterData registerData) {
        networkUtil.write(registerData);
        Object SM;
        while (true) {
            System.out.println("in register");
            SM = networkUtil.read();
            if (SM instanceof ServerMessage) break;
        }
        ServerMessage serverMessage = (ServerMessage) SM;
        if (serverMessage.getMessage().equals("wrong")) {
            Platform.runLater(() -> {
                Main.showMessage("Error!","Username already exists!");
            });
            return false;
        }
        if (serverMessage.getMessage().equals("register")) {
            userName=registerData.getUserName();
            File file1 = new File(userName+"wins.txt");
            File file2 = new File(userName+"loses.txt");
            try {
                file1.createNewFile();
                file2.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
            loginSuccessful=true;
            System.out.println("registered");
            return true;
        }
        return false;
    }

    public boolean getSet () {
        System.out.println("in getset");
        Intention intention = new Intention(true);
        networkUtil.write(intention);
        while (true) {
            Object data = networkUtil.read();
            if (data instanceof ServerMessage) {
                ServerMessage serverMessage = (ServerMessage) data;
                if (serverMessage.getMessage().equals("play")) {
                    opponent=serverMessage.getPlayer();
                    myColor=serverMessage.getId();
                    break;
                }
            }
        }
        System.out.println("canplay  "+opponent);
        canPlay=true;
        return true;
    }

    public void startGame(){
        while (true){
            if(myMove){
                System.out.println("Please give a move");
                Object o =moveData();
                System.out.println((GameInfo) o);
                System.out.println("hhkj");
                myMove=false;
            }
            else {
                System.out.println("Receive a move data");

                receiveData= networkUtil.read();
                if(receiveData==null ){
                    return;
                }
                else{
                    try{
                        if(((String) receiveData).equals("EXIT")){
                            return;
                        }
                    }catch (Exception e){ }
                }
                performMove(receiveData);
                myMove=true;
            }
        }
    }


    public <T> T moveData(){
        System.out.println("in moveData");
        Future futureMove;
        Callable callable= () -> {
            System.out.println("Working");
            while(true){
                if(playerGiveMove){
                    System.out.println("Giving move");
                    playerGiveMove=false;
                    GameInfo gameInfo=move();
                    System.out.println("sending "+gameInfo);
                    networkUtil.write(gameInfo);
                    return null;
                }
                else{
                    System.out.print("");
                    //wait();
                }

            }
        };
        ExecutorService executor = Executors.newFixedThreadPool(1);
        futureMove=executor.submit(callable);
        return null;
    }

    public void close () {
        LogoutData logout = new LogoutData(userName);
        networkUtil.write(logout);
    }

    public  abstract void initialize();

    public abstract <T>  T move();

    public abstract <T> void performMove(T data);

    public abstract void setIp();

}

