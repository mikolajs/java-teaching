package pl.edu.osp.impl;

import java.util.ArrayList;

import pl.edu.osp.api.IRoom;

public class Room implements IRoom {
    private ArrayList<RoomOption> options;
    private int nr = 0;
    private String name = "Nazwa";
    
    public Room(int nr, String name, ArrayList<RoomOption> options) {
        this.options = options;
        this.nr = 0;
        this.name = "Nazwa";
    }
    
    public String getRoomOptions() {
        StringBuilder info = new StringBuilder();
        for(RoomOption ro : options) {
            info.append(ro.getRooms()); 
        }
        return String.format("Pokój %s nr: %d, Możliwe konfiguracje pokoju: %d \n %s", 
        		name, nr, options.size(), info.toString());
    }

}
