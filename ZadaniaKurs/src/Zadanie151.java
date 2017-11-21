import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Point2D {
    protected double x, y;
    
    public Point2D() {
        x = 0.0; y = 0.0;
    }
    
    public Point2D(double X, double Y) {
        x = X; y = Y;
    }
    
    public double getX() {return x;}
    public double getY() {return y;}
    public void setX(double X) {x = X;}
    public void setY(double Y) {y = Y;}
    
    public double distance() { return Math.sqrt(x*x + y*y); }
    public double angleX() { return Math.atan(y/x); }
    
    public boolean equals(Point2D p) {
        if(Math.abs(p.getX() - x) <= 0.01 && Math.abs(p.getY() - y) <= 0.01) return true;
        else return false;
    }
    
    @Override 
    public String toString() {
        return String.format("Pukt2D o współrzędnych x: %.2f cm y: %.2f cm odległości: %.2f cm i kącie: %.2f°",
                x, y, distance(), Math.toDegrees(angleX()));
    }
}

class Point3D extends Point2D {
    private double z;
    
    public double getZ() {return z;}
    public void setZ(double Z) {z = Z; }
    
    @Override
    public double distance() {return Math.sqrt(x*x + y*y + z*z);}
    @Override
    public double angleX() {return Math.acos(x / distance());}
    
    @Override 
    public String toString() {
        return String.format("Pukt3D o współrzędnych x: %.2f y: %.2f z: %.2f", x, y, z);
    }
    
}

class Point2DAccumulator {
    Point2D[] points;
    int pos;
    
    public Point2DAccumulator() {
        points = new Point2D[1000];
        pos = -1;
    }
    
    public boolean insert(Point2D point) {
        for(int i = 0; i <= pos; i++) {
            if(distance(point, points[i]) < 1.0)
                return false;
        }
        points[++pos] = point;
        return true;
    }
    
    public boolean remove(Point2D point) {
        for(int i = 0; i <= pos; i++) {
            if(distance(point, points[i]) < 1.0)
                return false;
        }
        points[++pos] = point;
        return true;
    }
    
    public int near(Point2D p, int radius) {
        int count = 0;
        for(int i = 0; i < points.length; i++) {
            if(distance (points[i], p) <= radius) count++;
        }
        return count;
    }
    
    private double distance(Point2D p1, Point2D p2) {
        double dx = p1.getX() - p2.getY();
        double dy = p1.getY() - p2.getY();
        return Math.sqrt(dx*dx + dy*dy);
    }
}

public class Zadanie151 { 

    public static void main(String[] args) {
        Point2D p2d1 = new Point2D(3.0, 3.0);
        assert p2d1.angleX() == 0.7853981633974483;
        System.out.println("Distance = " + p2d1.distance());
        Point2D p2d2 = new Point2D(2.0, 4.0);
        assert p2d2.angleX() > 1.1071 && p2d2.angleX() < 1.1072;
        System.out.println("Distance = " + p2d2.distance());
        Point2D p2d3 = new Point2D(3.0, 4.0);
        assert p2d3.distance() == 5.0;
        
        Point2DAccumulator pa = new Point2DAccumulator();
        File file = new File("zadanie151.txt");
        Scanner sc;
        int notLoaded = 0;
        try {
            sc = new Scanner(file);
            while(sc.hasNextDouble()) {
                Point2D p = new Point2D(sc.nextDouble(), sc.nextDouble());
                System.out.println(p);
                if(!pa.insert(p)) {
                    notLoaded++;
                    System.out.println("Nie dodano!!!");
                }
            }
            System.out.println("Nie dodano " + notLoaded + " puktów");
        } catch (FileNotFoundException e) {
            System.out.println("Not found file with data");
        }   
    } 
}
