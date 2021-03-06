package R2016;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/* zadanie 304 */
public class Zadanie6 {
	
	public static void main(String[] args) {
	Zadanie6 zad6 = new Zadanie6();
	System.out.println("Wyniki zadania 6 drukowane do pliku");
	zad6.encryptStrings();
	zad6.decryptStrings();
	zad6.checkStrings();
	}
	
	
	public void encryptStrings(){
		StringBuilder sb = new StringBuilder();
		try {
			List<String> sl = Files.readAllLines(Paths.get("dane/R2016/dane_6_1.txt"));
			for(String s: sl){
				sb.append(code(s, 107) + "\n");
			}
			//Files.write(Paths.get("./wyniki_6_1.txt"), sb.toString().getBytes());
			System.out.println("ad1. wyniki_6_1.txt ");
			System.out.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void decryptStrings(){
		StringBuilder sb = new StringBuilder();
		try {
			List<String> sl = Files.readAllLines(Paths.get("dane/R2016/dane_6_2.txt"));
			String[] arr;
			for(String s: sl){
				//System.out.println(s);
				arr = s.split(" ");
				if(arr.length <1 ) continue;
				else if(arr.length < 2) {
					sb.append(arr[0] + " !Brak klucza!");
				}
				else sb.append(decode(arr[0].trim(), Integer.valueOf(arr[1]).intValue()) + "\n");
			}
			System.out.println("ad2. wyniki_6_2.txt ");
			System.out.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void checkStrings(){
		StringBuilder sb = new StringBuilder();
		try {
			List<String> sl = Files.readAllLines(Paths.get("dane/R2016/dane_6_3.txt"));
			String[] arr;
			for(String s: sl){
				//System.out.println(s);
				arr = s.split(" ");
				if(arr.length < 2) continue;
				if(!checkCode(arr[0].trim(), arr[1].trim())) sb.append(arr[0].trim() + "\n");
			}
			System.out.println("ad3. wyniki_6_3.txt ");
			System.out.println(sb.toString());			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean checkCode(String plain, String crypted){
		if(plain.length() != crypted.length()) return false;
		int dist = (int) crypted.charAt(0) - (int) plain.charAt(0);
		if(dist < 0) dist += 26;
		String coded = code(plain, dist);
		if(coded.equals(crypted)) return true;
		return false;
	}
	
	
	private String code(String crypted, int key){
		StringBuilder decrypted = new StringBuilder();
		key %= 26;
		int code = 0;
		for(char c: crypted.toCharArray()){
			code = ((int) c) + key;
			if(code > 90) code -= 26;
			decrypted.append((char) code);
		}
		return decrypted.toString();
	}
	
	private String decode(String decrypted, int key){
		StringBuilder crypted = new StringBuilder();
		key %= 26;
		int code = 0;
		for(char c: decrypted.toCharArray()){
			code = ((int) c) - key;
			if(code < 65) code += 26;
			crypted.append((char) code);
		}
		return crypted.toString();
	}
	
}
