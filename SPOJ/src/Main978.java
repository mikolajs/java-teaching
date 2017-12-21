import java.util.Scanner;

/* STOS */
public class Main978 {
	 public static void main(String[] args) {
	        System.out.println("");
	        Scanner s = new Scanner(System.in);
	       
	        int[] stack = new int[10];
	        
	        int p = -1;
	        char command = ' ';
	        while(s.hasNext()) {
	        	command = s.next().trim().charAt(0);
	        	if(command == '+') {
	        		if(p == stack.length - 1) {
	        			System.out.println(":(");
	        			s.nextInt();
	        		} else {
	        			p++;
	        			stack[p] = s.nextInt();
	        			System.out.println(":)");
	        		}
	        	} else {
	        		if(p < 0) {
	        			System.out.println(":(");
	        		} else {
	        			System.out.println(stack[p]);
	        			p--;
	        		}
	        	}
	        }
	        
	        s.close();
	    }
}
