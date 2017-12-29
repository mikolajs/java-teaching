import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/* GLUTTON - Obżartuchy - scanner za wolny*/
public class Main626 {

	public static void main(String[] args) throws IOException {
		System.out.println();
		//Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int z = Integer.parseInt(br.readLine());
		//int z = sc.nextInt();
		int N = 0;
	    int M = 0;
	    final int doba = 86400;
	    int c = 0;
	    int p = 0;
	    int t = 0;
	    String[] str;
		for(int i = 0; i < z; i++) {
			str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			M = Integer.parseInt(str[1]);
			c = 0;
			for(int j = 0; j < N; j++) {
				t = Integer.parseInt(br.readLine());
				c += doba / t;
			}
			p = c / M;
			if(c % M != 0) p++;
			System.out.println(p);
		}
		//sc.close();
	}

}
