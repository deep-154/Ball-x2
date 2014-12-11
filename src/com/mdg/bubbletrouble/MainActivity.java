package com.mdg.bubbletrouble;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity{

	SensorManager sensorManager;
	public Sensor sensor;
	Dialog d ;
	static int selectMethod = -1;
	RadioGroup radioGroup;
	
	AccelerometerData data = new AccelerometerData();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new Levels(getBaseContext(),this));
		selectMethod=-1;
	    d = new Dialog(MainActivity.this);
		d.setTitle("Options");
		d.setContentView(R.layout.options);
		d.setCanceledOnTouchOutside(false);
		d.show();
		radioGroup = (RadioGroup)d.findViewById(R.id.rg);
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	    sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	    
       
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int checkedId) {
				// TODO Auto-generated method stub
				
				if(checkedId==R.id.radio0){
					data.registerSensor(sensor, sensorManager);	
					selectMethod=1;
					d.dismiss();
				}else if(checkedId==R.id.radio1){
					selectMethod=2;
					d.dismiss();
				}
			}
		});
	  
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(sensor!=null){
		data.unregisterSensor(sensor, sensorManager);
		}
		if(d!= null)
	        d.dismiss();
		selectMethod=-1;	
		
	}

	
}
