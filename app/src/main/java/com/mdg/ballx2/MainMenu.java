package com.mdg.ballx2;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

public class MainMenu extends Activity {

	static MediaPlayer mediaPlayer;
	static int selectMethod = 1;
    static boolean playMusic = true;
   static MainMenu activity;


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
        activity = MainMenu.this;
		// -----------------------------------------------------------------------------------------------------
	/*	DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		height = displaymetrics.heightPixels;
		width = displaymetrics.widthPixels;*/
	}

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = MediaPlayer.create(this, R.raw.menu_music);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(50, 50);

        if(playMusic) {
            mediaPlayer.start();
        }


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            Window w = activity.getWindow();
            final View decorView = w.getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }
	
	@Override
	protected void onStop() {
		super.onStop();
		if (mediaPlayer != null) {
            if(playMusic)
			mediaPlayer.stop();
			mediaPlayer.release();
            mediaPlayer = null;
		}
	}



}



