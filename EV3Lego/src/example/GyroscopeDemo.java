package example;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

public class GyroscopeDemo {

    public static void main(String[] args) {
        EV3 ev3 = (EV3) BrickFinder.getLocal();
        TextLCD lcd = ev3.getTextLCD();
        Port port = LocalEV3.get().getPort("S1");
        GyroscopeSensor gyro = new GyroscopeSensor(port);
        lcd.drawString("Wcisnij przycisk ", 1, 1);
        Button.waitForAnyPress();
        Button.LEDPattern(4);
        float angle;
        while(true) {
            angle = gyro.getAngle();
            lcd.drawString(String.format("angle= %f",angle), 1, 3);
            if(angle > 5.0f) Sound.beepSequenceUp();
            Delay.msDelay(1000); 
//            if(angle < 0.1f) break;
            if(Button.ESCAPE.isDown()) break;
        }
        gyro.close();
    }
}
