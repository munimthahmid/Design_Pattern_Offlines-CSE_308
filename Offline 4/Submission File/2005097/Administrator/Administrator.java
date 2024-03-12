package Administrator;

import Client.Client;
import EventManager.EventManager;
import Stock.Stock;
import util.NetworkUtil;

import java.io.IOException;
import java.util.*;

public class Administrator {
    public HashMap<String, NetworkUtil> clientMap; // HashMap of client's name and socket information
    protected List<Stock> stocks;

    protected   EventManager eventManager;


    public Administrator()
    {
        clientMap=new HashMap<>();
        stocks=new ArrayList<>();
        eventManager=new EventManager(stocks);
    }


    public void increasePrice(String name, double amount) {


        for (Stock stock : stocks) {
            if (stock.getName().equalsIgnoreCase(name)) {

                stock.setPrice(stock.getPrice() + amount);

                notify(name,"Price of "+name+" increased to " + stock.getPrice());
            }
        }
    }

    public void decreasePrice(String name, double amount) {
        for (Stock stock : stocks) {
            if (stock.getName().equalsIgnoreCase(name)) {
                stock.setPrice(stock.getPrice() - amount);

                notify(name,"Price of "+name+" decreased to " + stock.getPrice());
            }
        }

    }
    public void addStock(Stock s)
    {
        stocks.add(s);
    }

    public void changeCount(String name,int count)
    {
        name=name.toLowerCase();

        for (Stock stock : stocks) {
            if (stock.getName().equalsIgnoreCase(name)) {
                stock.setCount(count);
                notify(name,"Count of "+name+" changed to "+stock.getCount());
            }
        }
    }

    public List<Stock> showStocks(String clientName)
    {
        List<Stock> tempStocks= new ArrayList<>();
        NetworkUtil networkUtil=clientMap.get(clientName);
        int counter=1;
        for(int i=0;i<stocks.size();i++)
        {

            List<String> clients=eventManager.listeners.get(stocks.get(i).getName());


            if (clients != null && clients.contains(clientName)) {
                try {

                    networkUtil.write(counter++ +". "+ stocks.get(i).getName());
                    tempStocks.add(stocks.get(i));


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return tempStocks;
    }


    public EventManager getEventManager() {
        return eventManager;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void notify(String stockName, String message)
    {
        List<String> temp=eventManager.listeners.get(stockName);
        if(temp!=null)
        {
            for(int i = 0; i< temp.size(); i++)
            {
                NetworkUtil networkUtil=clientMap.get(temp.get(i));
                try {

                    networkUtil.write(message);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }
}
