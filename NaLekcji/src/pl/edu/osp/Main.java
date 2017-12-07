package pl.edu.osp;

class MyInt {
    public int a;
}

public class Main {
    public static void main(String[] args) {
       int a = 34;
       int b = 32;     
       System.out.println("a = " + a + ",  b = " + b);
       swap(a, b);
       System.out.println("a = " + a + ",  b = " + b);
       swap2(a, b);
       System.out.println("a = " + a + ",  b = " + b);
       Integer x = 34;
       Integer y = 32;
       swap(x, y);
       System.out.println("x = " + x + ",  y = " + y);
       swap2(x, y);
       System.out.println("x = " + x + ",  y = " + y);
       
       MyInt m = new MyInt();
       m.a = 34;
       MyInt n = new MyInt();
       n.a = 32;
       System.out.println("m = " + m.a  + " n = " + n.a);
       swap3(m, n);
       System.out.println("m = " + m.a  + " n = " + n.a);
    }
    
    public static void swap2(Integer a, Integer b) {

        Integer x;
        x = b;
        b = a;
        a = x;
        System.out.println("a = " + a + ",  b = " + b);
    }
    
    public static void swap(int a, int b) {
       int x = b;
       b = a;
       a = b;
    }
    
    public static void swap3(MyInt x, MyInt y) {
        MyInt tmp = x;
        x = y;
        y = tmp;
    }

}
