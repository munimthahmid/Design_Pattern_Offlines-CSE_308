package TCP;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Administrator.Administrator;
import EventManager.EventManager;
import Server.Server;
import Stock.Stock;
import jdk.jfr.Event;

public class WriteThreadServer extends Administrator   implements Runnable  {

    private Thread thr;
    public WriteThreadServer(EventManager eventManager, List<Stock>stocks) {
        this.stocks=stocks;
        this.eventManager=eventManager;
        this.thr = new Thread(this);
        thr.start();


    }

    public void run() {
        try {
            Scanner input = new Scanner(System.in);
            while (true) {

                String s=input.nextLine();
                String[] tokens=s.split(" ");
                String command=tokens[0];
                String name=tokens[1].toLowerCase();
               if(command.equalsIgnoreCase("I"))
               {
                   increasePrice(name,Double.parseDouble(tokens[2]));
               }
               else if(command.equalsIgnoreCase("D"))
               {
                  decreasePrice(name,Double.parseDouble(tokens[2]));

               }

               else if(command.equalsIgnoreCase("C"))
               {
                   changeCount(name,Integer.parseInt(tokens[2]));
               }


            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}



