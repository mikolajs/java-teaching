package eu.brosbit;

import org.usb4java.Context;
import org.usb4java.Device;
import org.usb4java.DeviceHandle;
import org.usb4java.LibUsb;
import org.usb4java.LibUsbException;

public class Exploration {

    DeviceHandle h;
    short VENDOR_ID = 0x046d;
    short PRODUCT_ID = (short) 0xc52e;

    public void testHandle() {
        Context con = new Context();
        int result = LibUsb.init(con);
        if (result < 0)
            throw new LibUsbException("Can't init", result);
        final DeviceHandle handle = LibUsb.openDeviceWithVidPid(null, VENDOR_ID, PRODUCT_ID);
        if(handle != null) {
            String info = LibUsb.getStringDescriptor(handle, (byte) 200);
            System.out.format("Extra Info from handle %s \n", info);
        } else {
            System.out.println("Handle is NULL!");
        }
        LibUsb.close(handle);
        LibUsb.exit(con);
    }

}
