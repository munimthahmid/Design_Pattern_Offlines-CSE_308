package Stock;

public class Stock {
    private String name;
    private int count;
    private double price;
    public Stock(String name,int count, double price)
    {
        this.name=name;
        this.count=count;
        this.price=price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }
}
