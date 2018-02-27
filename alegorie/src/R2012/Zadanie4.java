package R2012;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Zadanie4 {


	public static void main(String[] args) {

		Zadanie4 zad = new Zadanie4();
		System.out.println("Zadanie 4 R2012");
		zad.test();
		System.out.println("A. zakodowane:");
		System.out.println(zad.zadA());
		zad.save("dane/R2012/wynikA.txt",zad.zadA());
		System.out.println("B. odkodowane:");
		System.out.println(zad.zadB());
		zad.save("dane/R2012/wynikB.txt",zad.zadB());
		
	}

	public String zadA() {
		String[] words = loadData("dane/R2012/tj.txt");
		String[] keys = loadData("dane/R2012/klucze1.txt");
		assert words.length == keys.length;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < words.length; i++ ) {
			sb.append(code(words[i], keys[i]) + "\n");
		}
		return sb.toString();
	}
	
	public String zadB() {
		String[] words = loadData("dane/R2012/sz.txt");
		String[] keys = loadData("dane/R2012/klucze2.txt");
		assert words.length == keys.length;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < words.length; i++ ) {
			sb.append(decode(words[i], keys[i]) + "\n");
		}
		return sb.toString();
	}
	
	private String code(String word, String key) {
		String coded = "";
		int k;
		for(int i = 0; i < word.length(); i++) {
			k = (int) word.charAt(i) + (int) key.charAt(i % key.length()) - 64;
			if(k > 90) k -= 26;
			coded += (char) k;
		}
		return coded;
	}
	
	private String decode(String word, String key) {
		String coded = "";
		int k;
		for(int i = 0; i < word.length(); i++) {
			k = (int) word.charAt(i) - ((int) key.charAt(i % key.length()) - 64);
			if(k < 65) k += 26;
			coded += (char) k;
		}
		return coded;
	}
	
	private String[] loadData(String path) {
		File file = new File(path);
		Scanner sc =  null;
		StringBuilder sb = new StringBuilder();
		try {
			sc = new Scanner(file);
			while(sc.hasNext()) sb.append(sc.next()+ " ");
			
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			if(sc != null) sc.close();
		}
		return sb.toString().split(" ");
	}
	
	public void save(String name, String content) {
		try {
		Files.write(Paths.get(name), content.getBytes());
		} catch(IOException e) {
			System.out.println("Path not found, DON'T SAVED");
		}
	}
	
	private void test() {
		//System.out.println( decode("IPXP", "WODA"));
		//System.out.println( code("MARTA", "TOR"));
		assert code("LATO", "WODA").equals("IPXP");
		assert code("MARTA", "TOR").equals("GPJNP");
		assert decode("GPJNP", "TOR").equals("MARTA");
		assert decode("IPXP", "WODA").equals("LATO");
	}
}
