package example;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class LineFallower {
	public static void main(String[] args) {
		final int black = 150;
        EV3 ev3 = (EV3) BrickFinder.getLocal();
        TextLCD lcd = ev3.getTextLCD();
        RegulatedMotor mR = new EV3LargeRegulatedMotor(MotorPort.C);
	    RegulatedMotor mL = new EV3LargeRegulatedMotor(MotorPort.B);
        Port port = LocalEV3.get().getPort("S4");
        ColorSensorRGB sensor = new ColorSensorRGB(port);
        lcd.drawString("Wcisnij przycisk ", 1, 1);
        Button.waitForAnyPress();
        Button.LEDPattern(4);
        boolean isForward = true;
        int bright = 0; 
        while(true) {
            bright = sensor.getBrightness();
            if(bright <= black) {
            	if(!isForward) {
            		mR.stop(true);
                    mL.stop(true);
            	}	
            	mR.forward();
            	mR.forward();
            	Delay.msDelay(5);
            } else {
            	isForward = false;
            	mR.rotate(10, true);
            	mR.rotate(-10, false);
            }
            lcd.drawString("bright " + bright, 1, 3);
            //Delay.msDelay(500); 
            if(Button.ESCAPE.isDown()) {
            	break;
            }
        }
        sensor.close();
    }
}
