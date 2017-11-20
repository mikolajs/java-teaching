package example;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;

public class GyroscopeSensor {
    EV3GyroSensor sensor;
    SampleProvider sp;
    float[] sample;

    public GyroscopeSensor(Port port) {
        sensor = new EV3GyroSensor(port);
        sp = sensor.getAngleMode();
        sample = new float[sp.sampleSize()];
    }
    
    public float getAngle() {
        sp.fetchSample(sample, 0);
        return sample[0];
    }
    
    public void close() {
        sensor.close();
    }
}
