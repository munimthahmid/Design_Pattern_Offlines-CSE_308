class MyThread implements  Runnable
{
    String name;
    Thread t;

    public  MyThread(String name)
    {
       this.name=name;
       t=new Thread(this,name);
    }

    public void startThread()
    {
        t.start();
    }

    @Override
    public void run() {

        for(int i=5;i>0;i--)
        {
            System.out.println(name+" :"+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

public class MultipleThread {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main Thread starting");
        MyThread ob1=new MyThread("One");
        MyThread ob2=new MyThread("Two");
        MyThread ob3=new MyThread("Three");
        ob1.startThread();
        ob2.startThread();
        ob3.startThread();
        ob1.t.join();
        ob2.t.join();
        ob3.t.join();


        System.out.println("Main Thread ending");
    }

}
