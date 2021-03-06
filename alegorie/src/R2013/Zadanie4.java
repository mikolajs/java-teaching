package R2013;

/* zadanie 308 */
public class Zadanie4 {
	// 1.12.2012 sobota 5
	// 28.02.2013 dzień 90-ty
	final int bufflo = 90;
	final int feedHay = 40;
	final int feedAcorns = 20;
	final int startAcorns = 5000;
	final int startHay = 100000;
	private int acorns = 5000;
	private int hay = 100000;
	private int dayOfWeek = 5;// sobota

	private int acronSupply = 0;
	private int haySupply = 0;
	private int firstDayAcron = 0;
	private int acronFed = 0;
	private int hayFed = 0;
	private int[] dArray;

	public static void main(String[] args) {

		Zadanie4 zad4 = new Zadanie4();
		System.out.println("Zadanie 4");
		System.out.println(String.format("a) dostawy siano: %d żołędzie: %d ", zad4.a_hay(), zad4.a_acron()));
		System.out.println("b) pierwszy raz żołędzie w dniu: " + zad4.b());
		System.out.println(String.format("c) karmione sianem: %d, żołędziami: %d", zad4.c_hay(), zad4.c_acron()));
		int[] d = zad4.d();
		System.out.println(String.format("d) zapasy: 31.12.2012 siano %d, żołędzie %d,", d[0], d[1]));
		System.out.println(String.format("d) zapasy: 31.01.2013 siano %d, żołędzie %d,", d[2], d[3]));
		System.out.println(String.format("d) zapasy: 28.02.2013 siano %d, żołędzie %d,", d[4], d[5]));
		System.out.println("e) maksymalna ilość żubrów " + zad4.e());
	}

	public Zadanie4() {
		acronSupply = 0;
		haySupply = 0;
		dArray = new int[6];
		simulation();
	}

	private void simulation() {
		acorns = startAcorns;
		hay = startHay;
		boolean firstAcron = false;

		for (int i = 0; i < 90; i++) {
			if (i == 30) {
				dArray[0] = hay;
				dArray[1] = acorns;
			}
			if (i == 61) {
				dArray[2] = hay;
				dArray[3] = acorns;
			}
			if (i == 89) {
				dArray[4] = hay;
				dArray[5] = acorns;
			}

			if (hay >= 50000) {
				hay -= bufflo * feedHay;
				hayFed++;
			} else {
				if (!firstAcron) {
					firstAcron = true;
					firstDayAcron = i + 1;
				}
				acorns -= bufflo * feedAcorns;
				acronFed++;
			}

			if (dayOfWeek == 4) {
				hay += 15000;
				haySupply++;
			}
			if (dayOfWeek == 1) {
				acorns += 4000;
				acronSupply++;
			}
			dayOfWeek = ++dayOfWeek % 7;
		}
	}

	private boolean simulationMax(int buffloMax) {
		acorns = startAcorns;
		hay = startHay;
		dayOfWeek = 5;

		for (int i = 0; i < 90; i++) {
			if (acorns < buffloMax * 20 && hay < 50000)
				return false;
			if (hay >= 50000)
				hay -= buffloMax * feedHay;
			else
				acorns -= buffloMax * feedAcorns;

			if (dayOfWeek == 4)
				hay += 15000;
			if (dayOfWeek == 1)
				acorns += 4000;

			dayOfWeek = ++dayOfWeek % 7;
		}

		return true;
	}

	public int a_hay() {
		return haySupply;
	}

	public int a_acron() {
		return acronSupply;
	}

	public int c_hay() {
		return hayFed;
	}

	public int c_acron() {
		return acronFed;
	}

	public int b() {
		return firstDayAcron;
	}

	public int[] d() {
		return dArray;
	}

	public int e() {
		int n = 90;
		while (simulationMax(++n)) {
		}
		return n - 91;
	}

}
