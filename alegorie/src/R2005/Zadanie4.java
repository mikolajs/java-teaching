package R2005;

public class Zadanie4 {
	double[] rangeA; 
	
	
	public Zadanie4(){
		rangeA = new double[4];
		countRangeA();
	}
	
	public double getD1RangeStart(){
		return rangeA[0];
	}
	public double getD2RangeStart(){
		return rangeA[2];
	}
	public double getD1RangeEnd(){
		return rangeA[1];
	}
	public double getD2RangeEnd(){
		return rangeA[3];
	}
	public double getD1cost(int data){
		return costD1(data);
	}
	public double getD2cost(int data){
		return costD2(data);
	}
	public double[] getD2Table(){
		double[] arr = new double[62];
		double N = 0.0;
		for(int i = 0; i < 62; i+= 2){
			N = (double)((i/2)*100 + 6000);
			arr[i] = 0.5*Math.sqrt(N);
			arr[i+1] = 5.0*f(N);
		}
		return arr;
	}
	
	private double f(double N){
		final double m = 0.0001*N;
		double r = 10.0*m*m*m + 7.0*m*m + 0.1*m + 0.1;
		return r;
	}
	private double costD1(double N){
		double c = 0.01*N + f(N);
		return c;
	}
	private double costD2(double N){
		double c = 0.5*Math.sqrt(N) + 5.0*f(N);
		return c;
	}
	
	private void countRangeA(){
		double range = 1.0;
		boolean lower1 = true;
		if(costD1(1) > costD2(1)) lower1 = false;
		double c1, c2;
		double change2 = 0.0;
		while(range <= 7000.0){
			c1 = costD1(range);
			c2 = costD2(range);
			if(lower1 && c1 > c2) {
				System.out.println("change  from 1 to 2 at " + range);
				change2 = range;
				lower1 = false;
			}
			if(!lower1 && c1 < c2) {
				System.out.println("change from 2 to 1 at" + range);
				lower1 = true;
			}
			
			range += 0.01;
		}
		rangeA[0] = 1;
		rangeA[1] = change2 - 0.01;
		rangeA[2] = change2;
		rangeA[3] = 7000;
	}
}
