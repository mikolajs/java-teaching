package pl.edu.osp;

public class MainPolibiusz {
	public static void main(String[] args) {
		String kod = "BTLLTU_EL_EOYPM_APJZLCYNDREOKYLI_ZMFO_ĄGJY_O_N_DEWFWGISYSII_LEI_";
		int size = (int) Math.sqrt(kod.length());
		if(kod.length() % size*size != 0) size++;
		char[][] tab = new char[size][size];
		wstaw(tab, kod);
		printTab(tab);
		System.out.println(odczytaj(tab));
	}
	
	public static void wstaw(char [][] tab, String kod) {
		final int size = tab.length;
		int n = 0;
		for(int i = 0; i< size; i++)
			for(int j = 0; j < size;j++) {
				if(i*size + j < kod.length())
					tab[j][i] = kod.charAt(n++);
			}
	}
	public static String odczytaj(char [][] tab) {
		final int size = tab.length;
		String str = "";
		for(int i = 0; i< size; i++)
			for(int j = 0; j < size;j++) {
				str += tab[i][j];
			}
		return str;
	}
	
	public static void printTab(char [][] tab) {
		final int size = tab.length;
		for(int i = 0; i< size; i++) {
			for(int j = 0; j < size;j++) {
				System.out.print(tab[i][j]);
			}
			System.out.println();
		}
	}
}
