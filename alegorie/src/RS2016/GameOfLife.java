package RS2016;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * Komórka umiera gdy nie ma dwóch lub trzech sąsiadów
 * Komórka ożywa gdy ma dokładnie 3 sąsiadów moze być po przekątnej
 * plansza jest nieskończona
 */
public class GameOfLife {
	private char[][] map;
	private int sizeX;
	private int sizeY;
	private boolean theSame = false;
	
	public GameOfLife(int size){
		sizeX = size;
		sizeY = size;
		map = new char[sizeY][];
		for(int i = 0; i < sizeY; i++){
			map[i] = new char[sizeX];
			for(int j = 0; j < sizeX; j++) {
				map[i][j] = '.';
			}
		}
	}
	
	public GameOfLife(String path) {
		readFromFile(path);
	}
	
	public void setLiving(int x, int y){
		if(x >= sizeX || y >= sizeY ){
			System.out.println("Za duże wartości x lub y!");
			return;
		}
		map[x][y] = 'X';
	}
	
	public void next(){
		char[][] newMap;
		newMap = new char[sizeY][];
		for(int j = 0; j < sizeY; j++) newMap[j] = new char[sizeX];
		int neighbours = 0;
		for(int i = 0; i < sizeY; i++)
			for(int j = 0; j < sizeX; j++){
				neighbours = countNeighbor(j, i);
				//System.out.println("Neighbors: " + neighbours);
				if(map[i][j] == '.') 
					if (neighbours == 3) newMap[i][j] = 'X'; else newMap[i][j] = '.';
				else if(neighbours == 2 || neighbours == 3 )  newMap[i][j] = 'X'; else newMap[i][j] = '.';
			}
		theSame = isTheSame(newMap);
		map = newMap;
	}
	
	public void draw(){
		for(int i = 0; i < sizeY; i++){
			for(int j = 0; j < sizeX; j++){
				System.out.print(map[i][j]);
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
	public int getCountNeighbor(int row, int col){
		return countNeighbor(col -1, row -1);
	}
	
	public int getLifes(){
		int lifes = 0;
		for(int i = 0; i < sizeY; i++)
			for(int j = 0; j < sizeX; j++) 
				if(map[i][j] == 'X') lifes++;
		return lifes;
	}
	
	public boolean getTheSame()  {return theSame;}
	
	private boolean isTheSame(char[][] tab){
		for(int i = 0; i < sizeY; i++)
			for(int j = 0; j < sizeX; j++) 
				if(tab[i][j] != map[i][j] ) return false;
		return true;
	}
	
	private int countNeighbor(int x, int y){
		int neighbors = 0;
		int X, Y;
		for(int i = -1; i <= 1; i++)
			for(int j = -1; j <= 1; j++){
				if(i == 0 && j == 0) continue;
				X = x + i; 
				Y = y + j;
				if(X < 0) X = sizeX -1;
				if(X >= sizeX) X = 0;
				if(Y < 0) Y = sizeY - 1;
				if(Y >= sizeY) Y = 0;
				if(map[Y][X] == 'X') neighbors++;
			}
		return neighbors;
	}
	
	private void readFromFile(String path) {
		String[] data;
		try {
			data = new String(Files.readAllBytes(Paths.get(path))).split("\n");
			if(!testData(data)) {
				System.out.println("Nieprawidłowe dane");
				return;
			}
			sizeY = data.length;
			map = new char[sizeY][];
			sizeX = data[0].trim().length();
			System.out.println("Y " + sizeY + " X " + sizeX);
			for(int i = 0; i < sizeY; i++){
				map[i] = new char[sizeX];
				for(int j = 0; j < data[i].trim().length(); j++){
					map[i][j] =  data[i].charAt(j);
				}		
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private boolean testData(String[] data){
		//int yLen = data.length;
		int xLen = data[0].trim().length();
		//System.out.println("number of lines: " + yLen);
		for(String s : data){
			//System.out.println("line size: " + s.trim().length());
			if(xLen != s.trim().length()) return false;
		}
		return true;
	}
	
}
