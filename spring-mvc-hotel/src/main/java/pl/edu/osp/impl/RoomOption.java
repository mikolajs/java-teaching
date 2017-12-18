package pl.edu.osp.impl;

import pl.edu.osp.api.*;


public class RoomOption implements IRoomOption {
    
    public RoomOption(int nr, String name, int personNumber, int price) {
        this.name = name;
        this.nr = nr;
        this.personNumb = personNumber;
        this.price = price;
    }
    public String getRooms() {
        return String.format("Pokój %s nr %d, ilość osób: %d, cena %d zł", name, nr, personNumb, price);
    }
    
    private int nr = 0;
    private String name = "Nazwa";
    private int personNumb = 0;
    private int price = 50;
    
}
