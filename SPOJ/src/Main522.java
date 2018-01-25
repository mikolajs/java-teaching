import java.util.Scanner;

public class Main522 {

	public static void main(String[] args) {
		System.out.println();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a, b;
		for(int i = 0; i < n; i++) {
			a = sc.nextInt();
			b = sc.nextInt();
			System.out.println(a/nwd(a, b)*b);
		}
	}
	
	public static int nwd(int a, int b) {
		int c = 0;
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
		return a;
	}

}
