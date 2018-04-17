package R2010;


import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

public class Zadanie4 {
	private String[] wordsLines;
	int a = 0;
	int b = 0;
	
	public static void main(String[] args) {
		System.out.println("Zadanie 4: Anagramy");
		Zadanie4 zad4 = new Zadanie4();
		zad4.load();
		System.out.println(String.format("A) linii z równymi długością napisami: %d", zad4.a()) );
		System.out.println(String.format("B) linii z samymi anagramami: %d", zad4.b()) );
	}
	
	public int a() { return a; }
	public int b() { return b; }
	
	public void count(){
		StringBuffer sbA = new StringBuffer();
		StringBuffer sbB = new StringBuffer();
		int start[];
		int end[];
		for(String str : wordsLines){
			String[] arr = str.split(" ");
			int size = arr[0].length();
			start = letters(arr[0]);
			boolean equal = true;
			int anagram = 0;
			for(String word : arr){
				word = word.trim();
				if(word.length() != size) {
					equal = false;
					break;
				}
			}
			if(equal) {
				sbA.append(str + "\n");
				a++;
				for(int i = 1; i < arr.length; i++){				
					end = letters(arr[i]);
					if (mkCompare(start, end)) anagram++; 
				}	
			} 
			if(anagram == 4){
				sbB.append(str + "\n");
				b++;
			}
			writeToFile("odp_4a.txt", sbA);
			writeToFile("odp_4b.txt", sbB);
			
		}
	}
	
	private int[] letters(String s){
		int[] arr = new int[26];
		//for(int i = 0; i < 26; i++) arr[i] = 0;
		for(int i = 0; i < s.length(); i++){
			arr[(int) s.charAt(i)-97]++;
		}
		return arr;
	}
	
	
	private void writeToFile(String path, StringBuffer sb){
		try {
			Files.write(Paths.get(path), sb.toString().getBytes());
		} catch (IOException e){
			System.out.println("błąd zapisu");
		}
	}
	
	private boolean mkCompare(int[] s, int[] e){
		for(int i = 0; i < s.length; i++){
			if(s[i] != e[i]) return false;
		}
		return true;
	}

	private void load() {
		try {
			 List<String> l = Files.readAllLines(
					 Paths.get("dane/R2010/anagram.txt"));//.toArray(wordsLines);
			 wordsLines = l.toArray(new String[0]);
			 count();
			} catch (IOException e) {
				System.out.println("Problem z plikiem anagramy.txt");
			}	
	}
}
