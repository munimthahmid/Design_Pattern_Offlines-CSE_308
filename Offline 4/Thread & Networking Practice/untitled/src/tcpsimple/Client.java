package tcpsimple;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    public Client(String serverAddress, int serverPort) {
        try
        {
        Socket socket=new Socket(serverAddress,serverPort);
        ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
        String name="Munim";

        oos.writeObject(name);

        System.out.println(ois.readObject());
    } catch (Exception e){
            System.out.println("Exception in Client"+e);
        } }



    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String serverAddress = "127.0.0.1";
        int serverPort = 44446;
        Client client = new Client(serverAddress, serverPort);
    }
}
