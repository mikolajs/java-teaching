package eu.brosbit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class SendFile {
	private static Logger LOG = Logger.getLogger("SendFile");
    private byte[] fileData;
    private byte p = 0;
    
    
    public SendFile() {
    	loadFile();
    }
    
    
    public byte[] getFullSize() {
        int s = 0;
        int tmp;
        int l = fileData.length;
        while(l > 0) {
            l /= 10;
            s++;
        }
        l = fileData.length;
        byte[] dataSize = new byte[s+2];
        for(int i = dataSize.length - 1; i >= 0; i--) {
            tmp = l % 10;
            l /= 10;
            dataSize[i] = (byte) tmp;
           // System.out.print(" " + (byte) tmp );
        }
        dataSize[dataSize.length-1] = 0;
        dataSize[dataSize.length-2] = 65;
        return dataSize;
    }
    
    public byte[] sendFirstBunch() {
        p++;
        byte[] sendData;
        int size =  255;
        if(fileData.length < 255) {
            size =  fileData.length;
        }
        sendData = new byte[size + 3];
        sendData[0] = p;
        sendData[1] = countControlSum();
        sendData[2] = (byte) size;
        for(int i = 0; i < size; i++) {
        	sendData[i+3] = fileData[i];
        }
        
        return sendData;
    }

    private void loadFile() {
        try {
            fileData = Files.readAllBytes(Paths.get("/home/ms/cypherlabs.cfg"));
            LOG.info("File data size: " + fileData.length);
        } catch (IOException e) {
            System.out.println("Config file not FOUND!");
        } finally {
            p = 0;
        }

    }
    
    private byte countControlSum() {
        byte size = (byte) 255;
        byte crc = (byte) 0xFF;
        if(fileData.length < 255) {
            size = (byte) fileData.length;
        }
        for(int i = 0; i < size; i++) {
            crc ^= fileData[i];
        }
        return crc;
    }
    
}
