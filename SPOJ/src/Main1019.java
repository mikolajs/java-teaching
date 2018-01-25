import java.util.Scanner;

/* systemy liczbowe SYS */
public class Main1019 {
	public static void main(String[] args) {
		System.out.println();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a;
		for(int i = 0; i < n; i++) {
			a = sc.nextInt();
			System.out.println(toString(a, 16) +
					" " + toString(a, 11));
		}
		sc.close();
	}
	
	public static String toString(int number, int radix) {
		//String s = "";
		char[] seq = new char[12];
		int a;
		int i = 0;
		while (number != 0) {
			a = number % radix;
			if(a < 10) {
			   seq[i] = (char) (a + 48);
			} else {
				seq[i] = (char)(a + 55);
			}
			number /= radix;
			i++;
		}
		StringBuilder sb = new StringBuilder();
		for(int j = i -1; j >= 0; j--) {
			sb.append(seq[j]);
		}
		return sb.toString();
	}
}
