package utilClient;

import client.Message;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class NetworkUtilClient  implements Message{
    boolean avaiable=true;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public NetworkUtilClient(String s, int port) {
        try {
            this.socket = new Socket(s, port);
            System.out.println("server connected");
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            avaiable=false;
            System.out.println("Cannot connnect to server: ");
        }
    }

    public NetworkUtilClient(Socket s) {
        try {
            this.socket = s;
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            System.out.println("In NetworkUtilClient : ");
        }
    }

    public Object read() {
        try {
            while (true){
                System.out.println("reading ");
                if(ois.available()>=0){
                  return  ois.readObject();
                }
                if(ois.available()==-1){
                    return "EXIT";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Reading Error in networkUtil : ");
        }
        return null;
    }

    public <T> boolean write(T o) {
        try {
            oos.writeObject(o);
            return true;
        } catch (Exception e) {
            System.out.println("Writing  Error in network : ");
            closeConnection();
            return false;
        }
    }

    public void closeConnection() {
        try {
            ois.close();
            oos.close();
            otherPlayerDisconnected();
        } catch (Exception e) {

            System.out.println("Closing Error in network : ");
        }
    }

    public boolean isAvaiable() {
        return avaiable;
    }
}

