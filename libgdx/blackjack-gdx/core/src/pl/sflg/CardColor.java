package pl.sflg;

public enum CardColor {
    HEART('♥'), DIAMOND('♦'), CLUB('♣'), SPADE('♠');

    char name;
    CardColor(char name){
        this.name = name;
    }
}
