
package example;

import lejos.hardware.BrickFinder;
// http://stemrobotics.cs.pdx.edu/node/5198
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.Port;
import lejos.utility.Delay; 

public class UltrasonicDemo {
    
    public static void main(String[] args) {
        
        EV3 ev3 = (EV3) BrickFinder.getLocal();
        TextLCD lcd = ev3.getTextLCD();
        Port port = LocalEV3.get().getPort("S1");
        UltraSonicSensor sonar = new UltraSonicSensor(port);
        lcd.drawString("Wcisnij przycisk ", 1, 1);
        Button.waitForAnyPress();
        Button.LEDPattern(4);
        float range;
        for(;;) {
            range = sonar.getRange();
            lcd.drawString(String.format("dist= %f",range), 1, 3);
            if(range < 1.0f) Sound.beepSequenceUp();
            Delay.msDelay(1000); 
            if(range < 0.1f) break;
        }
        sonar.close();
    }

}
