
class Dane {
    public int a;
}

public class Test {
 
    public void change(Dane d) {
        d.a = d.a*d.a;
    }
    
    public void swap(Dane d1, Dane d2) {
        Dane tmp = d1;
        d1 = d2;
        d2 = tmp;
        System.out.println("wewnątrz metody d1 ma a = " + d1.a + " zaś d2 ma a = " + d2.a);
    }
    
    public Dane get(int a) {
        Dane d = new Dane();
        d.a = a;
        return d;
    }
    
    public static void main(String[] args) {
        Test t  = new Test();
        Dane d1 = new Dane();
        d1.a = 3;
        t.change(d1);
        System.out.println(d1.a);
        Dane d2 = new Dane();
        d2.a = 5;
        t.change(d2);
        System.out.println(d2.a);
       
        t.swap(d1, d2);
        System.out.println("na zewnątrz dalej d1 ma a = " + d1.a + " zaś d2 ma a = " + d2.a);
        
        Dane d3 = t.get(7);
        System.out.println(d3.a);
    } 

}
