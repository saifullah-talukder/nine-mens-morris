package server;

import userData.*;
import utilServer.NetworkUtilServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Deque;

class ServeGame {
    private Thread thread;
    private HashMap<String, NetworkUtilServer> clientMap;
    Deque<String> clientQ;
    Server server;

    ServeGame (Server server) {
        this.server=server;
        clientMap = new HashMap<>();
        clientQ = new LinkedList<>();
        serve();
    }

    public void add (String userName, NetworkUtilServer nc) {
        System.out.println("added   " + userName);
        clientMap.put(userName,nc);
        clientQ.add(userName);
        System.out.println("size = " + clientQ.size());
    }

    public void serve(){
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        thread.sleep(100);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    //System.out.print("");
                    if (clientQ.size()<2) continue;
                    String name1,name2;
                    NetworkUtilServer player1,player2;
                    name1=clientQ.pop();
                    name2=clientQ.pop();
                    player1=clientMap.get(name1);
                    player2=clientMap.get(name2);
                    ServerMessage serverMessage = new ServerMessage("play",name2,1);
                    player1.write(serverMessage);
                    serverMessage.setPlayer(name1);
                    serverMessage.setId(2);
                    player2.write(serverMessage);
                    new NewGame(player1,player2,server);
                }
            }
        };
        thread=new Thread(runnable);
        thread.start();
    }
}

class ServeClient {
    private Thread thread;
    private String userName;
    private String password;
    private NetworkUtilServer networkUtil;
    private Server server;

    ServeClient (NetworkUtilServer networkUtil,Server server) {
        System.out.println("In serveclient");
        this.server=server;
        this.networkUtil=networkUtil;
        serve();
    }

    public void serve(){
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //System.out.println("");
                    Object data=networkUtil.read();
                    if (data instanceof RegisterData) {
                        RegisterData registerData = (RegisterData) data;
                        userName=registerData.getUserName();
                        password=registerData.getPassword();
                        if (server.userInfo.get(userName)!=null) {
                            String message = new String("wrong");
                            ServerMessage serverMessage = new ServerMessage(message, null,0);
                            networkUtil.write(serverMessage);
                            continue;
                        }
                        else {
                            server.add(userName, password);
                            String message = new String("register");
                            server.loggedIn.add(userName);
                            ServerMessage serverMessage = new ServerMessage(message, null,0);
                            networkUtil.write(serverMessage);
                            System.out.println("registered");
                            break;
                        }
                    }
                    else if (data instanceof LoginData) {
                        LoginData loginData = (LoginData) data;
                        userName=loginData.getUserName();
                        password=loginData.getPassword();
                        if (server.loggedIn.indexOf(userName)!=-1) {
                            String message= new String("already");
                            ServerMessage serverMessage = new ServerMessage(message,null,0);
                            networkUtil.write(serverMessage);
                            continue;
                        }
                        else if (server.userInfo.get(userName)==null) {
                            String message= new String("notexist");
                            ServerMessage serverMessage = new ServerMessage(message,null,0);
                            networkUtil.write(serverMessage);
                            continue;
                        }
                        else if (!server.userInfo.get(userName).equals(password)) {
                            String message= new String("wrong");
                            ServerMessage serverMessage = new ServerMessage(message,null,0);
                            networkUtil.write(serverMessage);
                            continue;
                        }
                        else {
                            String message= new String("login");
                            server.loggedIn.add(userName);
                            ServerMessage serverMessage = new ServerMessage(message,null,0);
                            networkUtil.write(serverMessage);
                            break;
                        }
                    }
                }
                while (true){
                    //System.out.println("");
                    Object data=networkUtil.read();
                    if (data instanceof Intention) {
                        Intention intention = (Intention) data;
                        if (intention.isWantToPlay()) {
                            server.serveGame.add(userName,networkUtil);
                            break;
                        }
                    }

                    if (data instanceof LogoutData) {
                        LogoutData logoutData = (LogoutData) data;
                        server.logout(logoutData);
                        break;
                    }
                }
            }
        };
        thread=new Thread(runnable);
        thread.start();
    }
}

public class Server {

    public ServeGame serveGame;
    private int clientCount=0;
    private int port=56789;
    private ServerSocket serverSocket;
    public HashMap<String, String> userInfo;
    public ArrayList<String> loggedIn;

    public Server(){
        try {
            serverSocket = new ServerSocket(port);
            serveGame = new ServeGame(this);
            userInfo = new HashMap<>();
            loggedIn = new ArrayList<String>();
            readInfo();
            while (true){
                getClient();
            }
        } catch (IOException e) {
            System.out.println("Exception in opening server socket");
            e.printStackTrace();
        }
    }

    public void logout (LogoutData logoutData)
    {
        if (loggedIn.indexOf(logoutData.getUserName())!=-1) {
            loggedIn.remove(logoutData.getUserName());
        }
    }

    public void readInfo () {
        final String FILE_NAME = "users.txt";
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            while (true) {
                line = br.readLine();
                if (line == null) break;
                String[] out;
                out=line.split(",");
                System.out.println(out[0]);
                userInfo.put(out[0],out[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in reading file");
        }
    }

    public void add (String userName, String password) {
        userInfo.put(userName,password);
        final String FILE_NAME = "users.txt";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME,true));
            bw.write(userName+","+password);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in writing file");
        }
    }

    public void getClient() {
        Socket playerSocket =  null;
        NetworkUtilServer networkUtil=null;

        while (true){
            try {
                if (playerSocket==null) {
                    playerSocket = serverSocket.accept();///login
                    networkUtil = new NetworkUtilServer(playerSocket);
                    new ServeClient(networkUtil, this);
                    System.out.println((clientCount + 1) + " th client connected");
                    clientCount++;
                    playerSocket=null;
                }
            } catch (Exception e) {
                System.out.println("Client not connected");
                e.printStackTrace();
                continue;
            }
        }
    }

    public int getClientCount() {
        return clientCount;
    }

    public void setClientCount(int clientCount) {
        this.clientCount = clientCount;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public String toString() {
        return "Server{" +
                "clientCount=" + clientCount +
                ", port=" + port +
                ", serverSocket=" + serverSocket +
                '}';
    }
}

