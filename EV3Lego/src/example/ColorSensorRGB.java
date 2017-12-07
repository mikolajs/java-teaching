package example;

//import lejos.robotics.Color;
import java.awt.Color;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class ColorSensorRGB {
	
	EV3ColorSensor sensor;
	SampleProvider samProv;
	float[] sample;
	
	public ColorSensorRGB(Port port){
		sensor = new EV3ColorSensor(port);
		samProv = sensor.getRGBMode();
		sample = new float[samProv.sampleSize()];
	}
	
	public Color getRGB() {
		int r = 0, g = 0, b = 0;
		samProv.fetchSample(sample, 0);
		if(sample.length == 3) {
			r = (int) sample[0];
			g = (int) sample[1];
			b = (int) sample[2];
		}
		return new Color(r,g,b);
	}
	
}
