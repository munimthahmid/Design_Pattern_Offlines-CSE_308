package Client;

import java.util.Scanner;

import TCP.ReadThreadClient;
import TCP.WriteThreadClient;
import util.NetworkUtil;

public class Client {

    String clientName;
    public Client(String serverAddress, int serverPort) {
        try {
            System.out.print("Enter name of the user: ");
            Scanner scanner = new Scanner(System.in);
            clientName = scanner.nextLine();
            NetworkUtil networkUtil = new NetworkUtil(serverAddress, serverPort);
            networkUtil.write(clientName);

            new ReadThreadClient(networkUtil,this);


            new WriteThreadClient(networkUtil, clientName);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update(String message)
    {
        System.out.println(message);
    }

    public String getClientName() {
        return clientName;
    }

    public static void main(String args[]) {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        Client client = new Client(serverAddress, serverPort);
    }
}

