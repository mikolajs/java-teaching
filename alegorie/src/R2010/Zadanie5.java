package R2010;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

public class Zadanie5 {
	String[] pesels;
	public Zadanie5(){
		try {
			pesels = (new String(Files.readAllBytes(Paths.get("pesel.txt")))).split("\n");
		} catch (IOException e){
			System.out.println("Problem z otwarciem pliku");
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
		PeselComparator pc = new PeselComparator();
		v.sort(pc);
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

}
