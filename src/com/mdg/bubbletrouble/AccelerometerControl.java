package com.mdg.bubbletrouble;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AccelerometerControl extends Activity implements SensorEventListener{

	SensorManager sensorManager;
	public Sensor sensor;
	float sensorX;
	
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		sensorX = event.values[1];
		getValues();
	}

	public void registerSensor(){		
			sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
			
	}
	protected void unregisterSensor() {
	    sensorManager.unregisterListener(this, sensor);
	}
	
	public float getValues(){
		return sensorX;
		
	}
	
}
