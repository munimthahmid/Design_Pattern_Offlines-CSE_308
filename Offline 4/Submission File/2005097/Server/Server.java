package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import Administrator.Administrator;
import EventManager.EventManager;
import Stock.Stock;
import TCP.ReadThreadServer;
import TCP.WriteThreadServer;
import util.NetworkUtil;

public class Server  {

    private ServerSocket serverSocket;
    private Administrator administrator;


    public Server() {
        administrator=new Administrator();


        String fileName = "init_stocks.txt";

        try {
            List<String> lines = readLinesFromFile(fileName);

            for (String line : lines) {
                String[] parts = line.split(" ");

                Stock p = new Stock(parts[0].toLowerCase(), Integer.parseInt(parts[1]), Double.parseDouble(parts[2]));
                administrator.addStock(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Thread inputThread = new Thread(this::getServerInput);
        inputThread.start();

        try {
            serverSocket = new ServerSocket(33333);


            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);

            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void getServerInput() {
        Scanner input = new Scanner(System.in);

        while (true) {
            String s = input.nextLine();
            String[] tokens = s.split(" ");
            String command = tokens[0];
            String name = tokens[1];

            if (command.equalsIgnoreCase("I")) {
                administrator.increasePrice(name, Double.parseDouble(tokens[2]));
            } else if (command.equalsIgnoreCase("D")) {
                administrator.decreasePrice(name, Double.parseDouble(tokens[2]));
            } else if (command.equalsIgnoreCase("C")) {
                administrator.changeCount(name, Integer.parseInt(tokens[2]));
            }
        }
    }


    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);

        String clientName = (String) networkUtil.read();
        show(networkUtil);
        administrator.clientMap.put(clientName, networkUtil);

        new ReadThreadServer(networkUtil, administrator);
    }

    public void show(NetworkUtil networkUtil) {
        try {
            networkUtil.write("Name      Count         Price");
            for (Stock stock : administrator.getStocks()) {
                String name = stock.getName();
                int count = stock.getCount();
                double price = stock.getPrice();


                networkUtil.write(name + "         " + count + "           " + price);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> readLinesFromFile(String fileName) {
        try {
            Path path = Paths.get(fileName);
            return Files.readAllLines(path);
        } catch (Exception ex) {
            System.out.println("Exception Found");
        }

        return null;
    }

    public static void main(String[] args) {
        Server server = new Server();
    }
}
