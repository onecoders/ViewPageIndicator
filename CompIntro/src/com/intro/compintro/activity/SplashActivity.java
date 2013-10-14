package com.intro.compintro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.actionbarsherlock.app.SherlockActivity;
import com.intro.compintro.R;

public class SplashActivity extends SherlockActivity {

	private static final int SPLASH_DISPLAY_LENGHT = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent i = new Intent(SplashActivity.this,
						BasicInfoActivity.class);
				SplashActivity.this.startActivity(i);
				SplashActivity.this.finish();
			}
		}, SPLASH_DISPLAY_LENGHT);
	}

	@Override
	public void onBackPressed() {

	}

}
