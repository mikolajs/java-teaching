package example;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.Keys;
//import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;;

public class Program{

    public static void main(String[] args)
    {
        
        EV3 ev3 = (EV3) BrickFinder.getLocal();
        TextLCD lcd = ev3.getTextLCD();
        int ver = 1;
        lcd.drawString("ver " + ver, 20, 1);
        Keys keys = ev3.getKeys();
        
        RegulatedMotor mR = new EV3LargeRegulatedMotor(MotorPort.A);
        RegulatedMotor mL = new EV3LargeRegulatedMotor(MotorPort.B);
        Port port = LocalEV3.get().getPort("S4");
        SensorMode touchSensor = new EV3TouchSensor(port);
        SimpleTouch touch = new SimpleTouch(touchSensor);
        
        boolean go = true;
        mR.setSpeed(400);
        mL.setSpeed(400);
        for (int i = 0; i < 10; i++){
            if(Button.ENTER.isDown()) break;
            for(int j = 0; j < 4; j++){
                mR.stop();
                mR.forward();
                mL.stop();
                mL.forward();
                Delay.msDelay(2000L);
                mR.stop(true);
                mL.stop(true);
                mR.rotate(215, true);
                mL.rotate(-215, true);
                Delay.msDelay(1000L);
            }
           
            
        }
        
        lcd.drawString("koniec", 1, 1);
        
        mL.close();
        mR.close();
    }
}