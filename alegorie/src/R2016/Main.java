package R2016;

public class Main {

	public static void main(String[] args) {
		Zadanie4 zad4 = new Zadanie4();
//		zad4.printDataInLine(1);
//		zad4.printDataInLine(10000);
		zad4.printPointPlacesAll();
		zad4.printEdgePoints();
		zad4.printPI(100);
		zad4.printPI(1000);
		zad4.printPI(5000);
		zad4.printPI(10000);
		zad4.createCSVErrorFile();
		
		Zadanie6 zad6 = new Zadanie6();
		System.out.println("Wyniki zadania 6 drukowane do pliku");
		zad6.encryptStrings();
		zad6.decryptStrings();
		zad6.checkStrings();
	}

}
