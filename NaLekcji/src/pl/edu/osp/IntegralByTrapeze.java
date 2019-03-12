package pl.edu.osp;

import java.util.Random;

public class IntegralByTrapeze {
	private double[] wielomian;
	final int maxSize = 10;
	
	public IntegralByTrapeze() {
		Random r = new  Random();
		int size = r.nextInt(maxSize/2) + maxSize/2;
		wielomian = new double[size];
		for(int i = 0; i < wielomian.length; i++) {
			wielomian[i] = r.nextDouble()*10.0;
		}
	}
	
	public double horner(double x, double[] a) {
		if(a.length == 0) return 0.0;
		double fx = a[0];
		for(int i = 1; i < a.length; i++) {
			fx *=x;
			fx += a[i];
		}
		return fx;
	}
	
	public void printChart() {
		long[] fy = new long[100];
		long max = 0L;
		for(int i = 0; i < fy.length; i++) {
		    fy[i] = Math.round(horner(i, wielomian));
		    if(i == 0) max = fy[i];
		    else if(max < fy[i]) max = fy[i];
		    System.out.print(fy[i] + "\t");
		}
		long factor = max / 20L;
		for(int i = 0; i < fy.length; i++) {
			long fyi = fy[i]/factor;
			System.out.print(i + ": ");
			for(int j = 0; j <= fyi; j++) {
				System.out.print("#");
			}
			System.out.println();
		}
	}
	
	public double integral(int start, int end, double step) {
		double value = 0.0;
		for(double x = (double) start; x < (double) end; x += step) {
			value += step*((horner(x, wielomian)+horner(x+step, wielomian))/2.0);
		}
		return value;
	}
	
	public double integral2(int start, int end, double step) {
		double value = 0.0;
		for(double x = (double) start; x < (double) end; x += step) {
			value += step*((Math.sqrt(x*x + 1.0)+Math.sqrt((x+step)*(x+step) + 1.0))/2.0);
		}
		return value;
	}
	// funkcja  Math.sqrt(x*x + 1) całka oznaczona od 0 do 1. krok 0.001
	
	public static void main(String[] args) {
		IntegralByTrapeze ibt = new IntegralByTrapeze();
		ibt.printChart();
		System.out.println(ibt.integral(2, 10, 0.1));
		System.out.println(ibt.integral(2, 10, 0.01));
		System.out.println(ibt.integral(2, 10, 0.001));
		System.out.println(ibt.integral(2, 10, 0.0001));
		System.out.println(ibt.integral(2, 10, 0.00001));
		
		System.out.println(ibt.integral2(0, 1, 0.001));
	}
}
