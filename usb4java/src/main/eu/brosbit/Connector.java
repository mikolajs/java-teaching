package eu.brosbit;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.usb.UsbConfiguration;
import javax.usb.UsbDevice;
import javax.usb.UsbDeviceDescriptor;
import javax.usb.UsbHostManager;
import javax.usb.UsbHub;
import javax.usb.UsbInterface;
import javax.usb.UsbInterfacePolicy;
import javax.usb.UsbServices;

public class Connector {

	private UsbServices services;
	private UsbHub rootHub;
	private UsbDevice device;
	private UsbConfiguration configuration;
	private UsbInterface iface;

	private static Logger LOG = Logger.getLogger("CONNECTOR: ");

//	final short VENDOR_ID = 0x10c4; 
//	final short PRODUCT_ID = (short) 0xea60;
	final short VENDOR_ID = 0x403; 
	final short PRODUCT_ID = (short) 0x6001;
	final byte INTERFACE = 0;
	private boolean connected = false;

	public Connector() {
		try {
			services = UsbHostManager.getUsbServices();
			rootHub = services.getRootUsbHub();
			device = findDevice(rootHub);
			System.out.println(device.getUsbDeviceDescriptor().toString());
			configuration = device.getActiveUsbConfiguration();
			iface = configuration.getUsbInterface(INTERFACE);
			System.out.println("iface active " + iface.isActive());
			iface.claim(new UsbInterfacePolicy() {
				@Override
				public boolean forceClaim(UsbInterface usbInterface) {
					return true;
				}
			});
			connected = true;
		} catch (Exception e) {
			LOG.log(Level.INFO, "Connection problem", e);
			connected = false;
		}

	}

	public boolean isConnected() {
		return connected;
	}

	public void printDevice() {
		LOG.log(Level.INFO, device.getUsbDeviceDescriptor().toString());
	}

	public UsbInterface getInterface() {
		return this.iface;
	}

	@SuppressWarnings("unchecked")
	private UsbDevice findDevice(UsbHub rootHub) {
		for (UsbDevice device : (List<UsbDevice>) rootHub.getAttachedUsbDevices()) {
			UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
			if (desc.idVendor() == VENDOR_ID && desc.idProduct() == PRODUCT_ID)
				return device;
			if (device.isUsbHub()) {
				device = findDevice((UsbHub) device);
				if (device != null)
					return device;
			}
		}
		return null;
	}

	public void release() {
		try {
			if (iface != null)
				iface.release();
		} catch (Exception e) {
			System.out.println("Exception in release interface");
		} finally {
			connected = false;
		}

	}
}
