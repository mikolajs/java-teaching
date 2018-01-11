package R2010;

/*
 * 
 */
public class Main {
	public static void main(String[] args) {
		System.out.println("Zadanie 4: Anagramy");
		Zadanie4 zad4 = new Zadanie4();
		System.out.println(String.format("A) linii z równymi długością napisami: %d", zad4.a()) );
		System.out.println(String.format("B) linii z samymi anagramami: %d", zad4.b()) );
		Zadanie5 zad5 = new Zadanie5();
		System.out.println("Zadanie 5: PESELe");
		System.out.println("A) Urodzeni w grudniu: " + zad5.a());
		System.out.println("B) Pracuje kobiet: " + zad5.b());
		System.out.println("C) Rok największej liczby urodzin: 19" + zad5.c());
		System.out.println("D) Nieprawidłowe pesele:");
		String[] s = zad5.d();
		for(String str : s){
			System.out.println(str);
		}
		System.out.println("E) Zestawienie urdzeń w dziesięcioleciach:");
		int[] dec = zad5.e();
		for(int i = 0; i < dec.length; i++){
			if(dec[i] > 0) {
				System.out.println(i+"0-te | " + dec[i]);
			}
		}
	}
}
