package tcpdiff;

import java.util.HashMap;
import java.util.Scanner;

import Administrator.Administrator;
import util.NetworkUtil;

public class WriteThreadServer implements Runnable {

    private Thread thr;
    String name;
    public HashMap<String, NetworkUtil> clientMap;
    boolean notify;
    Administrator administrator;

    public WriteThreadServer(HashMap<String, NetworkUtil> map, String name,boolean notify,Administrator administrator) {
        this.clientMap = map;
        this.name = name;
        this.thr = new Thread(this);
        thr.start();
        this.notify=notify;
        this.administrator=administrator;
    }

    public void run() {
        try {
            Scanner input = new Scanner(System.in);
            
            if(notify==false)
            {
                while (true) {





                    System.out.println("Enter the name of the client to send, a message to send: ");
                    String[] s = input.nextLine().split(",");
                    NetworkUtil networkUtil = clientMap.get(s[0]);
                    if (networkUtil != null) {
                        networkUtil.write(name + ":" + s[1]);
                    }





                }
            }
            else
            {
                while (true) {

                    System.out.println("inside true whileloop");
                    System.out.println(administrator.clients.size());
                    String s=input.nextLine();



                    System.out.println("Enter the name of the client to send, a message to send: ");
                    NetworkUtil networkUtil = null;
                   
                    for(int i=0;i<administrator.clients.size();i++)
                    {
                       networkUtil=  clientMap.get(administrator.clients.get(i));
                        if (networkUtil != null) {
                            networkUtil.write(s);
                        }
                    }

                    





                }
            }
         
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}



