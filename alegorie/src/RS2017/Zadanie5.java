package RS2017;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.*;


public class Zadanie5 {
	final private LocalDate startDate;
	final private LocalDate endDate;
	private LocalDate date;
	private LocalDate lowWoodDate = null;
	private boolean morning = false;
	private int wood = 550;
	final private int woodUse = 26;
	private int eveningWood = 0;
	private int addWood = 0;
	final private int allDays;
	
	public static void main(String[] args) {
		Zadanie5 z = new Zadanie5();
		System.out.println(z);
	}

	public Zadanie5() {
		startDate = LocalDate.of(2015, Month.SEPTEMBER, 15);
		endDate = LocalDate.of(2016, Month.APRIL, 1);
		date = LocalDate.of(2015, Month.SEPTEMBER, 15);
		allDays = (int) (endDate.toEpochDay() - startDate.toEpochDay());
		loop();
		// dane csv do wykresu w arkuszu
		solv3();
	}

	private void loop() {
		DayOfWeek dow;
		while (date.isBefore(endDate)) {
			dow = date.getDayOfWeek();
			if (dow.equals(DayOfWeek.SATURDAY) || dow.equals(DayOfWeek.SUNDAY)) {
				// rano
				if (wood >= 26) {
					wood -= 26;
					if (lowWoodDate == null && wood < 100) {
						lowWoodDate = LocalDate.ofEpochDay(date.toEpochDay());
						morning = true;
					}
				}
				// wieczorem
				if (wood >= 26) {
					wood -= 26;
					eveningWood++;
					if (lowWoodDate == null && wood < 100)
						lowWoodDate = LocalDate.ofEpochDay(date.toEpochDay());
				}
			} else {
				// dostarczenie drewna
				if (dow.equals(DayOfWeek.FRIDAY)) {
					if (wood < 100) {
						wood += 300;
						addWood++;
					}
				}
				// wieczorem
				if (wood >= 26) {
					wood -= 26;
					eveningWood++;
					if (lowWoodDate == null && wood < 100)
						lowWoodDate = LocalDate.ofEpochDay(date.toEpochDay());

				}

			}
			date = date.plusDays(1);
		}
	}

	private int loop2() {
		DayOfWeek dow;
		boolean shortAmount = true;
		int add = 300;
		while (shortAmount) {
			shortAmount = false;
			date = LocalDate.ofEpochDay(startDate.toEpochDay());
			wood = 550;
			while (date.isBefore(endDate)) {
				dow = date.getDayOfWeek();
				if (dow.equals(DayOfWeek.SATURDAY) || dow.equals(DayOfWeek.SUNDAY)) {
					// cały dzień 2 razy
					if (wood >= 52) {
						wood -= 52;
					} else {
						shortAmount = true;
						break;
					}
				} else {
					// dostarczenie drewna
					if (dow.equals(DayOfWeek.FRIDAY) && wood < 100)
						wood += add;
					if (wood >= 26)
						wood -= 26;
					else {
						shortAmount = true;
						break;
					}
					;
				}
				date = date.plusDays(1);
			}
			if (shortAmount)
				add++;
		}
		return add;
	}

	private String solv1() {
		String when = "wieczorem";
		if (morning)
			when = "rano";
		return String.format("1) \n Poziom drewna po raz pierwszy poniżej 100kg: ")
				+ String.format("dnia %s ", lowWoodDate.toString()) + String.format("%s \n", when);
	}

	private String solv2() {
		return String.format("2) \n Dostarczono drewno %d razy \n", addWood)
				+ String.format("Ogrzewanie wieczorem drewnem: %d razy, gazem %d razy \n", 
						eveningWood, allDays - eveningWood);
	}

	private void solv3() {
		String str = "";
		try {
			Files.write(Paths.get("woods.csv"), str.getBytes());
		} catch (IOException e) {
			System.out.println("Nie można otworzyć pliku do zapisu");
		}
	}

	private String solv4() {
		return String.format("4)\n Ilość miniamalna dostaw %d " , loop2()) ;
	}

	@Override
	public String toString() {
		return solv1() + solv2() + solv4();
	}
}
