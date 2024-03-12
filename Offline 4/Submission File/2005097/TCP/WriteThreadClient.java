package TCP;

import java.io.IOException;
import java.util.Scanner;

import DataWrapper.Data;
import util.NetworkUtil;

public class WriteThreadClient implements Runnable {

    private Thread thr;
    private NetworkUtil networkUtil;
    String name;

    public WriteThreadClient(NetworkUtil networkUtil, String name) {
        this.networkUtil = networkUtil;
        this.name = name;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {



            Scanner input = new Scanner(System.in);


            while (true) {

                String line = input.nextLine();
                String []tokens=line.split(" ");

                String command=tokens[0];
                Data data=new Data();

                if(command.equalsIgnoreCase("U"))
                {
                    data.setCommand(command);
                    data.setStockName(tokens[1]);

                    data.setClientName(name);

                }
                else if(command.equalsIgnoreCase("S"))
                {
                    data.setCommand(command);
                    data.setStockName(tokens[1]);

                    data.setClientName(name);
                }
                else
                {
                    data.setCommand(command);
                    data.setClientName(name);
                }

                networkUtil.write(data);
            }



        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



