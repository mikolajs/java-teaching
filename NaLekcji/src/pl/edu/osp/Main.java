package pl.edu.osp;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;


class Main {

    public static void main(String[] args) {
    	Main m = new Main();
    	System.out.println();
    	m.test();
    	
    	StringBuilder sb = new StringBuilder();
    	
    	try {
			Scanner s1 = new Scanner(new File("tj.txt"));
			Scanner s2 = new Scanner(new File("kucze1.txt"));
			while (s1.hasNext()) {
				sb.append(m.code(s1.next(), s2.next()) + "\n"); 
				s2.close();
				s1.close();
			}

		} catch (Exception e) {
			System.out.println("Błąd " + e.toString());
		}
    	
    	
    	try {
    		Files.write(Paths.get("wynikA.txt"), sb.toString().getBytes());
    	} catch (IOException e) {
    		System.out.println("Błąd" + e.toString());
    	}
    	
    	
    	
    }
    
    public String code(String crypted, String key) {
    	String decrypted = "";
    	int k;
		for(int i = 0; i < crypted.length(); i++) {
			k = (int) crypted.charAt(i) + (int) key.charAt(i % key.length()) - 64;
			if(k > 90) k -= 26;
			decrypted += (char) k;
		}	
    	return decrypted;
    }
    
    public String decode(String decrypted, String key) {
    	String crypted = "";
    	int k;
    	for(int i = 0; i < decrypted.length(); i++) {
			k = (int) decrypted.charAt(i) -((int) key.charAt(i % key.length()) - 64);
			if(k < 65) k += 26;
			crypted += (char) k;
		}	
    	return crypted;
    }
    
    public void test() {
    	assert code("LATO", "WODA").equals("IPXP");
    	assert code("MARTA", "TOR").equals("GPJNP");
    	assert decode("IPXP", "WODA").equals("LATO");
    	assert decode("GPJNP", "TOR").equals("MARTA");
    }
}
