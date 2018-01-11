package P2017;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

public class Zadanie4 {
	private int maxCifersSum = 0; 
	private int indexMaxCifersSum = 0;
	private int numberOf35CifersSum = 0;
	private Numbers[] nums;
	public Zadanie4() {
		File file = new File("dane/R2017/binarne.txt");
		Numbers[] nums = new Numbers[1000];
		int i = 0;
		try {
		Scanner sc = new Scanner(file);
		 while(sc.hasNextLine()) {
			nums[i] = new Numbers(sc.nextInt(), sc.nextInt(), sc.nextInt());
			i++;
		 }
		} catch (FileNotFoundException e) {
			System.out.println("Brak pliku \n" + e);
		}
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
		int index = -1;
		int max = -1;
		for(int i = 0; i < nums.length; i++) {
			all = ciferSum(nums[i].a) + ciferSum(nums[i].b)
				+ ciferSum(nums[i].c);
			if(all > max) {
				index = i;
				max = all;
			}
			if(all == 35) n++;
		}
		maxCifersSum = max;
		indexMaxCifersSum = index;
		numberOf35CifersSum = n;
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
		while(sum > 0) {
			sum += a % 10;
			a /= 10;
		}
		return sum;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
	    sb.append(String.format("1) uporządkowanych rosnąco %d", solv1()));
	    sb.append(String.format("2) suma NWD %d", solv2()));
	    sb.append(String.format("3) linii z sumą cyfr 35 jest %d \n" +
	    		"maksymalna suma cyfr to %d " + " i jest w linii nr %d"
	    		,numberOf35CifersSum, maxCifersSum, indexMaxCifersSum ));
		return sb.toString();
	}
}
