
class Dane {
    private int a;
    public static int b = 3;
    static void print() {
        System.out.println(b);
    }
    public void setA(int a) {this.a = a; }
    public int getA() { return a;}
}

public class Main {
    public static void main(String[] args) {
	   Dane.b = 34;
       Dane.print();
       Dane d1 = new Dane();
       Dane d2 = new Dane();
       d1.setA(1);
       d2.setA(2);
       System.out.println("d1.a = " + d1.getA() + " d2.a = " + d2.getA());
	}
}

