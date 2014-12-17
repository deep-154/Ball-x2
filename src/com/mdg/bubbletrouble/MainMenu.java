package com.mdg.bubbletrouble;

import android.R.color;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainMenu extends Activity{

	RelativeLayout r;
	int height,width;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		
		DisplayMetrics displaymetrics = new DisplayMetrics();
    	getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
    	height =  displaymetrics.heightPixels;
    	width = displaymetrics.widthPixels; 
        
		r = (RelativeLayout) findViewById(R.id.background);
		
		ImageButton play = new ImageButton(this);
        play.setBackgroundColor(Color.TRANSPARENT);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(10*width/37,42*height/100);
		params.leftMargin = 70*width/100;
		params.topMargin = height/23;
		
		ImageButton options = new ImageButton(this);
        options.setBackgroundColor(Color.TRANSPARENT);
		RelativeLayout.LayoutParams paramsOpt = new RelativeLayout.LayoutParams(18*width/100,26*height/100);
		paramsOpt.leftMargin = 54*width/100;
		paramsOpt.topMargin = 43*height/100;
		
		ImageButton help = new ImageButton(this);
		help.setBackgroundColor(Color.TRANSPARENT);
		RelativeLayout.LayoutParams paramsHelp = new RelativeLayout.LayoutParams(
				14 * width / 100, 20 * height / 100);
		paramsHelp.leftMargin = 80 * width / 100;
		paramsHelp.topMargin = 59 * height / 100;
		
		ImageButton credits = new ImageButton(this);
		credits.setBackgroundColor(Color.TRANSPARENT);
		RelativeLayout.LayoutParams paramsCredit = new RelativeLayout.LayoutParams(
				12 * width / 100, 18 * height / 100);
		paramsCredit.leftMargin = (int) (64.5 * width / 100);
		paramsCredit.topMargin = 79 * height / 100;
		
		r.addView(play, params);
		r.addView(options, paramsOpt);
		r.addView(help, paramsHelp);
		r.addView(credits, paramsCredit);
		
		
		play.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent("com.mdg.bubbletrouble.MAINACTIVITY");
				startActivity(i);
			}
		});
		
		options.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Dialog d = new Dialog(MainMenu.this);
				 d.setTitle("Options");
				 d.setContentView(R.layout.options);
				 d.show();
			  
			}
		});
		help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Dialog d = new Dialog(MainMenu.this);
				d.setTitle("Help");
				TextView tv = new TextView(MainMenu.this);
				tv.setText("Aim of Game is to shoot the Bubbles from Gun. Also Avoid the bubbles from Hitting you.ENJOY!!");
				tv.setBackgroundColor(Color.DKGRAY);
				d.setContentView(tv);
				d.show();

			}
		});

		credits.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent("com.mdg.bubbletrouble.CREDITS");
				startActivity(i);
			}
		});
	}
}
