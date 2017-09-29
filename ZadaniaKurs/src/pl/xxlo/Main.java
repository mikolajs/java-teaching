package pl.xxlo;

class Queue { 
	private int[] tab;
	private int start = -1;
	private int size = 0;
	
	public Queue(int size) {
		tab = new int[size];
	}
	
	public int pop() throws Exception {
		if(start < 0 || size == 0) throw new Exception("PUSTA");
		start--;
		size--;
		return 0;
	}
	public boolean push(int a) throws Exception {
		if(size == tab.length) throw new Exception("PEŁNA");
		if(start + size < tab.length) tab[start + size] = a;
		size++;
		
		return false;
	}
	public int look() throws Exception {
		if(start < 0 || size == 0) throw new Exception("PUSTA");
		if(start + size < tab.length) return tab[start + size - 1];
		else return tab[tab.length - size];
	}
	public int getSize() {
		return size;
	}
}

public class Main {

	public static void main(String[] args) {
		
		

	}

}
