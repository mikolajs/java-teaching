package eu.brosbit;

import jssc.SerialPort;

public class Main {
	public static void main(String[] args) throws Exception {
		SerialPort serialPort = new SerialPort("/dev/ttyUSB0");
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
