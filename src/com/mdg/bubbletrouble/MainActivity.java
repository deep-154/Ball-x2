package com.mdg.bubbletrouble;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity implements controller.OnTouch {

	AccelerometerControl acc = new AccelerometerControl();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Fragment fr = new Levels();
		FragmentManager fm = getFragmentManager();
		FragmentTransaction fragmentTransaction = fm.beginTransaction();
		fragmentTransaction.replace(R.id.main, fr, "one");
		fragmentTransaction.commit();
		
		acc.sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	    acc.sensor = acc.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	    acc.registerSensor();
	    
	}


	

	@Override
	public void onCoordinateSelected(float position, boolean checkShoot) {
		// TODO Auto-generated method stub

			Levels one = (Levels) getFragmentManager().findFragmentByTag("one");
			one.getCoordinate(acc.getValues(), checkShoot);	
			//Log.v("accelerate",""+acc.getValues());
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		acc.unregisterSensor();
	}

	
	
	
	

	
}
