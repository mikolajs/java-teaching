package R2019;
import java.util.Scanner;
import java.io.File;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new File("dane/R2019/liczby.txt"));
		int[] data = new int[500];
		for(int i = 0; i < 500; i++)
		 data[i] = sc.nextInt();
		Zadanie zad = new Zadanie(data);
		zad.test();
		zad.solve();
		sc.close();
	}

}
