package example;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class SelfBalacer {
//    static RegulatedMotor mR = new EV3LargeRegulatedMotor(MotorPort.C);
    static RegulatedMotor mL = new EV3LargeRegulatedMotor(MotorPort.B);
    static UnregulatedMotor mR = new UnregulatedMotor(MotorPort.C);
    public static void main(String[] args) {
        EV3 ev3 = (EV3) BrickFinder.getLocal();
        TextLCD lcd = ev3.getTextLCD();
        Port port = LocalEV3.get().getPort("S1");
        GyroscopeSensor gyro = new GyroscopeSensor(port);
        lcd.drawString("Wcisnij przycisk ", 1, 1);
        Button.waitForAnyPress();
        Button.LEDPattern(4);
        float angle;
        boolean isMoving = false;
        //mR.setSpeed(800);
        mL.setSpeed(800);
        //mR.setAcceleration(800);
        mL.setAcceleration(800);
        while(true) {
            angle = gyro.getAngle();
            lcd.drawString(String.format("angle= %f",angle), 1, 3);
            if(angle > 5.0f) {
                isMoving = true;
                mR.setPower(-50);
                mL.forward();
            } else if(angle < -5.0f) {
                isMoving = true;
                mR.setPower(50);
                mL.backward();
            }
            Delay.msDelay(50);
            if(isMoving) {
                isMoving = false;
                //mR.stop(true);
                mL.stop(true);
            }
            //Delay.msDelay(1000); 
//            if(angle < 0.1f) break;
            if(Button.ESCAPE.isDown()) break;
        }
        gyro.close();
    }
}
