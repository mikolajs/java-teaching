package R2017;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

/* Słodzik */
public class Zadanie4 {

	double[] prices;
	Sold[] solds;

	public static void main(String[] args) {
		Zadanie4 zad = new Zadanie4();
		zad.loadFile();
		zad.solve1();
		zad.solve2();
		zad.solve3();
		zad.solve4();
		zad.solve5();
	}

	private void solve1() {
		Map<String, Integer> map = new TreeMap<>();
		int tmp;
		for (Sold s : solds) {
			if (map.containsKey(s.nip)) {
				tmp = map.get(s.nip);
				tmp += s.nr;
				map.put(s.nip, tmp);
			} else {
				map.put(s.nip, s.nr);
			}
		}
		System.out.println("1)  ");
		int[] maxi = map.values().stream().sorted((m1, m2) -> Integer.compare(m2, m1)).limit(3).mapToInt(i -> i)
				.toArray();
		Set<String> keys = map.keySet();
		int max;
		for (String nip : keys) {
			max = map.get(nip);
			for (int i : maxi) {
				if (max == i)
					System.out.println(nip + " " + i);
			}
		}

	}

	private void solve2() {
		int[] years = new int[10];
		Calendar calendar = new GregorianCalendar();
		for (Sold s : solds) {
			calendar.setTime(s.d);
			years[calendar.get(Calendar.YEAR) - 2005] += s.nr;
		}
		double income = 0.0;
		for (int i = 0; i < years.length; i++) {
			income += years[i] * prices[i];
		}
		System.out.println("2) Dochód: " + income);
	}

	private void solve3() {
		int[] years = new int[10];
		Calendar calendar = new GregorianCalendar();
		for (Sold s : solds) {
			calendar.setTime(s.d);
			years[calendar.get(Calendar.YEAR) - 2005] += s.nr;
		}
		System.out.println("3) Sprzedano: ");
		for (int i = 0; i < years.length; i++) {
			System.out.println((i + 2005) + "\t" + years[i]);
		}
	}

	private void solve4() {
		Map<String, Integer> map = new TreeMap<>();
		int tmp;
		double discount = 0;
		for (Sold s : solds) {
			if (map.containsKey(s.nip)) {
				tmp = map.get(s.nip);
				tmp += s.nr;
			} else {
				tmp = s.nr;
			}
			if (tmp >= 100) {
				if (tmp < 1000)
					discount += s.nr * 0.05;
				else if (tmp < 10000)
					discount += s.nr * 0.1;
				else
					discount += s.nr * 0.2;
			}
			map.put(s.nip, tmp);

		}
		System.out.println("4)  Zniżki: " + discount);
	}

	private void solve5() {
		int month = 1;
		int m;
		int add;
		int state = 5000;
		int above4000 = 0;
		Calendar calendar = new GregorianCalendar();
		for (Sold s : solds) {
			calendar.setTime(s.d);
			m = calendar.get(Calendar.MONTH);
			if (m == month)
				state -= s.nr;
			else {
				month = m;
				add = 5 - state / 1000;
				
				if (add > 3)
					above4000++;
				state += 1000 * add;
				state -= s.nr;
			}
		}
		add = 5 - state / 1000;
		if (add > 3)
			above4000++;
		state += 1000 * add;
		System.out.println("5)   " + above4000);
	}

	private void loadFile() {
		Scanner sc = null;
		prices = new double[10];
		solds = new Sold[2162];
		int counter = 0;
		try {
			sc = new Scanner(new File("dane/R2017/cennik.txt"));
			while (sc.hasNextDouble()) {
				if (counter < prices.length) {
					sc.nextInt();
					prices[counter++] = sc.nextDouble();
				} else
					System.out.println("Za dużo linii??? cennik");
			}
			sc.close();
			sc = new Scanner(new File("dane/R2017/cukier.txt"));
			String[] line;
			DateFormat formatter = new SimpleDateFormat("yy-MM-dd");
			counter = 0;
			while (sc.hasNextLine()) {
				if (counter < solds.length) {
					line = sc.nextLine().split("\t");

					solds[counter] = new Sold();
					solds[counter].d = formatter.parse(line[0].trim());
					solds[counter].nip = line[1].trim();
					solds[counter].nr = Integer.parseInt(line[2].trim());
					counter++;
				} else
					System.out.println("Za dużo linii??? cukier");
			}
		} catch (IOException e) {
			System.out.println("brak pliku");
		} catch (ParseException pe) {
			System.out.println("Nieprawidłowy format daty");

		} finally {
			if (sc != null)
				sc.close();
		}
	}

}

class Sold {
	public Date d;
	public String nip;
	public int nr;

	public String toString() {
		return d.toString() + " " + nip + " " + nr;
	}
}

class Buyers {
	public String nip;
	public int amount;
}
