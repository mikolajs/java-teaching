package eu.brosbit;

public class Main
{ 
    public static void main(String[] args) {
        Connector con = new Connector();
        //con.printInfoDevice();
        con.printDevices();
        con.destroy();
    }   
}

