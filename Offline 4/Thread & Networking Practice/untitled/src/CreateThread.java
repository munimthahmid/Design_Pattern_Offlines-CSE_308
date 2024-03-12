class NewThread2
{

    void hello()  {
        for(int i=0;i<5;i++)
        {
            System.out.println("Hello");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

public class CreateThread {
    public static void main(String[] args) throws InterruptedException {
        int i=0;
        NewThread2 ct=new NewThread2();
        new Thread(ct::hello).start();
        while(true)
        {
            System.out.println(i++);
            Thread.sleep(1000);
        }
    }
}
