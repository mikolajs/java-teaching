package R2015;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/* demografia */
public class Zadanie5 {

	Land[] lands;
	public static void main(String[] args) {
		System.out.println();
		Zadanie5 zad = new Zadanie5();
		zad.add1();
		zad.add2();
		zad.add3();
	}
	
	public Zadanie5() {
		load();
		//print(0,100);
	}
	
	
	public void add1() {
	int[] people = new int[4];
		for(Land land : lands) {
			people[(int)land.region - 65] += land.m2013 + land.w2013;
		}
		for(int i = 0; i < people.length; i++) {
			System.out.println((char) (i + 65 ) + " : " + people[i]);
		}
	}
	
	public void add2() {
		int[] districts = new int[4];
		for(Land land : lands) {
			if(land.w2014 > land.w2013 && land.m2014 > land.m2013) 
			districts[(int)land.region - 65]++;
		}
		System.out.println("5.2 Regiony z przyrostem mężczyzn i kobiet");
		int sum = 0;
		for(int i = 0; i < districts.length; i++) {
			System.out.println((char) (i + 65 ) + " : " + districts[i]);
			sum += districts[i];
		}
		System.out.println("wszystkiech razem regionów: " + sum);
	}
	
	public void add3() {
		System.out.println("5.3 Ludność w 2025");
		for(Land land : lands) {
			land.inc = (double)(land.m2014 + land.w2014)/(double)(land.m2013 + land.w2013);
			land.inc = (double) ((int) (land.inc*10000.0)) / 10000.0;
			//System.out.println(land.inc);
			land.a2025 = land.m2014 + land.w2014;
			if(land.a2025 > 2*(land.m2013 + land.w2013)) land.overpop = true;
			else land.overpop = false;
		}
		for(int i = 2015; i <= 2025; i++) {
			for(Land land : lands) {
				if(!land.overpop) {
					land.a2025 = (int)(land.inc * (double)land.a2025);
					if(land.a2025 > 2*(land.m2013 + land.w2013))
							land.overpop = true;
				}
			}
		}
		int sum = 0;
		String maxDistr = "w";
		int maxPop = 0;
		int over = 0;
		for(Land land : lands) {
			sum += land.a2025;
			if(land.a2025 > maxPop) {
				maxPop = land.a2025;
				maxDistr = "w";
				if(land.nr < 10) maxDistr += "0";
				maxDistr += land.nr;
				maxDistr += land.region;
			}
			if(land.overpop) over++;
		}
		System.out.println("Cała populacja w 2025 to " + sum);
		System.out.println("Najwięcej mieszkańców: " + maxDistr + " " + maxPop);
		System.out.println("Przeludnienie w " + over + " województwach");
	}
	
	private void load() {
		Scanner sc;
		int nrOfLines = countLines();
		lands = new Land[nrOfLines];
		int n = 0;
		try {
			sc = new Scanner(new File("dane/R2015/kraina.txt"));
			String[] line;
			while(sc.hasNextLine()) {
				
				line = sc.nextLine().trim().split(";");
				lands[n] = new Land();
				for(int i = 0; i < line.length;i++) {
					switch(i) {
					case 0:
						lands[n].region = line[i].charAt(3);
						lands[n].nr = Integer.valueOf(line[i].substring(1, 3));
						break;
					case 1:
						lands[n].w2013 = Integer.valueOf(line[i]);
						break;
					case 2:
						lands[n].m2013 = Integer.valueOf(line[i]);
						break;
					case 3:
						lands[n].w2014 = Integer.valueOf(line[i]);
						break;
					case 4:
						lands[n].m2014 = Integer.valueOf(line[i]);
						break;
					}
				}
				n++;
			}
		} catch (IOException e) {
			System.out.println("brak pliku");
		}
	}
	
	
	private int countLines() {
		Scanner sc;
		int lines = 0;
		try {
			sc = new Scanner(new File("dane/R2015/kraina.txt"));
			while(sc.hasNextLine()) {
				if(sc.nextLine().trim().length() > 1) lines++;
			}
		} catch (IOException e) {
			System.out.println("brak pliku");
		}
		return lines;
	}
	
	private void print(int start, int end) {
		for(int i = start; i < end; i++) {
			if(i >= lands.length) break;
			System.out.println(lands[i].toString());
		}
	}
}


class Land {
	public int nr;
	public char region;
	public int w2013;
	public int w2014;
	public int m2013;
	public int m2014;
	public double inc;
	public int a2025;
	public boolean overpop = false;
	@Override
	public String toString() {
		return String.format("w%d%s %d %d %d %d", nr, region, w2013, m2013, w2014, m2014);
	}
}