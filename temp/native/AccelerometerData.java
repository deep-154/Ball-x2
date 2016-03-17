package com.mdg.ballx2;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AccelerometerData extends Activity implements SensorEventListener {

	public static float sensorX;
		
	public void registerSensor(Sensor s,SensorManager sm){	
		sm.registerListener(this, s, SensorManager.SENSOR_DELAY_GAME);
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		sensorX = event.values[1];	
		getSensorValues();
	}
	
	
	public float getSensorValues() {
		// TODO Auto-generated method stub
		return sensorX;
	}

	public void unregisterSensor(Sensor s,SensorManager sm){		
		sm.unregisterListener(this, s);
	}


	
	}
