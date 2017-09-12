import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
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
		System.out.println("postincrementacja a++" + a++ );
		System.out.println("preincrementacja ++a" + ++a );
		System.out.println("postdecrementacja a--" + a-- );
		System.out.println("predecrementacja --a" + --a );
		
		double c = (double) a;
		double d = (double) b;
		System.out.println("dodawanie zmiennoprzecinkowe c + d = " + (c + d));
		System.out.println("odejmowanie  zmiennoprzecinkowe c - d = " + (c - d));
		System.out.println("mnożenie  zmiennoprzecinkowe c * d = " + (c * d));
		System.out.println("dzielenie  zmiennoprzecinkowe c / d = " + (c / d));
		
		/*
		 *  char l; cout << "Wpisz znak (literę lub cyfrę): " << endl; cin >> l ;
		 *   cout << "Naprawdę pod tą literą" << l << "kryje się liczba: " << static_cast<int>(l) << endl; 
		 *   string n1; cout << "Wpisz jakiś napis i wciśnij enter: " << endl; cin >> n1; 
		 *   string n2; cout << "Wpisz drugi napis i wciśnij enter: " << endl; cin >> n2; 
		 *  cout << "Wpisałeś taki napis: " << n1 + n2 << endl;
		 * 
		 */
		sc.close();
	}

}


