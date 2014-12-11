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
		
		r.addView(play, params);
		r.addView(options, paramsOpt);
		
		
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
	}
}
