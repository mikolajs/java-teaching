package example;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
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
    
    static RegulatedMotor mR = new EV3LargeRegulatedMotor(MotorPort.A);
    static RegulatedMotor mL = new EV3LargeRegulatedMotor(MotorPort.B);
    
    public static void main(String[] args) {

        EV3 ev3 = (EV3) BrickFinder.getLocal();
        TextLCD lcd = ev3.getTextLCD();

        lcd.drawString("Mikolaj S", 20, 1);
        Keys keys = ev3.getKeys();
        keys.waitForAnyPress();

       
        mR.setSpeed(400);
        mL.setSpeed(400);
        mR.stop();
        mL.stop();
        for(int i = 0; i < 2; i++) {
            if(Button.ENTER.isDown()) {
                break;
            }
            for(int j = 0; j < 4; j++){
                mR.forward();
                mL.forward();
                Delay.msDelay(1000L);
                mR.stop(true);
                mL.stop(true);
                mR.rotate(180, true);
                mL.rotate(-180, false);
                //Delay.msDelay(1000L);
                mR.stop(true);
                mL.stop(false);
            }
        }
        mL.close();
        mR.close();
        lcd.drawString("KONIEC", 1, 1);
        keys.waitForAnyPress();
    }

    public static void forward(long time, int power) {
        mR.setSpeed(power);
        mR.forward();
        mL.forward();
        Delay.msDelay(time);
        mR.stop(true);
        mL.stop(false);
    }
}
