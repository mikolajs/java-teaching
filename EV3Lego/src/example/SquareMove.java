package example;

import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class SquareMove {

    public static void main(String[] args) {

        EV3 ev3 = (EV3) BrickFinder.getLocal();
        TextLCD lcd = ev3.getTextLCD();

        lcd.drawString("Mikolaj S", 20, 1);
        Keys keys = ev3.getKeys();
        
        RegulatedMotor mR = new EV3LargeRegulatedMotor(MotorPort.A);
        RegulatedMotor mL = new EV3LargeRegulatedMotor(MotorPort.B);
        Port port = LocalEV3.get().getPort("S4");
        
       
        mR.setSpeed(400);
        mL.setSpeed(400);
        mR.stop();
        mL.stop();
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 4; j++){
                mR.forward();
                mL.forward();
                Delay.msDelay(1000L);
                mR.stop();
                mL.stop();
                mR.rotate(190, false);
                mL.rotate(-190, false);
                Delay.msDelay(1000L);
                mR.stop();
                mL.stop();
            }
        }
        
        lcd.drawString("KONIEC", 1, 1);
        
        mL.close();
        mR.close();
    }

}
