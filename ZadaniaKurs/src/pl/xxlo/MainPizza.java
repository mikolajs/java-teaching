package pl.xxlo;

import java.util.Scanner;

class PizzaWorker {
    Scanner sc;
    
    public PizzaWorker() {
        sc = new Scanner(System.in);
    }
    
    public void getData() {
        System.out.println("Pierwsza opcja pizzy.");
        System.out.println("Podaj kształt okrągła (1)"+
        " kwadratowa (2), prostokątna (3)");
        sc.next().trim().charAt(0);
        
    }
    
    @Override
    protected void finalize() {
        sc.close();
    }
}

public class MainPizza {
   
    public static Shape getShape() {
       
        return new Circle(1);
    }
    
    public static void main(String[] args) {
        System.out.println("Pierwszy rodzaj pizzy:");
        Shape sh1 = getShape();
        Shape sh2 = getShape();
    }
}
