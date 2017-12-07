package example;

import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.lcd.*;
import lejos.utility.Delay;

public class GyroTest {

	public static void main(String[] args) throws InterruptedException {
		EV3GyroSensor Gyro = new EV3GyroSensor(SensorPort.S1);
		Gyro.reset();
		LCD.drawString("Sample size = " + Gyro.sampleSize(), 0, 2);
		Delay.msDelay(2000);
		LCD.clear();
		long startTime;
		long result = 0;
		float[] AngleSamples = new float[2];
		float[] RateSamples = new float[2];
		float[] AngleRateSamples = new float[2];
		Delay.msDelay(2000);

		// Get the Angle.
		for (int i = 0; i < 1000; i = i + 1) {
			startTime = System.currentTimeMillis();
			Gyro.getAngleMode().fetchSample(AngleSamples, 0);
			LCD.drawString("Angle = ", 0, 2);
			LCD.drawString("= " + AngleSamples[0], 0, 3);
			Delay.msDelay(10);
			result = result + System.currentTimeMillis() - startTime - 10;
		}
		LCD.drawString("Average run time", 0, 5);
		LCD.drawString("= " + (result / 1000), 0, 6);
		Delay.msDelay(2000);
		LCD.clear();
		Delay.msDelay(2000);
		// During the wait, begin manually changing the position of the
		// Gyro Sensor.

		// Get the Angular Velocity.
		result = 0;
		for (int i = 0; i < 1000; i = i + 1) {
			startTime = System.currentTimeMillis();
			Gyro.getRateMode().fetchSample(RateSamples, 0);
			LCD.drawString("Anglular Velocity", 0, 2);
			LCD.drawString("= " + RateSamples[0], 0, 3);
			Delay.msDelay(10);
			result = result + System.currentTimeMillis() - startTime - 10;
		}
		LCD.drawString("Average run time", 0, 5);
		LCD.drawString("= " + (result / 1000), 0, 6);
		Delay.msDelay(2000);
		LCD.clear();
		Delay.msDelay(2000);

		// Get both.
		result = 0;
		for (int i = 0; i < 1000; i = i + 1) {
			startTime = System.currentTimeMillis();
			Gyro.getAngleAndRateMode().fetchSample(AngleRateSamples, 0);
			LCD.drawString("Both", 0, 2);
			LCD.drawString("= " + AngleRateSamples[0] + ", " + AngleRateSamples[1], 0, 3);

			Delay.msDelay(10);
			result = result + System.currentTimeMillis() - startTime - 10;
		}
		LCD.drawString("Average run time", 0, 5);
		LCD.drawString("= " + (result / 1000), 0, 6);
		Delay.msDelay(2000); // Let the last message display before ending the program.
	}

}
