package pl.edu.osp.impl;

import pl.edu.osp.api.*;


public class RoomOption implements IRoomOption {
    
    public RoomOption(int personNumber, int price) {
      ;
        this.personNumb = personNumber;
        this.price = price;
    }
    public String getRooms() {
        return String.format("ilość osób: %d, cena %d zł\n", personNumb, price);
    }
    
   
    private int personNumb = 0;
    private int price = 50;
    
}
