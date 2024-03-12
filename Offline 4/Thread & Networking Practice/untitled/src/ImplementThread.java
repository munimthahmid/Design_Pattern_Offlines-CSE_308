class NewThread implements  Runnable
{

    @Override
    public void run() {
        for(int i=0;i<10;i++)
        {
            System.out.println(i+1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    void hello()
    {
        System.out.println("Hello");
        System.out.println("Hello");
        System.out.println("Hello");
        System.out.println("Hello");
        System.out.println("Hello");
    }
}

public class ImplementThread {
    public static void main(String[] args) throws InterruptedException {
        Runnable r=new NewThread();
        Thread t=new Thread(r);
        t.start();
        int i=0;
        while(true)
        {
            System.out.println(i++);
            Thread.sleep(1000);
        }
    }
}
