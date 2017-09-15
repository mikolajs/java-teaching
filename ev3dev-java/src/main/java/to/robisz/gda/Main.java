package to.robisz.gda;

 import org.ev3dev.hardware.lcd.LCD;
 import org.ev3dev.hardware.lcd.LCDGraphics;
                         
 public class Main { 
                    
  public static void main(String[] args){
       LCD lcd = new LCD();               
       LCDGraphics g = new LCDGraphics(lcd); 
       g.drawString("Hello World!", 50, 50); 
       g.flush(); 
  }
}
