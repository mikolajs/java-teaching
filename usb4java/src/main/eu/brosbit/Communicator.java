package eu.brosbit;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.usb.UsbEndpoint;
import javax.usb.UsbException;
import javax.usb.UsbInterface;
import javax.usb.UsbPipe;
import javax.usb.UsbPlatformException;

import org.usb4java.BufferUtils;
import org.usb4java.Device;
import org.usb4java.DeviceHandle;
import org.usb4java.LibUsb;
import org.usb4java.LibUsbException;

public class Communicator {

    private static Logger LOG = Logger.getLogger("COMMUNICATOR ");
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

    final byte OUT_ENDPOINT = 0x02;// (byte) 0x01;
    final byte IN_ENDPOINT = (byte) 0x81; // 0x82;

    final Connector connector;
    final UsbInterface iface;
    final private int TIMEOUT = 5000;

    public Communicator(Connector connector) {
        this.connector = connector;
        this.iface = connector.getInterface();
        LOG.log(Level.INFO, "STARTING COMMUNICATOR ");
    }

    public void sendFile() {

    }

    public void reciveFile() {
        byte indexPackage = 0;
        byte[] data;
        do {
            data = readPackage(indexPackage++);
        } while (data.length > 0);
    }

    private byte[] readPackage(byte nr) {
        byte[] head = read(3);
        if (head.length < 3)
            return null;
        LOG.log(Level.INFO, "INITIALIZE: Header 1 = " + head[0]);
        LOG.log(Level.INFO, "INITIALIZE: header 2 = " + head[1]);
        LOG.log(Level.INFO, "INITIALIZE: header 3 = " + head[2]);
        byte[] body = null;
        if (head[0] != (byte) 0 && head[1] != -1) {
            body = read(head[0]);
            if (checkControlSum(body, head[1])) {
                LOG.log(Level.INFO, "Checksum is OK");
                write(new byte[] { ACK });
            } else
                LOG.log(Level.INFO, "Checksum is WRONG!");
            if (nr == head[2])
                LOG.log(Level.INFO, "Package number is OK");
            else
                LOG.log(Level.INFO, "Package number is WRONG!");
            LOG.log(Level.INFO, new String(body));
        }
        return body;
    }

    public void write(byte[] data)  {
        try {
            UsbEndpoint endpoint1 = iface.getUsbEndpoint(OUT_ENDPOINT);
            UsbPipe pipe1 = endpoint1.getUsbPipe();
            pipe1.open();
            try {
                int sent = pipe1.syncSubmit(data);
                System.out.println(sent + " bytes sent");
            } finally {
                pipe1.close();
            }
        } catch (UsbException e) {
            LOG.log(Level.INFO, "USB EXCEPTION while write ", e);
        }
    }

    public byte[] read(int numb) {
        byte[] data = null;
        try {
            UsbEndpoint endpoint = this.iface.getUsbEndpoint(IN_ENDPOINT);
            UsbPipe pipe = endpoint.getUsbPipe();
            pipe.open();
            try {
                data = new byte[numb];
                int received = pipe.syncSubmit(data);
                LOG.log(Level.INFO, received + " bytes received " + new String(data, Charset.forName("utf-8")));
            } catch (UsbPlatformException e) {
               // LOG.log(Level.WARNING, "Overflow");
            } finally {
                pipe.close();
            }
        } catch (UsbException e) {
            LOG.log(Level.INFO, "USB EXCEPTION while read ", e);
        }
        return data;
    }
    
    public void release() {
        this.connector.release();
    }
    
    public void writeString(String str) {
        write(str.getBytes());
    }
    
    public char readLetter() {
        byte[] data = read(1);
        if(data != null)
            return (char) data[0];
        return '\0';
    }
    
    public boolean isConnected() { return connector.isConnected(); }

    private boolean checkControlSum(byte[] array, byte pattern) {
        byte sum = (byte) 0xFF;
        for (byte b : array) {
            sum ^= b;
        }
        return pattern == sum;
    }

   

}
