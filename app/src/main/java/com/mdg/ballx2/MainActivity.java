package com.mdg.ballx2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mdg.ballx2.Levels.BaseLevel;
import com.mdg.ballx2.Levels.Level2;
import com.mdg.ballx2.Levels.Level3;
import com.mdg.ballx2.Levels.Level4;
import com.mdg.ballx2.Levels.Level5;
import com.mdg.ballx2.Levels.Level6;
import com.mdg.ballx2.Levels.Level7;

import java.util.jar.Attributes;


public class MainActivity extends Activity implements BaseLevel.onStageChangeListener{

    SensorManager sensorManager;
    public Sensor sensor;
    public static MainActivity activity;
    int height,width;
    AccelerometerData data = new AccelerometerData();
    public static TextView noOflevel,scoreView,lifeView;
    ImageButton pause,play;
    public RelativeLayout relativeLayout;
    public static int currentLevel = 2,score =0,noOflife =5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = MainActivity.this;
        setContentView(R.layout.levels);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width =  metrics.widthPixels;
        height = metrics.heightPixels;



        //setting up Music
	  /* 	mediaPlayer = MediaPlayer.create(this, R.raw.game_music);
		mediaPlayer.setLooping(true);
		mediaPlayer.setVolume(50,50);
		mediaPlayer.start();*/

        Typeface custom_font = Typeface.createFromAsset(getAssets(),
                "fonts/chewy.ttf");
        BaseLevel.pauseGame = true;


        noOflevel = (TextView)findViewById(R.id.levelTitle);
        noOflevel.setTextColor(Color.rgb(247,245,228));
        noOflevel.setTypeface(custom_font);

        scoreView = (TextView)findViewById(R.id.score);
        scoreView.setTypeface(custom_font);

        lifeView = (TextView)findViewById(R.id.lifeView);
        lifeView.setTypeface(custom_font);


    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        data.registerSensor(sensor, sensorManager);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            setupMainWindowDisplayMode();
            int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
            boolean isImmersiveModeEnabled =
                    ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
            if(isImmersiveModeEnabled){
                Point size = new Point();
                Display display = getWindowManager().getDefaultDisplay();
                display.getRealSize(size);
                width = size.x;
                height = size.y;
            }
        }
         relativeLayout = (RelativeLayout)findViewById(R.id.mainView);

        pause = new ImageButton(this);
        pause.setBackgroundResource(R.drawable.pausebtnbackground);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                10 * width / 100, 17 * height / 100);
        params.leftMargin = 85 * width / 100;
        params.topMargin = 55*height / 1000;
        relativeLayout.addView(pause,params);

        play = (ImageButton)findViewById(R.id.play);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseLevel.pauseGame=true;
                play.setVisibility(View.VISIBLE);
                pause.setClickable(false);
            }
        });


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseLevel.pauseGame=false;
                pause.setClickable(true);
                play.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void setupMainWindowDisplayMode() {
        final View decorView = setSystemUiVisilityMode();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                setSystemUiVisilityMode(); // Needed to avoid exiting immersive_sticky when keyboard is displayed
            }
        });
    }

    private View setSystemUiVisilityMode() {
        View decorView = getWindow().getDecorView();
        int options;
        options =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        decorView.setSystemUiVisibility(options);
        return decorView;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        pause.performClick();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Handle the back button
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            //Ask the user if they want to quit
            BaseLevel.pauseGame = true;
            AlertDialog.Builder dBuilder=    new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Quit")
                    .setMessage("Do you really want to exit")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           //Stop the activity
                            finish();
                            currentLevel = 1;
                            noOflife = 5;
                            score = 0;
                        }

                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            BaseLevel.pauseGame = false;
                        }
                    })
                    .setCancelable(false);
            AlertDialog d = dBuilder.create();
            d.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            d.show();
            //Set the dialog to immersive
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                d.getWindow().getDecorView().setSystemUiVisibility(
                        MainActivity.activity.getWindow().getDecorView().getSystemUiVisibility());
            }
            //Clear the not focusable flag from the window
            d.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

            return true;
        }
        else {
            return super.onKeyDown(keyCode, event);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        BaseLevel.pauseGame = true;
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if(sensor!=null){
            data.unregisterSensor(sensor, sensorManager);
        }
       /* if (mediaPlayer != null){
            mediaPlayer.stop();
            if (isFinishing()){
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }*/
    }


    @Override
    public void onStageChanged(int runningLevel,int life) {

        noOflife = life;
        View v = findViewById(R.id.myView);
        int index =0;
        if(v!=null) {
             index = relativeLayout.indexOfChild(v);
            relativeLayout.removeView(v);
        }
        View gameView = null;
        switch (runningLevel) {

            case 2:
                gameView = new Level2(getApplicationContext(),null);
                break;
            case 3:
               gameView = new Level3(getApplicationContext(),null);
                break;
            case 4:
                gameView= new Level4(getApplicationContext(),null);
                break;
            case 5:
                gameView = new Level5(getApplicationContext(),null);
                break;
            case 6:
                gameView = new Level6(getApplicationContext(),null);
                break;
            case 7:
               gameView= new Level7(getApplicationContext(),null);
                break;

        }

        gameView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        gameView.setId(R.id.myView);
        relativeLayout.addView(gameView,index);

    }


}
