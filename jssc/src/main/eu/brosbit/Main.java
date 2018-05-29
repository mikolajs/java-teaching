package eu.brosbit;

import jssc.SerialPort;

public class Main {
	public static void main(String[] args) throws Exception {
	    String dev = "/dev/ttyUSB";
	    if(args.length > 0) {
	        String[] arr = args[0].split("p");
	        if(arr.length > 1)
	            dev += arr[1];
	        else {
	            System.out.println("Użycie: java - jar plik.jar -p0 \n gdzie 0 numer portu");
	            System.exit(0);
	        }
	    }
	    else {
            System.out.println("Użycie: java - jar plik.jar -p0 \n gdzie 0 numer portu");
            System.exit(0);
        }
		SerialPort serialPort = new SerialPort(dev);
		serialPort.openPort();// Open serial port
		serialPort.setParams(115200, 8, 1, 0);// Set params.
		serialPort.writeByte((byte)3);
		serialPort.writeByte((byte)1);
		serialPort.writeByte((byte)1);
		serialPort.writeByte((byte)3);
		serialPort.writeByte((byte)3);
		serialPort.writeByte((byte)3);
		while (true) {
			byte[] buffer = serialPort.readBytes(10);
			if (buffer != null) {
				for (byte b : buffer) {
					System.out.print(b);
				}
			}
		}
	}
}
