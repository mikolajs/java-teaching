package RS2016;

public class Zadanie4 {
	final int maxTime = 1500;
	double water = 1000;
	double substantion = 4000;
	int time = 0;
	double poured = 0;
	double water191 = 0;
	double substantion191 = 0;

	public void run() {
		System.out.println("Zadanie 4.\n Dane do wykresu (4.3):");
		System.out.println("Czas\tSubstancja\tWoda");
		while (time <= maxTime) {
			System.out.println(
					String.format("%d\t%.2f\t%.2f", time, 
							substantion, water));
			if (time == 191) {
				water191 = water;
				substantion191 = substantion;
			}
			if (time == 1500) {
				System.out.println(String.format("4.2 Na końcu stężenie: %.2f, wolano razem wody %.2f", 
			 100.0*(substantion / (substantion + water)), poured));
				System.out.println(
						String.format("4.1 191 minucie  roztworu: %.2f, wody %.2f, substancji %.2f",
								(substantion191 + water191),
							 water191, substantion191));
			}
			step(20.0);
		}
		System.out.println(String.format("4.4 Maksymalne dolewanie to: %.2f",prediction()));

	}

	private void step(double add) {
		time++;
		double p = 0;
		double concentration = substantion / (substantion + water);
		if (time % 2 == 0) {
			substantion -= add* concentration;
			water -= add * (1.0 - concentration);
		} else if (time > 1 && time % 50 == 1) {
			p = 5000.0 - water - substantion;
			water += p;
			poured += p;
		}
	}
	
	private double prediction() {
			
		double delta = 20.0;
		double concentration = 80.0;
		while(concentration >= 1.0) {
			water = 1000;
			substantion = 4000;
			time = 0;
			delta += 0.01;
			while(time < maxTime) step(delta);
			concentration = 100.0*(substantion/(substantion+water));
			//System.out.println(concentration + " in time " + time + " delta " + delta);
		}
		return delta - 0.01 ;
	}

}
