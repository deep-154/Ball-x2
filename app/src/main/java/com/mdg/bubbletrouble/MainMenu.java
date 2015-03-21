package com.mdg.bubbletrouble;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

		/*
		help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Dialog e = new Dialog(MainMenu.this);
				e.setTitle("Help");
				TextView tv = new TextView(MainMenu.this);
				tv.setText("Aim of Game is to shoot the Bubbles from Gun. Also Avoid the bubbles from Hitting you.ENJOY!!");
				tv.setBackgroundColor(Color.DKGRAY);
				e.setContentView(tv);
				e.show();

			}
		});
*/

	}

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("resume called",""+playMusic);
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



