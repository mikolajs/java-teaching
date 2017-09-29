package pl.xxlo;

class Queue { 
	private int[] tab;
	private int start = 0;
	private int size = 0;
	
	public Queue(int size) {
		tab = new int[size];
	}
	
	public int pop() throws Exception {
		if(size == 0) throw new Exception("PUSTA");
		int temp = tab[start++];
		start %= tab.length;
		size--;
		return temp;
	}
	public boolean push(int a) {
		if(size == tab.length) return false;
		tab[(start + size++) % tab.length ] = a;
		return true;
	}
	public int look() throws Exception {
		if(size == 0) throw new Exception("PUSTA");
		return tab[start];
	}
	public int getSize() {
		return size;
	}
	
	public void print() {
		System.out.println("Start: " + start + " size " + size);
		for(int e : tab) {
			System.out.print(e + ", ");
		}
		System.out.println();
	}
}

public class MainQueue {

	public static void main(String[] args) {
		final int SIZE = 10;
		Queue q = new Queue(SIZE);
		for(int i = 0; i < SIZE; i++) q.push(i*i);
		try {
			for(int i = 0; i < SIZE / 2; i++)
				System.out.print(q.pop() + "\t"); 
			System.out.println("\nNastępna " + q.look());
			System.out.println("Rozmiar: " + q.getSize());
			q.print();
			
		} catch( Exception e) {
			System.out.println(e.toString());
		}
		for(int i = 0; i < SIZE / 2; i++) q.push(i*10);
		q.print();
		try {
			for(int i = 0; i < SIZE; i++)
				System.out.print(q.pop() + "\t"); 
			System.out.println("\nRozmiar: " + q.getSize());
			
		} catch( Exception e) {
			System.out.println(e.toString());
		}		
	}
}
