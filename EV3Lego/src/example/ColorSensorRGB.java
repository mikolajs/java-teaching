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
	private int r, g, b;
	
	public ColorSensorRGB(Port port){
		sensor = new EV3ColorSensor(port);
		samProv = sensor.getRGBMode();
		sample = new float[samProv.sampleSize()];
	}
	
	public Color getRGB() {
		readRGB();
		return new Color(r,g,b);
	}
	
	public int getRed() {
		readRGB();
		return r;
	}
	
	public int getGreen() {
		readRGB();
		return g;
	}
	
	public int getBlue() {
		readRGB();
		return b;
	}
	
	public void close() {
		sensor.close();
	}
	
	public int getBrightness() {
		return r + b + g;
	}
	
	private void readRGB() {
		samProv.fetchSample(sample, 0);
		if(sample.length == 3) {
			r = (int) (sample[0]*156.0f);
			g = (int) (sample[1]*156.0f);
			b = (int) (sample[2]*156.0f);
		}
	}
	
	
	
}
