package eu.brosbit;

import jssc.SerialPortException;

public class Main {
    
    
    
    
	public static void main(String[] args) throws Exception {
	    int port = -1;
	    Communicator com = null;
	    if(args.length > 0) {
	        String[] arr = args[0].split("p");
	        //System.out.println(args[0]);
	        if(arr.length > 1) {
	            try {
	                port = Integer.parseInt(arr[1].trim());
	            } catch(NumberFormatException e) {
	                System.out.println("Błędny port. Podaj parametr -p0 gdzie 0 numer portu");
	                System.exit(0);
	            }
	        }
	        else {
	            System.out.println("Użycie: java - jar plik.jar -p0 \n gdzie 0 numer portu");
	            System.exit(0);
	        }
	    }
	    else {
            System.out.println("Użycie: java - jar plik.jar -p0 \n gdzie 0 numer portu");
            System.exit(0);
        }
	    System.out.println("PORT " + port);
	    
		try {
		    com = new Communicator(port);
		    com.testConnection();
//		    com.sendStartSeq();
		    
		    
		} catch (SerialPortException e) {
		    System.out.println("Błąd komunicacji: " + e.getMessage());
		} finally {
		   if(com != null) com.release();
		}
		
	}
}
