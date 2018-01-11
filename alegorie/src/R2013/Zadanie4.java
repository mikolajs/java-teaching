package R2013;
public class Zadanie4 {
	//1.12.2012 sobota 5
	//28.02.2013 dzień 90-ty
	final int bufflo = 90;
	final int feedHay = 40;
	final int feedAcorns = 20;
	final int startAcorns = 5000;
	final int startHay = 100000;
	private int acorns = 5000;
	private int hay = 100000;
	private int dayOfWeek = 5;//sobota 
	
	private int acronSupply = 0;
	private int haySupply = 0;
	private int firstDayAcron = 0;
	private int acronFed = 0;
	private int hayFed = 0;
	private int[] dArray;

	public Zadanie4(){
		acronSupply = 0;
		haySupply = 0;
		dArray = new int[6];
		simulation();
	}
	
	private void simulation(){
		acorns = startAcorns;
		hay = startHay;
		boolean firstAcron = false;
		
		for(int i = 0; i < 90; i++){
			if(i == 30) {
				dArray[0] = hay;
				dArray[1] = acorns;
			}
			if(i == 61) {
				dArray[2] = hay;
				dArray[3] = acorns;
			}
			if(i == 89) {
				dArray[4] = hay;
				dArray[5] = acorns;
			}
			
			if(hay >= 50000) {
				hay -= bufflo*feedHay;
				hayFed++;
			}
			else {
				if(!firstAcron) {
					firstAcron = true;
					firstDayAcron = i + 1;
				}
				acorns -= bufflo*feedAcorns;
				acronFed++;
			}
			
			
			
			if(dayOfWeek == 4) {
				hay += 15000;
				haySupply++;
			}
			if(dayOfWeek == 1) {
				acorns += 4000;
				acronSupply++;
			}
			dayOfWeek = ++dayOfWeek % 7;
		}
	}
	
	private boolean simulationMax(int buffloMax){
		acorns = startAcorns;
		hay = startHay;
		dayOfWeek = 5;
		
		for(int i = 0; i < 90; i++){
			if(acorns < buffloMax*20 && hay < 50000) return false;
			if(hay >= 50000) hay -= buffloMax*feedHay;
			else acorns -= buffloMax*feedAcorns;

			if(dayOfWeek == 4) hay += 15000;
			if(dayOfWeek == 1) acorns += 4000;

			dayOfWeek = ++dayOfWeek % 7;
		}
		
		return true;
	}
	
	public int a_hay(){
		return haySupply;
	}
	public int a_acron(){
		return acronSupply;
	}
	
	public int c_hay(){
		return hayFed;
	}
	public int c_acron(){
		return acronFed;
	}
	
	public int b(){
		return firstDayAcron;
	}
	public int[] d(){
		return dArray;
	}
	public int e(){
		int n = 90;
		while(simulationMax(++n)){}
		return n - 91;
	}
	
}
