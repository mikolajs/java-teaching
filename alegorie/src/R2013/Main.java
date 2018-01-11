package R2013;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * Matura 2013 rozszerzona
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		Zadanie6 zad6 = new Zadanie6();
		System.out.println("Zadanie 6" );
		System.out.println("a) ilość liczb z taką samą pierwszą i ostatnią cyfrą w 8: " + zad6.a());
		System.out.println("b) ilość liczb z taką samą pierwszą i ostatnią cyfrą w 10: " + zad6.b());
		System.out.println("b) ilość liczb z nie mniejszymi kolejnymmi cyframi: " + zad6.c());
		System.out.println(String.format("c) największa %s i najmniejsza %s taka liczba", zad6.getMax(), zad6.getMin()));
		
		Zadanie4 zad4 = new Zadanie4();
		System.out.println("Zadanie 4" );
		System.out.println(String.format("a) dostawy siano: %d żołędzie: %d ", zad4.a_hay(), zad4.a_acron()));
		System.out.println("b) pierwszy raz żołędzie w dniu: " + zad4.b());
		System.out.println(String.format("c) karmione sianem: %d, żołędziami: %d", zad4.c_hay(), zad4.c_acron()));
		int[] d = zad4.d();
		System.out.println(String.format("d) zapasy: 31.12.2012 siano %d, żołędzie %d,", d[0], d[1])); 
		System.out.println(String.format("d) zapasy: 31.01.2013 siano %d, żołędzie %d,", d[2], d[3])); 
		System.out.println(String.format("d) zapasy: 28.02.2013 siano %d, żołędzie %d,", d[4], d[5])); ;
		System.out.println("e) maksymalna ilość żubrów " + zad4.e());
		
	}
}
