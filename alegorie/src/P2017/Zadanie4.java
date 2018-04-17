package P2017;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



/* zadanie 165 */
public class Zadanie4 {
	private int maxCifersSum = 0; 
	private int numberMaxCifersSum = 0;
	private int numberOf35CifersSum = 0;
	private Numbers[] nums;
	
	public static void main(String[] args) {
	    System.out.println();
        Zadanie4 zad4 = new Zadanie4();
        System.out.println(zad4);
    }
	
	
	public Zadanie4() {
		load();
	}
	
	private int solv1() {
		int n = 0;
		for(int i = 0; i < nums.length; i++) {
			if(nums[i].a < nums[i].b  && nums[i].b < nums[i].c) n++;
		}
		return n;
	}
	
	private int solv2() {
		int sum = 0;
		int tmp;
		for(int i = 0; i < nums.length; i++) {
			tmp = NWD(nums[i].a, nums[i].b);
			sum += NWD(tmp, nums[i].c);
		}
		
		return sum;
	}
	
	private void solv3() {
		int all = 0;
		int n = 0;
		int max = -1;
		int[] ciferSumArray = new int[nums.length];
		for(int i = 0; i < nums.length; i++) {
            ciferSumArray[i] = ciferSum(nums[i].a) + ciferSum(nums[i].b)
                + ciferSum(nums[i].c);
            all = ciferSum(nums[i].a) + ciferSum(nums[i].b)
            + ciferSum(nums[i].c);
            if(all > max) {
                max = all;
            }
            if(all == 35) n++;  
		}
		maxCifersSum = max;
		numberOf35CifersSum = n;
		n = 0;
		for(int i = 0; i < nums.length; i++) {
			if(ciferSumArray[i] == max) n++;
		}
		
		numberMaxCifersSum = n;
		
	}
	
	private int NWD(int a, int b) {
		int tmp;
		if(a < b) {
			tmp = a;
			a = b;
			b = tmp;
		}
		while(a % b != 0) {
			tmp = b;
			b = a % b;
			a = tmp;
		}
		return b;
	}
	
	private int ciferSum(int a) {
		int sum = 0;
		while(a > 0) {
			sum += a % 10;
			a /= 10;
		}
		return sum;
	}
	
	private void load() {
	    File file = new File("dane/P2017/liczby.txt");
        nums = new Numbers[1000];
        int i = 0;
        Scanner sc = null;
        try {
        sc = new Scanner(file);
         while(sc.hasNextInt()) {
            if(i < 1000)
                nums[i] = new Numbers(sc.nextInt(), sc.nextInt(), sc.nextInt());
            i++;
         }
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku \n" + e);
        } finally {
           if(sc != null) sc.close();
        }
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
	    sb.append(String.format("1) uporządkowanych rosnąco %d \n", solv1()));
	    sb.append(String.format("2) suma NWD %d \n", solv2()));
	    solv3();
	    sb.append(String.format("3) linii z sumą cyfr 35 jest %d \n" +
	    		"maksymalna suma cyfr to %d " + " i jest ich %d"
	    		,numberOf35CifersSum, maxCifersSum, numberMaxCifersSum ));
		return sb.toString();
	}
}

class Numbers {
	int a;
	int b;
	int c;
	public Numbers(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
}
