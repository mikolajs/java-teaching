package pl.edu.osp;


interface Iterator {
	
	boolean hasNext();
	Number next();
}


class ListNumber {
	
	class ListNumbItem {
		private ListNumbItem next = null;
		private Number num;
		private ListNumbItem(Number number){
			num = number;
		}
		
		public Number get() { return num;}
		
		public ListNumbItem getNext() { return next; }
		
		public void setNext(ListNumbItem next) { if(this.next == null) this.next = next; }
		
	}
	
	class ListNumberIterator implements Iterator {
		private ListNumbItem act;
		private int nr = 0;
		private ListNumberIterator(ListNumbItem first){
			act = first;
			nr = 0;
		}

		public boolean hasNext() {
			
			if(act.getNext() == null) return false;
			else return true;
		}
		
		public Number next() {
			act = act.getNext();
			return act.get();
		}
	}

	
	ListNumbItem first = null;
	int items = 0;
	
	public Number getAt(int pos) {
		if(pos >= items) return null;
		ListNumbItem actual = first;
		

		for(int i = 0; i <= pos; i++) {
			if(actual == null) return null;
			actual = actual.getNext();
		}
		if(actual ==  null) return null;
		else return actual.get();
	}
	
	public void push(Number numb) {
		ListNumbItem item = new ListNumbItem(numb);
		item.setNext(first);
		first = item;
		items++;
	}
	public ListNumberIterator iterator() {
		return new ListNumberIterator(first);
	}
	
	public int size() { return items; }
}



public class MainListNumber {
	public static void main(String[] args) {
		System.out.println("Start");
		ListNumber listInt = new ListNumber();
		for(int i = 1; i < 20; i++) {
			listInt.push(i*i);
		}
		System.out.println("size of list is " + listInt.size());
		Iterator it = listInt.iterator();
		int n;
		int pos = 0;
		while(it.hasNext()) {
			n  = it.next().intValue();
			System.out.println(pos + " : " + n);
			pos++;
		}
		final int p = 10;
		System.out.println(" At position " +  p + " is " + listInt.getAt(p));
		
		ListNumber listDouble = new ListNumber();
		for(int i = 0; i < 15; i++ ) {
			listDouble.push(Math.random()*1000);
		}
		System.out.println("size of list is " + listInt.size());
		Iterator it2 = listDouble.iterator();
		pos = 0;
		double d;
		while(it2.hasNext()) {
			d = it2.next().doubleValue();
			System.out.println(pos + " : " + d);
			pos++;
		}
		System.out.println(" At position " +  p + " is " + listDouble.getAt(p));
		System.out.println(" At position " +  p + " is " + listDouble.getAt(p).getClass().getName());
	}
}
