package example;

import java.awt.Color;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class BrightSensor {
	
	EV3ColorSensor sensor;
	SampleProvider samProv;
	float[] sample;
	private int  b = 0;
	
	public BrightSensor(Port port){
		sensor = new EV3ColorSensor(port);
		samProv = sensor.getRGBMode();
		sample = new float[samProv.sampleSize()];
	}
	
	public int getBright() {
		readBright();
		return b;
	}
	
	
	
	private void readBright() {
		samProv.fetchSample(sample, 0);
			b = (int) (sample[0]*1000.0f);
		}
}
