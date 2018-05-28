package eu.brosbit;

import java.util.Scanner;

//import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str;
        str = sc.nextLine();
        Communicator com = new Communicator(new Connector());
        if(!com.isConnected()) {
            System.out.println("NIEUDANE POŁĄCZENIE");
        }
        com.writeString(str);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        
        final long ts = System.currentTimeMillis();
        char c;
        while(System.currentTimeMillis() - ts < 20_000) {
            
            c  = com.readLetter();
            if(c != '\0')
                System.out.print(c);
            try { Thread.sleep(10);} catch (InterruptedException e) {};
        }
        com.release();
    }
}
