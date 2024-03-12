package TCP;

import Administrator.Administrator;
import Client.Client;
import util.NetworkUtil;

import java.io.IOException;

public class ReadThreadClient extends Administrator implements Runnable {
    private Thread thr;
    private NetworkUtil networkUtil;
    private Client client;

    public ReadThreadClient(NetworkUtil networkUtil,Client client) {
        this.networkUtil = networkUtil;
        this.client=client;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {





                String s = (String) networkUtil.read();
                client.update(s);
                

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



