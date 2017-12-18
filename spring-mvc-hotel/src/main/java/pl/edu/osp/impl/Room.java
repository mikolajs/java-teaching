package pl.edu.osp.impl;

import java.util.ArrayList;

import pl.edu.osp.api.IRoom;

public class Room implements IRoom {
    private ArrayList<RoomOption> rooms;
    
    public Room(ArrayList<RoomOption> rooms) {
        this.rooms = rooms;
    }
    
    public String getRoomOptions() {
        StringBuilder info = new StringBuilder();
        for(RoomOption ro : rooms) {
            info.append(ro.getRooms()); 
        }
        return String.format("Możliwe konfiguracje pokoju: %d \n %s", rooms.size(), info.toString());
    }

}
