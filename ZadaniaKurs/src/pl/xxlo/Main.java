package pl.xxlo;

class Box {
    private double width;
    private double height;
    private double depth;

    public Box() {
        this(1, 1, 1);
    }

    public Box(double width, double height, double depth) {
        setDim(width, height, depth);
    }

    private void setDim(double w, double h, double d) {
        width = w;
        height = h;
        depth = d;
    }

    public double volume() {
        return width*height*depth;
    }

    public double filling(double vol) {
        return vol / volume();
    }

    public String toString() {
        return "Box(" + width + " × " + height  + " × "  + depth + ")";
    }
}

public class Main {

    public static void main(String[] args) {
        Box box1 = new Box(4.5, 6.7, 2.3);
        System.out.printf("Pudełko %s\n", box1.toString());
        System.out.printf("Objętość pudełka %.2f \n", box1.volume());
        System.out.printf(" %.2f pudełka zostanie wypełniona przez objętość  %.2f \n", 
                box1.filling(41.8), 41.8);

    }
}