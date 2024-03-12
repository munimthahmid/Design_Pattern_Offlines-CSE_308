package TCP;

import Administrator.Administrator;
import DataWrapper.Data;
import EventManager.EventManager;
import Server.Server;
import Stock.Stock;
import util.NetworkUtil;

import java.io.IOException;

public class ReadThreadServer extends Administrator implements Runnable {
    private Thread thr;
    private NetworkUtil networkUtil;
    private Administrator administrator;

    public ReadThreadServer(NetworkUtil networkUtil,Administrator administrator) {
        this.networkUtil = networkUtil;
        this.administrator=administrator;
        this.thr = new Thread(this);
        thr.start();
    }


    public void run() {
        try {
            while (true) {




                Object o=networkUtil.read();

                if(o instanceof String)
                {
                    String s = (String) o;
//                eventManager.subscribe();
                    System.out.println(s);
                }
                else if(o instanceof Data)
                {
                    Data data=(Data) o;

                    if(data.getCommand().equalsIgnoreCase("S"))
                    {
                        administrator.getEventManager().subscribe(data.getStockName().toLowerCase(),data.getClientName());

                    }
                    else if(data.getCommand().equalsIgnoreCase("U"))
                    {
                       this.administrator.getEventManager().unsubscribe(data.getStockName().toLowerCase(),data.getClientName());

                    }
                    else if(data.getCommand().equalsIgnoreCase("V"))
                    {
                           administrator.showStocks(data.getClientName());
                    }








                }






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



