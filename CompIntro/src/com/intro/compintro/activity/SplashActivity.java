package com.intro.compintro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.actionbarsherlock.app.SherlockActivity;
import net.jsip.market.R;

public class SplashActivity extends SherlockActivity {

	private static final int SPLASH_DISPLAY_LENGHT = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent i = new Intent(SplashActivity.this, MainActivity.class);
				SplashActivity.this.startActivity(i);
				SplashActivity.this.finish();
				overridePendingTransition(R.anim.zoomout, R.anim.zoomin);
			}
		}, SPLASH_DISPLAY_LENGHT);
	}

	@Override
	public void onBackPressed() {

	}

}
