import java.util.*;

/* RNO_DOD - Proste dodawanie */
public class Main549 {

	public static void main(String[] args) {

		System.out.println();
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int n = 0;
		int suma;
		for(int i = 0; i < t; i++) {
			n = sc.nextInt();
			suma = 0;
			for(int j = 0; j < n; j++) {
			   suma += sc.nextInt();
			}
			System.out.println(suma);
		}
		
		sc.close();
	}

}
