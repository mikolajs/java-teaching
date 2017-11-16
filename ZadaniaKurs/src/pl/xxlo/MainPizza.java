package pl.xxlo;



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
