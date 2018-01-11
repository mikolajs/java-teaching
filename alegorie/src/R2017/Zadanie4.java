package R2017;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Zadanie4 {
	
	private String[] binary;
	private int doubleCyclic;
	private int maxDC;
	private String maxStrDC ="";
	private int badBCD;
	private int minBCD;
	private int maxBinary;
	private int maxBinaryIndex;
	
	Zadanie4(){
		
		File file = new File("dane/R2017/binarne.txt");
		binary = new String[500];
		try {
			Scanner sc = new Scanner(file);
			for(int i = 0; i < binary.length; i++) {
				binary[i] = sc.nextLine().trim();
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Brak pliku");
		}
		solution1();
		solution2();
		solution3();
	}
	
	private void solution1() {
		int n = 0;
		int max = 0;
		String maxStr = "";
		for(String b : binary) {
			if(b.substring(0, b.length()/2).equals(
					b.substring(b.length()/2, b.length()))) {
				n++;
				if(max < b.length()) {
					max = b.length();
					maxStr = b;
				}	
			}
		}
		doubleCyclic = n;
		maxDC = max;
		maxStrDC = maxStr;
	}
	
	private void solution2() {
		boolean good = true;
		int num;
		int bad = 0;
		int min = Integer.MAX_VALUE;
		for(String b : binary) {
			good = true;
			for(int i = 0; i < b.length(); i+= 4) {
				num = Integer.valueOf(b.substring(i, i+4), 2);
				if(num > 9) {
					bad++;
					if(min > b.length()) min = b.length();
					break;
				}
			}
		}
		badBCD = bad;
		minBCD = min;
	}
	
	private void solution3() {
		int max = 0;
		int maxIndex = -1;
		int tmp;
		for(int i = 0; i < binary.length; i++) {
			if(binary[i].length() > 16) continue;
			tmp = Integer.valueOf(binary[i], 2);
				if(max < tmp) {
					max = tmp;
					maxIndex = i;
			}
		}
		maxBinaryIndex = maxIndex;
		maxBinary = max;
	}
	
	private String answer1() {
		return String.format("1)\n Ilość liczb dwucykicznych %d \n", doubleCyclic) +
				String.format("Ilość cyfr w nadłuższym dwucyklicznym: %d \n", maxDC) +
				String.format("Najdłuższy dwucykliczny %s\n" , maxStrDC) ;
	}
	
	private String answer2() {
		return String.format("2)\nNiepoprawnych BCD: %d\n", badBCD) + 
				String.format("Najkrótszy niepoprawny BCD: %d \n", minBCD);
	}
	
	private String answer3() {
		return String.format("3) \nMaksymalna liczba binarna: %d \n", maxBinary) +
				String.format("Jej wartość w zapisie binarnym %s", 
						binary[maxBinaryIndex]);
	}
	@Override
	public String toString() {
		return answer1() + answer2() + answer3();
		
	}
}
