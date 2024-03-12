public class MainThread {


    public static void main(String[] args) {
        Thread t=Thread.currentThread();

        System.out.println("current thread name: "+t.getName());

        t.setName("Munim's Thread");
        System.out.println("After changing name: "+t.getName());


        for(int i=0;i<5;i++)
        {
            System.out.println(i+1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        for(int i=0;i<5;i++)
        {
            System.out.println((i+1)*10);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
