import java.util.Scanner;

/* NWW2 
 * Za wolne
 * */
public class Main747 {
	public static void main(String[] args) {
		System.out.println();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m;
		long b = 0L;
		long[] d;
		int k, s;
		for(int i = 0; i < n; i++) {
			m = sc.nextInt();
			
			if(m < 2) {
				System.out.println(sc.nextLong());
				continue;
			}
			if(m % 2 == 0)
			  d = new long[m/2];
			else 
				d = new long[m/2+1];
			for(int j = 0; j < m/2; j++) {
				d[j] = nww(sc.nextLong(), sc.nextLong());
			}
			if(m % 2 == 1)
				d[m/2] = sc.nextLong();
			k = 1;
			
			while(k < d.length) {
				s = 0;
				while(s < d.length) {
					if(s+k < d.length)
					 d[s] = nww(d[s],d[s+k]);
					s += 2*k;
				}
				k *= 2;
			}
			
			System.out.println(d[0]);	
		}
		sc.close();
		
	}
	
	public static long nww(long a, long b) {
		long A = a;
		long B = b;
		long c = 0;
		if(a < b) {
			c = b;
			b = a;
			a = c;
		}
		while(b != 0) {
			c = a % b;
			a = b;
			b = c;
		}
		return A/a*B;
	}
}



	
