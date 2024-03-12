package Administrator;

import java.util.ArrayList;
import java.util.List;

public class Administrator {

    public List<String>clients;
    int counter;
    public Administrator()
    {
        clients=new ArrayList<>();
        counter=0;
    }
    public void addClient(String name)
    {
        clients.add(name);

    }
    public int getCounter()
    {
        return counter;
    }
    public void incrementCounter()
    {
        counter++;
    }

    public void setCounter(int i) {
        counter=0;
    }
}
