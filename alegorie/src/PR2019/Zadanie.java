package PR2019;
import java.util.*;

public class Zadanie {
	private int[] mData;
	public Zadanie(int[] d) {
		mData = d;
	}
	
    private int odpA(int[] data) {
    	int ile = 0;
    	int pot3 = 1;
    	for(int i = 0; i < data.length; i++) {
    		pot3 = 1;
    		while(pot3 < Math.sqrt(100000)) {
        		if(data[i] == pot3) {
        			ile++;
        			break;
        		}
        		pot3 *= 3;
        	}
    	}
    	return ile;
    }
    
    private ArrayList<Integer> odpB(int[] data) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	int reszta;
    	int suma = 0;
    	for(int liczba : data) {
    		reszta = liczba;
    		suma = 0;
    		while(reszta != 0) {
    			suma += silnia(reszta % 10);
    			reszta /= 10;
    		}
    		if(suma == liczba) list.add(liczba);
    	}
    	
    	return list;
    }
    
    private WynikC odpC() {
    	WynikC wynik = new WynikC();
    	
    	
    	return wynik;
    }
    
    private int silnia(int n) {
    	int s = 1;
    	while (n > 1) {
    		s *= n;
    		n--;
    	}
    	return s;
    }
    
    private int nwd(int a, int b) {
    	int c = 0;
    	while(a % b != 0) {
    		c = a % b;
    		a = b;
    		b = c;
    	}
    	return b;
    }
    
    public void solve() {
    	System.out.println("ODP A");
    	System.out.format("Z 500 liczb %d jest potęgami 3\n",
    			odpA(mData));
    	System.out.println("ODP B");
    	System.out.println("Liczby równe sumie silni cyfr: ");
    	odpB(mData).stream().forEach(num -> System.out.println(num));
    	System.out.println("\n ODP C");
    	WynikC wynikC = odpC();
    	System.out.format("Pierwsza liczba najdłużeszgo  ciągu %d, długość %d, nwd %d"
    			, wynikC.pierwsza, wynikC.dlugosc, wynikC.nwd);
    }
    
    public void test() throws Exception {
    	int[] tab = {1, 5, 9, 17, 38, 81, 99, 102};
    	int a = odpA(tab);
    	if(a != 3) throw new Exception();
    	int[] tab2 = {343, 145};
    	ArrayList<Integer> array = odpB(tab2);
    	if(array.isEmpty() && array.size() != 1) throw new Exception();
        if(array.get(0) != 145) throw new Exception();
    	if(nwd(32, 12) != 4) throw new Exception();
    }
	
}

class WynikC {
	int pierwsza;
	int dlugosc;
	int nwd;
}
