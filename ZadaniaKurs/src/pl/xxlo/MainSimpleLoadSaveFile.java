package pl.xxlo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainSimpleLoadSaveFile {
	
	public long sum() {
		String path = "/home/ms/Programy/java-teaching/NaLekcji/suma.txt";
		long s = 0L;
		File file = new File(path);
		try {
			Scanner sc = new Scanner(file);
			s += sc.nextLong();
			sc.close();
		} catch(FileNotFoundException e) {
			System.out.println(e.toString());
		}
		return s;
	}
	///wrong implemented
	public long avg() {
		String path = "/home/ms/Programy/java-teaching/NaLekcji/srednia.txt";
		long s = 0L;
		File file = new File(path);
		try {
			Scanner sc = new Scanner(file);
			s += sc.nextLong();
			sc.close();
		} catch(FileNotFoundException e) {
			System.out.println(e.toString());
		}
		return s;
	}

	public static void main(String[] args) {
		MainSimpleLoadSaveFile m = new MainSimpleLoadSaveFile();
		System.out.println(m.sum());
		System.out.println(m.avg());

	}

}
