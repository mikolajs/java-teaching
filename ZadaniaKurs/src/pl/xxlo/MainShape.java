package pl.xxlo;


interface Shape {
    double area();
    double circuit();
}

class Rectangle implements Shape {
    private double a;
    private double b;
    public Rectangle(double a, double b){
        this.a = a;
        this.b = b;
    }
    public double area() {
        return a*b;
    }
    public double circuit() {
        return 2*a+2*b;
    }
    public String toString() {
        return "Prostokąt";
    }
}


class Square extends Rectangle {
    public Square(double a) {
        super(a, a);
    }
    public String toString() {
        return "Kwadrat";
    }
}


class Circle implements Shape {
    private double r;
    public Circle(double r) {
        this.r = r;
    }
    public double circuit() {
        return 2*Math.PI*r;
    }
    public double area() {
        return Math.PI*r*r;
    }
    public String toString() {
        return "Koło";
    }
}

public class MainShape {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[3];
        shapes[0] = new Rectangle(23.4, 3.5);
        shapes[1] = new Square(45.5);
        shapes[2] = new Circle(34.4);
        for(Shape s : shapes) {
            System.out.println(s);
            System.out.println(s.area());
            System.out.println(s.circuit());
        }
    }
}
    
    
