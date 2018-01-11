package R2013;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Zadanie6 {
	private String[] data;
	private String min = "7777777";
	private String max = "0";
	
	public Zadanie6() {
		try {
		String d = new String(Files.readAllBytes(Paths.get("./dane.txt")));
		this.data = d.split("\n");
		assert (data.length == 5000);
		mkTrimmed();
		} catch (IOException e){
			System.out.println("Nieudane otwarcie pliku");
		}
	}
	
	public int a(){
		int n = 0;
		for(String s : this.data){
			if(s.charAt(0) == s.charAt(s.length()-1)) n++;
			//System.out.println(s.charAt(0) + " " + s.charAt(s.length() -1));
		}
		return n;
	}
	
	public int b(){
		int n = 0;
		String d;
		for(String s : this.data){
			d = Integer.valueOf(s, 8).toString();
			if(d.charAt(0) == d.charAt(d.length()-1)) n++;
		}
		return n;
	}
	
	public int c(){
		int n = 0;
		int d;
		boolean good = false;
		char[] ch;
		for(String s : this.data){
			d = Integer.valueOf(s, 8);
			good = true;
			ch = s.toCharArray();
			for(int i = 1; i < ch.length; i++){
				if(ch[i] < ch[i -1]) {
					good = false;
					break;
				}
			}
			if(good) {
				n++;
				if(Integer.valueOf(this.min, 8) > d) this.min = s;
				else if(Integer.valueOf(this.max, 8) < d) this.max = s;
			}
		}
		return n;
	}
	
	public String getMax(){
		return this.max;
	}
	
	public String getMin(){
		return this.min;
	}
	
	private void mkTrimmed(){
		for(int i = 0; i < data.length; i++){
			data[i] = data[i].trim();
		}
	}

}
