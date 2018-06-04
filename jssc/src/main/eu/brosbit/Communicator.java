package eu.brosbit;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import jssc.SerialPort;
import jssc.SerialPortException;

public class Communicator {
    private static Logger LOG = Logger.getLogger("COMMUNICATOR");
    final byte ETX = 0x03;
    final byte EOT = 0x04;
    final byte ENQ = 0x05;
    final byte ACK = 0x06;
    final byte CUT = 0x07;
    final byte TLF = 0x08;
    final byte ABT = 0x09;
    final byte RST = 0x0A; // rozpocznij transmisje od nowa (bo np. zbyt dużo pakietów o złych numerach)
    final byte BAD = 0x0B; // zły format pliku (np. zła długość)
    final byte IRD = 0x0C; // transmsisja przez IrD
    final byte NAK = 0x15;
    final byte ANY = 0x5A;
    private String device = "/dev/ttyUSB";
    final private SerialPort serialPort;
    Queue<Byte> input;

    public Communicator(int port) throws SerialPortException {
        if (port > -1)
            device += port;
        else
            throw new SerialPortException("No port", device, "");
        serialPort = new SerialPort(device);
        serialPort.openPort();// Open serial port
        serialPort.setParams(SerialPort.BAUDRATE_115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                SerialPort.PARITY_EVEN);
        input = new LinkedList<Byte>();
    }

    public Communicator(int port, int baund, int dataBits, int stopBits, int parity) throws SerialPortException {
        this(port);
        setParams(baund, dataBits, stopBits, parity);
    }

    public void sendStartSeq() {
        write(new byte[] {5, 4, 5});
        LOG.log(Level.INFO, String.valueOf(readByte()));
    }

    private byte[] read(int bytes) {
        byte[] data = null;
        try {
            data = serialPort.readBytes(bytes);
        } catch (SerialPortException e) {
            LOG.log(Level.WARNING, "Can read serial");
        }
        return data;
    }
    
    private byte readByte() {
        byte b = read(1)[0];
        return b;
    }
    
    private void write(byte[] data) {
        try {
        serialPort.writeBytes(data);
        } catch (SerialPortException e) {
            LOG.log(Level.WARNING, "Can write to serial");
        }
    }
    
    private void writeByte(byte b) {
        write(new byte[] {b});
    }

    private void setParams(int baund, int dataBits, int stopBits, int parity) throws SerialPortException {
        serialPort.setParams(baund, dataBits, stopBits, parity);
    }

    public void release() {
        try {
            serialPort.closePort();
        } catch (SerialPortException e) {
            LOG.log(Level.WARNING, "Cant close port!");
        }
    }
    
    public  void testConnection() {
        for(byte i = 0; i < 127; i++) {
            writeByte(i);
            try { Thread.sleep(5); } catch (InterruptedException e) {
            }
        }
        try { Thread.sleep(4000); } catch (InterruptedException e) {}
        LOG.log(Level.INFO, new String(read(1)));
    }
}
