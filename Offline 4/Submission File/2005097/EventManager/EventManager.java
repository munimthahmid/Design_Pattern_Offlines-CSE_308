package EventManager;

import Client.Client;
import Stock.Stock;
import util.NetworkUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {

    public HashMap<String, List<String>> listeners = new HashMap<>();
    private List<Stock>stocks;


    public EventManager(List<Stock>stocks)
    {

    this.stocks=stocks;
    }



    public void subscribe(String stockName, String clientName)
    {
        stockName=stockName.toLowerCase();
        if (listeners.containsKey(stockName)) {
            List<String> userList = listeners.get(stockName);
            userList.add(clientName);
        } else {
            List<String> newUserList = new ArrayList<>();
            newUserList.add(clientName);
            listeners.put(stockName, newUserList);

        }
        List<String>users=listeners.get(stockName);

    }

    public void unsubscribe(String stockName, String clientName) {
        stockName=stockName.toLowerCase();

        if (listeners.containsKey(stockName)) {
            List<String> userList = listeners.get(stockName);
            userList.remove(clientName);

            if (userList.isEmpty()) {
                listeners.remove(stockName);
            }
        }
    }

    public Map<String, List<String>> getListeners() {
        return listeners;
    }

    public void notify(Stock stock, HashMap<String, NetworkUtil> clientMap) {

    }


}
