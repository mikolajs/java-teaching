import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		part4();
	}
	
	public static void part3() {
		// TODO Auto-generated method stub
		System.out.println("podaj dwie liczby całkowite");
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		System.out.println("dodawanie a + b = " + (a + b));
		System.out.println("odejmowanie a - b = " + (a - b));
		System.out.println("mnożenie a * b = " + (a * b));
		System.out.println("dzielenie całkowite a / b = " + (a / b));
		System.out.println("reszta z dzielenia a % b = " + (a % b));
		System.out.println(" a wynosi: " + a);
		System.out.println("postincrementacja a++ = " + a++ );
		System.out.println("preincrementacja ++a = " + ++a );
		System.out.println("postdecrementacja a-- = " + a-- );
		System.out.println("predecrementacja --a = " + --a );
		System.out.println("Powiększanie zmiennej od wartość: a += 5 to to samo co a = a + 5 ");
		a += 5;
		System.out.println("zmienna a po zwiększeniu o 5: " + a);
		
		double c = (double) a;
		double d = (double) b;
		System.out.println("dodawanie zmiennoprzecinkowe c + d = " + (c + d));
		System.out.println("odejmowanie  zmiennoprzecinkowe c - d = " + (c - d));
		System.out.println("mnożenie  zmiennoprzecinkowe c * d = " + (c * d));
		System.out.println("dzielenie  zmiennoprzecinkowe c / d = " + (c / d));
		
		System.out.println("wpisz znak (liczbę lub cyfrę");
		char l = sc.next().charAt(0);
		System.out.println("Pod znakiem " + l  + " kryje się liczba " + ((int) l));
		System.out.println("Wpisz jakiś napis i wciśnij enter: );");
		String s1 = sc.next();
		System.out.println("Wpisz drugi napis i wciśnij enter: " );
		String s2 = sc.next();
		System.out.println("Wpisałeś: " + s1  + " " + s2);
		sc.close();
	}
	
	public static void part4() {
		System.out.println("losujemy ile ocen wylosować");
		Random generator = new Random();
		int ile = generator.nextInt(1000_000);
		System.out.println("Będziemy losować " + ile + " liczb.");
		int suma = 0;
		for(int i = 0; i < ile; i++) {
			suma += generator.nextInt(6) + 1;
		}
		System.out.println("Suma ocen " + suma);
		System.out.printf("Średnia ocen %.2f \n", ((double) suma / (double) ile) );
	}

}


