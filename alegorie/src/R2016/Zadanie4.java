package R2016;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Zadanie4 {
	private MyPoint[] tab;
	private int r2 = 40000;
	private int circlePoints = 0;
	private int edgePoints = 0;
	private MyPoint[] edgeCord;
	
	public Zadanie4() {
		edgeCord = new MyPoint[100];
		String str = "";
		try {
		  str = new String(Files.readAllBytes(Paths.get("./punkty.txt")));
		} catch (IOException e){
			e.printStackTrace();
		}
		String[] arr = str.split("\n");
		
		assert arr.length == 10000;
		
		tab = new MyPoint[arr.length];
		for(int i = 0; i < tab.length; i++){
			String[] pair = arr[i].split(" ");
			MyPoint p = new MyPoint(Integer.valueOf(pair[0].trim()).intValue(),
					Integer.valueOf(pair[1].trim()).intValue());
			//System.out.println(String.format("point (%d;%d)", p.x, p.y));
			tab[i] = p;
		};
	}
	public void printDataInLine(int line){		
		line--;
		System.out.println(String.format("point (%d;%d)", tab[line].x, tab[line].y));	
	}
	
	public void printPointPlacesAll(){
		countPointsPlace(10000);
		System.out.println(
				String.
				format("Dla wszystkich punktów, punkty na okręgu: %d, punkty w kole: %d", edgePoints, circlePoints));
	}
	
	public void printPI(int points) {
		countPointsPlace(points);
		System.out.println(String.format("Wyliczone dla %d punktów - PI: %.4f", points, Pi(points)));
	}
	
	public void createCSVErrorFile(){
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i <= 1700; i++){
			countPointsPlace(i);
			sb.append(String.format("%d;%.4f\n", i, Math.abs(Math.PI - Pi(i))));
		}
		try {
		Files.write(Paths.get("./errors.csv"), sb.toString().getBytes() );
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void printEdgePoints(){
		System.out.println("Punkty brzegowe");
		for(int i = 0; i < edgePoints; i++){
			System.out.println(String.format("(%d;%d)", edgeCord[i].x, edgeCord[i].y));
		}
	}
	
	public void printErrorPi(int points){
		System.out.println(String.format("Bład dla %d wynosi: %.4f", points));
	}
	
	private void countPointsPlace(int points){
		edgePoints = 0;
		circlePoints = 0;
		int radius = 0;
		for(int i = 0; i < points; i++){
			radius =  (tab[i].x -200)*(tab[i].x -200) + (tab[i].y -200)*(tab[i].y -200);
			if(radius == r2) {
				edgeCord[edgePoints++] = tab[i];			
			}
			else if(radius < r2) circlePoints++;
		}
	}
	
	private double Pi(int points) {
		double squareCircle = ((double) (circlePoints + edgePoints) * 160000.0) / (double)(points);
		return squareCircle / 40000.0;
	}
	
}

