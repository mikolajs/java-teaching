package R2007;

public class Main {

	public static void main(String[] args) {
		Zadanie5 zad = new Zadanie5();
		System.out.println("przedział <2;100>" + zad.superPrimesCount(2, 100));
		System.out.println("przedział <2;1000>" + zad.superPrimesCount(2, 1000));
		System.out.println("przedział <1000;10000>" + zad.superPrimesCount(1000, 10000));
		System.out.println("przedział <10000;100000>" + zad.superPrimesCount(10000, 100000));
		
	}

}
