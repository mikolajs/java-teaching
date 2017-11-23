package pl.xxlo;

import java.util.Random;
import java.util.Scanner;


class Player {
    private int money = 1000;
    private int goods = 0;
    
    public int getMoney() { return money; }
    public int getGoods() { return goods; }
    
    public String toString() {
        return String.format("Masz %d $ i %d towaru", money, goods);
    }
    
    public void sale(int price, int amount) {       
        if(amount > 0) {
           int toSale = money / price;
           if(toSale <= amount) {
               goods += toSale;
               money -= toSale*price;
           } else {
               goods += amount;
               money -= amount*price;
           }
        } else {
            amount = Math.abs(amount);
            if(goods >= amount) {
                goods -= amount;
                money += amount*price;
            } else {
                goods = 0;
                money += goods*price;
            }
        }
    }
}

class City {
    String[] names = {"Gdańsku", "Kopenhagdze", "Sztokholmie", "Rydze", "Kaliningradzie",
             "Oslo", "Londynie", "Hamburgu", "Kolonii", "Lubece", "Dunkierce", "Brest",
             "Edynburgu", "Glasgow", "Trondheim", "Bergen", "Nantes", "La Corunie", 
             "Szczecinie", "Sankt Petersburgu"};
    String name;
    final int max = 100;
    final int min = 10;
    private int price = 10;
    private int number = 10;
    Random random = new Random();
    Scanner scanner;
    
    City(){
        scanner = new Scanner(System.in);
    }
    
    protected void finalize() {
        scanner.close();
    }
    
    public void random() {
        price = random.nextInt(max - min) + min;
        number = random.nextInt(max - min) + min;
        name = names[random.nextInt(names.length)];
    }
    
    public String toString() {
        return String.format("Witaj w %s! Cena: %d, ilość: %d", name, price, number);
    }
    
    public void sale(Player player) {
        System.out.println("Kupić (k) czy sprzedać (s)? Wciśnij literę k lub s");
        char action;
        do {
            action = scanner.next().charAt(0);
        } while(!(action == 'k' || action == 's'));
        if(action == 'k') System.out.println("Wybrałeś kupno");
        else System.out.println("Wybrałeś sprzedaż");
        System.out.println("Ile towaru?");
        int amount;
        do {
           amount = scanner.nextInt();
           System.out.println(amount);
        } while(amount < 0);
        if(amount > number) amount = number;
        else if(amount < 0) amount = 0;
        if(action == 's') amount *= -1; 
        player.sale(price, amount);
    }
}

class Game {
    int turn = 1;
    Player player = new Player();
    City city = new City();
    
    public void run() {
        while(true) {
            System.out.println("----------------------------");
            System.out.println("TURA: " + turn);
            System.out.println(player.toString());
            city.random();
            System.out.println(city.toString());
            city.sale(player);
            if(turn == 100) {
                System.out.println("\nKONIEC GRY. Posiadasz  " 
                        + player.getMoney());
                break;
            } 
            turn++;
        }     
    }
}


public class TraderGame {

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }

}
