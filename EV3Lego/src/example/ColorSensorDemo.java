package example;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

public class ColorSensorDemo {

	 public static void main(String[] args) {
	        
	        EV3 ev3 = (EV3) BrickFinder.getLocal();
	        TextLCD lcd = ev3.getTextLCD();
	        Port port = LocalEV3.get().getPort("S4");
	        ColorSensorRGB sensor = new ColorSensorRGB(port);
	        lcd.drawString("Wcisnij przycisk ", 1, 1);
	        Button.waitForAnyPress();
	        Button.LEDPattern(4);
	        int r, g, b;
	        for(;;) {
	            r = sensor.getRed();
	            g = sensor.getGreen();
	            b = sensor.getBlue();
	            lcd.drawString(
	            		String.format("rgb(%d, %d, %d)", r, g, b), 1, 3);
	            Delay.msDelay(500); 
	            if(Button.ESCAPE.isDown()) {
	            	break;
	            }
	        }
	        sensor.close();
	    }
}
