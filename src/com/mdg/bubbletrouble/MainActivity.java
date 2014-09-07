package com.mdg.bubbletrouble;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity implements controller.OnTouch {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Fragment fr = new Levels();
		FragmentManager fm = getFragmentManager();
		FragmentTransaction fragmentTransaction = fm.beginTransaction();
		fragmentTransaction.replace(R.id.main, fr, "one");
		fragmentTransaction.commit();
	}


	@Override
	public void onCoordinateSelected(float position, boolean checkShoot) {
		// TODO Auto-generated method stub

			Levels one = (Levels) getFragmentManager().findFragmentByTag("one");
			one.getCoordinate(position, checkShoot);	
		
	}

	
}
