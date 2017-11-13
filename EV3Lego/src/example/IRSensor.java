package example;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;

class IRSensor extends Thread
{
    EV3IRSensor ir = new EV3IRSensor(SensorPort.S4);
    SampleProvider sp = ir.getDistanceMode();
    public int control = 0;
    public int distance = 255;

    IRSensor()
    {

    }
    
    public void run()
    {
        while (true)
        {
            float [] sample = new float[sp.sampleSize()];
            control = ir.getRemoteCommand(0);
            sp.fetchSample(sample, 0);
            distance = (int)sample[0];
            System.out.println("Control: " + control + " Distance: " + distance);
            
        }
        
    }
}