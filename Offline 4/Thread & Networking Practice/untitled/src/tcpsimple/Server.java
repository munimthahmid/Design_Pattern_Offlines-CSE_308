package tcpsimple;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server  {
    private ServerSocket serverSocket;
    int clientCount=0;

    Server()  {
        try {
            serverSocket=new ServerSocket(44446);
            System.out.println("Server is waiting");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Server accepts a client ... ");
                serve(clientSocket);

            }


        } catch (Exception e) {
            System.out.println("Server throws exception: "+e);
        }


    }

    private void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        clientCount++;

        ObjectOutputStream oos=new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream ois=new ObjectInputStream(clientSocket.getInputStream());
        System.out.println(ois.readObject()+" "+clientCount);
        oos.writeObject("Hello Client "+clientCount);


    }

    public static void main(String args[]) {
        Server server = new Server();
    }
}
