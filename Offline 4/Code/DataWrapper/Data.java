package DataWrapper;

import java.io.Serializable;

public class Data implements Serializable {

    private String clientName;
    private String stockName;
    private String command;

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getClientName() {
        return clientName;
    }

    public String getStockName() {
        return stockName;
    }

    public String getCommand() {
        return command;
    }
}

