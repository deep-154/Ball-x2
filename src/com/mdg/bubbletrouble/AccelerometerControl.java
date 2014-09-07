package com.mdg.bubbletrouble;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class AccelerometerControl extends Activity implements SensorEventListener{

	SensorManager sensorManager;
	static double sensorX;
	MyListener listener;
	
	public interface MyListener{
		  public void processSensorEvent(SensorEvent event);
		}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		sensorX = event.values[0];
		fireUpdate(event);
		
	}

	public void registerSensor(){
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		if(sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() !=0){
			Sensor s = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			sensorManager.registerListener(this, s, SensorManager.SENSOR_DELAY_GAME);
		}
	}
	
	private void fireUpdate(SensorEvent event){ 
		registerSensor();
	  listener.processSensorEvent(event);
		  
		}
	
}
