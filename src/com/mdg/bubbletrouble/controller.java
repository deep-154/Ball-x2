package com.mdg.bubbletrouble;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class controller extends Fragment{
	OnTouch mCallback;
	float a =-1;
	boolean tag = false;
	float time;
	int lifeGone = 1;
	int stageChanged = 0;
	int delay= 0;
	// Container Activity must implement this interface
    public interface OnTouch {
        public void onCoordinateSelected(float position,boolean checkShoot);
       
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnTouch) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTouch");
        }
    }
    
    public void OnStageOneChanged(int changeStage, int restartStage) {
		// TODO Auto-generated method stub
		stageChanged = changeStage;
		lifeGone = restartStage;
	}

    public void OnStageTwoChanged(int changeStage, int restartStage) {
		// TODO Auto-generated method stub
    	stageChanged = changeStage;
		lifeGone = restartStage;
	}
	public class view2 extends View{

		Bitmap background_bottom,fireUp;
		float x=-1,y,X=-1,Y=-1;
		float secondX = -1,secondY = -1;
		int alpha;
		boolean check = false;
		int height ;
		int width ;
		
		public view2(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			background_bottom = BitmapFactory.decodeResource(getResources(), R.drawable.score_bar);
			fireUp = BitmapFactory.decodeResource(getResources(), R.drawable.fire_up);	
		}
		
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			// TODO Auto-generated method stub
			x = event.getX(0);
			y = event.getY(0);
			
			X=x;
			int action = event.getAction()& MotionEvent.ACTION_MASK;
			switch(action){
			
			case MotionEvent.ACTION_UP:
				check = true;
				
				
				break;
			case MotionEvent.ACTION_DOWN:
				check = false;
				break;
			case MotionEvent.ACTION_MOVE:
				
				if(event.getPointerCount()==2){
					secondX = event.getX(1);
					secondY = event.getY(1);
					Y = secondX;
				}
			case MotionEvent.ACTION_POINTER_DOWN:	
				if(event.getPointerCount()==2){
					secondX = event.getX(1);
					secondY = event.getY(1);
					Y = secondX;
				}
			}
			
			//Log.i("index",""+pointerId);
			
			return true;
			
		}

		@SuppressLint("DrawAllocation") @Override
		protected void onDraw(Canvas c) {
			// TODO Auto-generated method stub
			super.onDraw(c);
			int height = this.getHeight();
			int width = this.getWidth();
			//-----------------------------------------------------
			// setting background
			Rect dest = new Rect(0,0,getWidth(),getHeight());
			Paint paint = new Paint();
			paint.setFilterBitmap(true);
			c.drawBitmap(background_bottom, null, dest, paint);
			//---------------------------------------------
			Paint p = new Paint();
			p.setAlpha(255);
			Rect r2 = new Rect( (82*width/100),4*height/10, width, height);
			c.drawBitmap(fireUp, null, r2, p);
			//----------------------------------------------
			//setting slider
			float touchBarWidth  = 43*width/100;
			Paint grey = new Paint();
			alpha = 80;
            if(x<touchBarWidth&&y>56* height/100){
				alpha = alpha+50;			
			}
            if(check == true){
          	  alpha =80;          	 
            }
        
			grey.setARGB(alpha, 242, 243, 244);
			c.drawRect(0,56* height/100, touchBarWidth, height, grey);
		//-------------------------------------------------------------------------------------------
			if(stageChanged==1||lifeGone ==1){
				x=-1;
			}
			if(x<touchBarWidth&&y>56* height/100){
				a = x;
			}
			
			if(X>82*width/100&&y>4*height/10||Y>82*width/100&&secondY>4*height/10){
				tag = true;
				X=-1;
				Y=-1;
			}
		
			mCallback.onCoordinateSelected(a, tag);
			tag = false;
	   //------------------------------------------------------------------------------------------------------
			float side_strip = (float) (0.011 * width);
			float side_strip2 = (float) (0.016 * width);
			
			Paint red = new Paint();
			red.setColor(Color.rgb(204, 0, 0));
			if(lifeGone == 1||stageChanged==1){
				time = this.getWidth()-side_strip2;
				//lifeGone = 0;
			}
			c.drawRect(side_strip, 7*height/100,time , 26*height/100, red);
			
			if(lifeGone ==1||stageChanged==1){
				delay++;
			}else delay = 101;
			
			if(delay>100){
			time = (float) (time-0.5);			
			if(time<side_strip){
				//lifeGone = 1;
			}
			
			lifeGone = 0;
			stageChanged =0;
			delay=0;
			}
			//Log.v("l2", "" + lifeGone);
			invalidate();
			
		}
		
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return new view2(this.getActivity());
    }

		
}
