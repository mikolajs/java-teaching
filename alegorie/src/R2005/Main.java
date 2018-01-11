
/*
 * Matura 2005
 */
package R2005;

import java.text.DecimalFormat;

public class Main {
	public static void main(String[] args) {
		System.out.println("Mikołaj Sochacki");
		System.out.println("Zadanie 4");
		Zadanie4 zad4 = new Zadanie4();
		System.out.println("A Przedziały opłacalności: ");
		System.out.println(String.format("D1 [ %.2f ; %.2f ]", zad4.getD1RangeStart(), zad4.getD1RangeEnd()));
		System.out.println(String.format("D2 [ %.2f ; %.2f ]", zad4.getD2RangeStart(), zad4.getD2RangeEnd()));
		System.out.println("Firma\t\t100\t\t1000\t\t1000");
		System.out.println(String.format("D1\t\t %.2f \t\t %.2f\t\t %.2f", 
				zad4.getD1cost(100), zad4.getD1cost(1000), zad4.getD1cost(5000)));
		System.out.println(String.format("D2\t\t %.2f \t\t %.2f \t\t %.2f",  
				zad4.getD2cost(100), zad4.getD2cost(1000), zad4.getD2cost(5000)));
		System.out.println("B tabela kosztów licencji i obliczeń dla D2 ");
		System.out.println("dane\t|\tLicencja\t|\tObliczenia");
		System.out.println("--------------------------------------------------");
		double[] arr = zad4.getD2Table();
		DecimalFormat df = new DecimalFormat("#.00");
	    for(int i = 0; i < arr.length; i+= 2){
		    System.out.println(String.format( " %d \t|\t %8s \t|\t %8s", 
		    		((i/2)*100 + 6000), df.format(arr[i]), df.format(arr[i+1])));
		}	
		System.out.println("C ");
		System.out.println("Ubuntu 16.04 LTS / Linux");
		System.out.println("Otworzyć Nautylius, PPM na komputer i wybrać właściowści. Okno pokaże wykres kołowy rozmiaru dysku i wolnego miejsca");

	}
}
