package RS2016;

/*
 Zadanie 5.1. (2 pkt)
Podaj liczbę żywych sąsiadów dla komórki w drugim wierszu i dziewiętnastej kolumnie
w trzydziestym siódmym pokoleniu.
Zadanie 5.2. (4 pkt)
Podaj liczbę żywych komórek w drugim pokoleniu tego układu.
Zadanie 5.3. (4 pkt)
W którym pokoleniu (sprawdzamy maksymalnie do 100) układ żywych i martwych komórek
się ustali (w bieżącym pokoleniu jest identyczny jak w poprzednim)?
Podaj, które to pokolenie oraz liczbę żywych komórek w tym pokoleniu.
 */

public class Main {

	public static void main(String[] arg) {
		System.out.println("Game of life");
		GameOfLife game = new GameOfLife("/home/ms/gra.txt");

		assert (game.getLifes() == 7);
		assert (game.getCountNeighbor(5, 9) == 5);
		assert (game.getCountNeighbor(5, 10) == 3);
		assert (game.getCountNeighbor(6, 9) == 5);
		assert (game.getCountNeighbor(7, 9) == 3);
		assert (game.getCountNeighbor(8, 10) == 1);
		assert (game.getCountNeighbor(10, 1) == 1);
		
		boolean notReady = true;
		for (int i = 1; i < 101; i++) {

			if (i == 2) {
				System.out.println("Zadanie 5,2. W drugim żywych komórek: " + game.getLifes());
				//game.draw();
			}
			if (i == 37) {
				System.out
						.println("Zadanie 5,1. W 2 rzędzie i 19 kolumnie sąsiadów:  " + game.getCountNeighbor(2, 19));
				//game.draw();

			}
			if (notReady && game.getTheSame()){
				notReady = false;
				System.out.println("Zadanie 5,3. Pokolenie powtórzone:  " + i);
			}
			game.next();
		}

	}

}
