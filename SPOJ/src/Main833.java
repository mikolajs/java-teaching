import java.util.Scanner;

/* BINOMS - Dwumiany 
 * np bionom 32 16 = 601080390. 
 * mnożenie do 17 do 32 przekroczy zakres long 
 * binom 30 15 = 155117520
 * binom 300 296 = 330791175
 * binom 450 4 = 1685905200
 * */
public class Main833 {
	public static void main(String[] args) {
		System.out.println("");
		Scanner sc = new Scanner(System.in);
		int D = sc.nextInt();
		int n = 0;
		int k = 0;
		long b = 0;
		
		for (int j = 0; j < D; j++) {
			n = sc.nextInt();
			k = sc.nextInt();
			if(k > n -k) k = n - k;
			b = 1L;
			for(int i = 1; i <= k; i++) {
				b *= (n - i + 1);
				b /= i;
			}
			System.out.println(b);
		}
		sc.close();
	}

	
}
