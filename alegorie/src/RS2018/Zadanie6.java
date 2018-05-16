package RS2018;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Zadanie6 {
	DWord[] data;
	public static void main(String[] args) {
		Zadanie6 z = new Zadanie6();
		z.loadFile();
		z.solve1();
		z.solve2();
		z.solve3();
		
	}
	
	private void solve1() {
		System.out.println("1) kończy się na A:  ");
	}

	private void solve2() {
		
	}
	
	private void solve3() {
		
	}
	
	
	private void loadFile() {
        Scanner sc = null;
        data = new DWord[1000];
        int counter = 0;
        try {
            sc = new Scanner(new File("dane/RS2018/slowa.txt"));
            while (sc.hasNext()) {
            	data[counter] = new DWord();
            	data[counter].a = sc.next();
            	data[counter].b = sc.next();
            }
        } catch (IOException e) {
            System.out.println("brak pliku");
        } finally {
            if (sc != null)
                sc.close();
        }
    }
}

class DWord {
	public String a;
	public String b;
}
