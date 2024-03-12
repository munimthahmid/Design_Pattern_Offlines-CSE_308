package tcpdiff;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import Administrator.Administrator;
import util.NetworkUtil;

public class Server {

    private ServerSocket serverSocket;
    private Administrator administrator;
    public HashMap<String, NetworkUtil> clientMap; // HashMap of client's name and socket information

    Server() {
        clientMap = new HashMap<>();
        administrator=new Administrator();
        try {
            serverSocket = new ServerSocket(33333);
            //only one write thread of server
            new WriteThreadServer(clientMap, "Server",false,administrator);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);


        //read the name and put into hashmap
        String clientName = (String) networkUtil.read();
        administrator.addClient(clientName);
        administrator.incrementCounter();
        clientMap.put(clientName, networkUtil);

        if(administrator.getCounter()>3)
        {
            new WriteThreadServer(clientMap,"Server",true,administrator);
            administrator.setCounter(0);
            System.out.println("3 crossed");
        }

        //read the message
        new ReadThread(networkUtil);
    }

    public static void main(String args[]) {
        Server server = new Server();
    }
}
