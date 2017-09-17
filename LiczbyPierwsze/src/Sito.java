
public class Sito {
	boolean[] sito;
	
	public Sito(int size) {
		sito = new boolean[size];

		mkSieve();
	}
	
	public void mkSieve() {
		for(int i = 2; i < sito.length; i++) {
			if(sito[i]) continue;
			int k = i;
			while(true) {
				k += i;
				if(k < sito.length) {
					sito[k] = true;
				} else break;
				
			}
		}
	}
	
	public int ilePierwszych() {
		int size = 0;
		for(int i = 2; i < sito.length; i++) {
			if(!sito[i]) size++;
		}
		return size;
	}
	
	public void wypisz(int start, int end) {
		for(int i = start; i < end; i++) {
			if(!sito[i]) System.out.print(i + " ");
		}
		System.out.println();
	}
}
