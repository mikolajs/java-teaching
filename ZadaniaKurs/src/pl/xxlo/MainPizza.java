package pl.xxlo;

import java.util.Scanner;
/*Kupując pizzę w różnych pizzeriach możemy natrafić 
 * na możliwość zakupu 2 mniejszych pizz lub 
 * jednej większej z tymi samymi składnikami, 
 * w różnych cenach. Możemy mieć również taką sytuację, 
 * że w jednej pizzerii mamy okrągłą pizzę, 
 * a w drugiej kwadratową lub prostokątną. 
 * Często zastanawiamy się, którą bardziej opłaca się kupić.
Napisz program, który pomoże rozwiązać ten problem. 
Użytkownik Twojego programu będzie wpisywał:
kształt pierwszej pizzy, ilość pizz, wymiary,
kształt drugiej pizzy, ilość pizz, wymiary
A Ty wpiszesz mu ile wynosi koszt na cm² 
i która jest bardziej opłacalna.*/
class PizzaWorker {
    Scanner sc;
    public PizzaWorker() {
        sc = new Scanner(System.in);
    }
    
    private int getShape() {
        while(true) {
            System.out.println("Podaj kształt okrągła (1)"+
                    " kwadratowa (2), prostokątna (3)");
            char shape = sc.next().trim().charAt(0);
            if(shape == '1' || shape == '2' || shape == '3') 
                return (int) (shape - 49);
            else System.out.println("Wpisz 1, 2, lub 3"); 
        } 
    }
    
    private double getPriceOnArea() {
        double area = getDimAndArea(getShape());
        double price = getPrice();
        return price / area;
    }
    
    private double getPrice() {
        System.out.println("Podaj cenę w zł i groszach");
        double price = sc.nextDouble();
        return price;
    }
    
    private double getDimAndArea(int object) {
        Shape shape;
        int a, b, r;
        switch(object) {
        case 0: 
            System.out.println("Podaj średnicę w pełnych cm");
            r = sc.nextInt();
            shape = new Circle(r);
            break;
        case 1:
            System.out.println("Podaj bok w pełnych cm");
            a = sc.nextInt();
            shape = new Square(a);
            break;
        case 2:
        default:
          System.out.println("Podaj bok a w pełnych cm");
          a = sc.nextInt();
          System.out.println("Podaj bok b w pełnych cm");
          b = sc.nextInt();
          shape = new Rectangle(a, b);
          break;
        }
        return shape.area();
    }
    
    public void getData() {
        System.out.println("Pierwsza opcja pizzy.");
        double priceOnArea1 = getPriceOnArea();
        System.out.println("Druga opcja pizzy.");
        double priceOnArea2 = getPriceOnArea();
        System.out.format("Koszt pierwszej opcji: %0.2f zł na cm²," +
                " koszt drugiej opcji: %0.2f na cm²",
                priceOnArea1, priceOnArea2);
        if(priceOnArea1 > priceOnArea2) 
            System.out.println("Druga opcja jest bardziej opłacalna");
        else 
            System.out.println("Pierwsza opcja jest bardziej opłacalna");
            
    }
    
    @Override
    protected void finalize() {
        sc.close();
    }
}

public class MainPizza {
   
    
    public static void main(String[] args) {
        System.out.println("Pierwszy rodzaj pizzy:");
        PizzaWorker pw = new PizzaWorker();
        pw.getData();
        
    }
}
