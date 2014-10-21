package com.mdg.bubbletrouble;


import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class MainActivity extends Activity implements SensorEventListener{

	SensorManager sensorManager;
	public Sensor sensor;
	static float sensorX;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new Levels(this));

		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	    sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	    sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
	}

	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		sensorX = event.values[1];	
	}
	
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		sensorManager.unregisterListener(this, sensor);
		
	}

	
	






	
	
	

	
}
