class Circle {
    double r;

    public Circle(double R) {
        r = R;
    }
    
    public double area() {
        return Math.PI * r * r;
    }
    
    public double circuit() {
        return 2.0 * Math.PI * r;
    }

}

public class Main {

     public static void main(String[] args) {
        final double cena32 = 14.90;
        final double cena65 = 34.90;
        Circle c32 = new Circle(32);
        Circle c65 = new Circle(65);
        if(cena32/c32.area() < cena65/c65.area()) {
            System.out.format("Najbardziej opłacalna jest pizza mała\n");
        } 
        else {
            System.out.format("Najbardziej opłacalna jest pizza duża\n");
        }
        System.out.format("Mała %.2f duża %.2f zł na 1000 cm²\n", 
                cena32/c32.area()*1000.0,
                cena65/c65.area()*1000.0);
    }
}