package com.intro.compintro.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.intro.compintro.R;
import com.intro.compintro.mainfragment.BasicInfoFragment;

public class MainActivity extends BaseActivity {

	SherlockFragment mFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_frame);
		mFragment = new BasicInfoFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, mFragment).commit();

	}

	@Override
	public void onBackPressed() {
		if (mFragment instanceof BasicInfoFragment) {
			if (!getSlidingMenu().isMenuShowing()) {
				exitBy2Click();
			}
		} else {
			switchContent(new BasicInfoFragment());
		}
	}

	private static Boolean isExit = false;

	private void exitBy2Click() {
		Timer tExit = null;
		if (isExit) {
			finish();
			System.exit(0);
		} else {
			isExit = true;
			Toast.makeText(this, R.string.clickAgain, Toast.LENGTH_SHORT)
					.show();
			tExit = new Timer();
			tExit.schedule(new TimerTask() {

				@Override
				public void run() {
					isExit = false;
				}
			}, 2000);
		}
	}

	public void switchContent(SherlockFragment fragment) {
		mFragment = fragment;
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
		getSlidingMenu().showContent();
	}

}
