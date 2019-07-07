package pl.sflg;

public class Card {
    final private String number;
    final private char color;
    final private int value;
    public Card(String number, char color, int value){
        this.number = number;
        this.color = color;
        this.value = value;
    }
    @Override
    public String toString(){
        return number + " " + color;
    }

    public String figure() { return  number;}
    public String color() { return String.valueOf(color); }
    public boolean isBlack() {
        switch (color) {
            case 'h':
            case 'd':
                return false;

            default:
                return true;
        }
    }

    public int getValue() {return value;}

}