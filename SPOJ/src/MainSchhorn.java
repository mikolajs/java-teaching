import java.io.BufferedReader;
import java.io.InputStreamReader;

//to slow 
public class MainSchhorn {

	public static void main (String[] args) throws java.lang.Exception	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int z = Integer.parseInt(br.readLine());
		int n, t;
		long multi[];
		
	    
		for(int i = 0; i < z; i++) {
			n = Integer.parseInt(br.readLine());
			String[] tab = br.readLine().split(" ");
			n++;
			multi = new long[n];
			for(int j = 0; j < n; j++) {
				multi[j] = Long.parseLong(tab[j]);
			}
			t = Integer.parseInt(br.readLine());
			tab = br.readLine().split(" ");
			for(int j = 0; j < t; j++) {
				System.out.println(horner(Long.parseLong(tab[j]), multi));
			}
		}
		br.close();
	}
	
	public static long horner(long x, long[] a) {
		if(a.length == 0) return 0L;
		long fx = a[0];
		for(int i = 1; i < a.length; i++) {
			fx *=x;
			fx += a[i];
		}
		return fx;
	}
}
