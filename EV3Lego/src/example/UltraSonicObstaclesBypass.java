package example;

import java.util.Random;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class UltraSonicObstaclesBypass {
    
    static RegulatedMotor mR = new EV3LargeRegulatedMotor(MotorPort.A);
    static RegulatedMotor mL = new EV3LargeRegulatedMotor(MotorPort.B);
    static Random rand = new Random();

    public static void main(String[] args) {
        EV3 ev3 = (EV3) BrickFinder.getLocal();
        TextLCD lcd = ev3.getTextLCD();
        Port port = LocalEV3.get().getPort("S1");
        UltraSonicSensor sonar = new UltraSonicSensor(port);
        lcd.drawString("Wcisnij przycisk ", 1, 1);
        Button.waitForAnyPress();
        Button.LEDPattern(4);
        boolean go = true;
        boolean forward = true;
        float range;
        int tmp;
        int rotateAngle = 0;
        while(go) {
            range = sonar.getRange();
            lcd.drawString(String.format("dist= %f",range), 1, 3);
            if(range > 0.2f) {
                if(!forward) {
                    mR.forward();
                    mL.forward();
                    forward = true;
                    Delay.msDelay(40);
                }
            } else {
                forward = false;
                mR.stop(true);
                mL.stop(true);
                tmp = rand.nextInt(2);
                rotateAngle = rand.nextInt(90) + 90;
                if(tmp == 1) {
                    mR.rotate(rotateAngle, true);
                    mL.rotate(-rotateAngle, false);
                } else {
                    mR.rotate(-rotateAngle, true);
                    mL.rotate(rotateAngle, false);
                }
            }
            if(Button.ESCAPE.isDown()) go = false;
        }
        sonar.close(); 

    }

}
