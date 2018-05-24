package eu.brosbit;

import org.usb4java.Context;
import org.usb4java.Device;
import org.usb4java.DeviceDescriptor;
import org.usb4java.DeviceHandle;
import org.usb4java.DeviceList;
import org.usb4java.LibUsb;
import org.usb4java.LibUsbException;

public class Connector {
    
    private Context context;
    private Device device = null;
    private DeviceHandle handle = null;
    final short vendor = 0x403;
    final short product = 0x6001;
    
    public Connector() {
        init();
        lookForDevice();
        handleDevice();
    }
   
    
    public void init() {
        context = new Context();
        int result = LibUsb.init(context);
        if (result < 0)
        {
            throw new LibUsbException("Unable to initialize libusb", result);
        }
    }
    
    public void printDevices() {
        init();

        // Read the USB device list
        DeviceList list = new DeviceList();
        int result = LibUsb.getDeviceList(context, list);
        if (result < 0) {
            throw new LibUsbException("Unable to get device list", result);
        }
        try
        {
            // Iterate over all devices and list them
            for (Device device: list)
            {
               printInfoDevice(device);
              
            }
        }
        finally
        {
            // Ensure the allocated device list is freed
            LibUsb.freeDeviceList(list, true);
        }
    }
    
    public void printInfoDevice(Device device) {
        int address = LibUsb.getDeviceAddress(device);
        int busNumber = LibUsb.getBusNumber(device);
        DeviceDescriptor descriptor = new DeviceDescriptor();
        int result = LibUsb.getDeviceDescriptor(device, descriptor);
        if (result < 0)
        {
            throw new LibUsbException(
                "Unable to read device descriptor", result);
        }
        
        System.out.format(
                "Bus %03d, Device %03d: Vendor %04x, Product %04x%n --------------\n  Info %s \n", 
                busNumber, address, descriptor.idVendor(), 
                descriptor.idProduct(), descriptor.toString());
    }
    
    public void printInfoDevice() {
        printInfoDevice(device);
    }
    
    public void printHandleInfoDevice() {
        if(handle != null) {
            String info = LibUsb.getStringDescriptor(handle, (byte) 200);
            System.out.format("Extra Info from handle %s \n", info);
        } else {
            System.out.println("Handle is NULL!");
        }
    }
    
    
    public void lookForDevice() {
        Context context = new Context();
        
        int result = LibUsb.init(context);
        if(result < 0) {
            throw new LibUsbException("Cannot connect to USB Lib", result);
        }
        DeviceList devList = new DeviceList();
        result = LibUsb.getDeviceList(context, devList);
        if(result < 0) {
            throw new LibUsbException("No devices list", result);
        }
        for(Device device : devList) {
            DeviceDescriptor desc = new DeviceDescriptor();
            result = LibUsb.getDeviceDescriptor(device, desc);
            if(result < 0) {
                throw new LibUsbException("Not open descriptor", result);
            }
            if(desc.idProduct() == product && desc.idVendor() == vendor) {
                this.device = device;
                    
                return;
            }
        }   
    }
    
    
    public void handleDevice() {
        handle = new DeviceHandle();
        int result = LibUsb.open(device, handle);      
        if(result != LibUsb.SUCCESS ) {
            System.out.println("Unable handle device");
        }
    }
    
    public void destroy() {
        // Deinitialize the libusb context
        LibUsb.close(handle);
        LibUsb.exit(context);
    }
}
