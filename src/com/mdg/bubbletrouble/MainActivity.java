package com.mdg.bubbletrouble;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RadioGroup;

public class MainActivity extends Activity{

	SensorManager sensorManager;
	public Sensor sensor;
	RadioGroup radioGroup;
	MediaPlayer mediaPlayer;
	
	AccelerometerData data = new AccelerometerData();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new Levels(getBaseContext(),this));
		//setting up Music
		mediaPlayer = MediaPlayer.create(this, R.raw.game_music);
		mediaPlayer.setLooping(true);
		mediaPlayer.setVolume(50,50);
		mediaPlayer.start();
		
		Levels.pauseGame = false;
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	    sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	    data.registerSensor(sensor, sensorManager);	
	   
	 
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    //Handle the back button
		
	    if(keyCode == KeyEvent.KEYCODE_BACK) {
	        //Ask the user if they want to quit
	    	Levels.pauseGame = true;
	        new AlertDialog.Builder(this)
	        .setIcon(android.R.drawable.ic_dialog_alert)
	        .setTitle("Quit")
	        .setMessage("Do you really want to exit")
	        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

	            @Override
	            public void onClick(DialogInterface dialog, int which) {

	                //Stop the activity
	                finish();
	            }

	        })
	        .setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Levels.pauseGame = false;
				}
			})
			.setCancelable(false)
	        .show();

	        return true;
	    }
	    else {
	        return super.onKeyDown(keyCode, event);
	    }

	}
	
	
	@Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null){
            mediaPlayer.stop();
            if (isFinishing()){
            mediaPlayer.stop();
            mediaPlayer.release();
            }
        }
    }
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(sensor!=null){
		data.unregisterSensor(sensor, sensorManager);
		}
	}

	
}
