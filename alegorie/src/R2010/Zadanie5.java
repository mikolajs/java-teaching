package R2010;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;
import java.util.Comparator;

/* Pesele */
public class Zadanie5 {
	String[] pesels;
	
	public static void main(String[] args) {
		Zadanie5 zad5 = new Zadanie5();
		zad5.load();
		System.out.println("Zadanie 5: PESELe");
		System.out.println("A) Urodzeni w grudniu: " + zad5.a());
		System.out.println("B) Pracuje kobiet: " + zad5.b());
		System.out.println("C) Rok największej liczby urodzin: 19" + zad5.c());
		System.out.println("D) Nieprawidłowe pesele:");
		String[] s = zad5.d();
		for(String str : s){
			System.out.println(str);
		}
		System.out.println("E) Zestawienie urodzeń w dziesięcioleciach:");
		int[] dec = zad5.e();
		for(int i = 0; i < dec.length; i++){
			if(dec[i] > 0) {
				System.out.println(i+"0-te | " + dec[i]);
			}
		}
	}
	
	public int a() {
		int n = 0;
		for(String p : pesels){
			if(p.charAt(2) == '1' && p.charAt(3)== '2') n++;
		}		
		return n;
	}
	
	public int b() {
		int n = 0;
		int tmp = 0;
		for(String p : pesels){
			tmp = (int)p.charAt(9) - 48;
			if(tmp % 2 == 0) n++;
		}
		return n;
	}
	
	public int c() {
		int[] tab = new int[100];
		int tmp = 0;
		for(String p : pesels){
			tmp = ((int)p.charAt(0) - 48)*10 + (int)p.charAt(1) - 48 ;
			tab[tmp]++;
		}
		int max = 0;
		tmp = 0;
		for(int i = 0; i < 100; i++){
			if(tab[i] > max) {
				tmp = i;
				max = tab[i];
			}
		}		
		return tmp;
	}
	
	public String[] d() {
		Vector<String> v = new Vector<String>();
		int sum, r;
		for(String p : pesels){
			sum = 0;
			sum += (int)p.charAt(0) - 48;
			sum += ((int)p.charAt(1) - 48)*3;
			sum += ((int)p.charAt(2) - 48)*7;
			sum += ((int)p.charAt(3) - 48)*9;
			sum += ((int)p.charAt(4) - 48);
			sum += ((int)p.charAt(5) - 48)*3;
			sum += ((int)p.charAt(6) - 48)*7;
			sum += ((int)p.charAt(7) - 48)*9;
			sum += ((int)p.charAt(8) - 48);
			sum += ((int)p.charAt(9) - 48)*3;
			r = sum % 10;
			if(r == 0) {
				if(((int)p.charAt(10) - 48) != r) v.addElement(p.trim()); 
			} else {
				r = 10 - r;
				if(((int)p.charAt(10) - 48) != r) v.addElement(p.trim()); 
			}
		}
		v.sort(new PeselComparator());
		return v.toArray(new String[0]);
	}
	
	public int[] e() {
		int[] a = new int[10];
		int tmp = 0;
		for(String p : pesels){
			tmp = p.charAt(0) - 48;
			a[tmp]++;
		}				
		return a;
	}
	
	private void load() {
		try {
			pesels = (new String(
					Files.readAllBytes(
							Paths.get("dane/R2010/pesel.txt"))))
						.split("\n");
		} catch (IOException e){
			System.out.println("Problem z otwarciem pliku");
		}
	}
}

class PeselComparator implements Comparator<String> {
	
		@Override
	    public int compare(String s1, String s2){
	        return s1.compareTo(s2);
	    }
}

