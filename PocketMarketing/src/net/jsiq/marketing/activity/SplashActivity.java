package net.jsiq.marketing.activity;

import net.jsiq.marketing.R;
import net.jsiq.marketing.constants.URLStrings;
import net.jsiq.marketing.util.LoaderUtil;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockActivity;

public class SplashActivity extends SherlockActivity {

	private static final int SPLASH_DISPLAY_LENGHT = 1000;

	private ImageView splashImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		// initSplashImage();
		delayAndSwitchToMain();
	}

	private void initSplashImage() {
		splashImageView = (ImageView) findViewById(R.id.splashImageView);
		LoaderUtil.displayImage(this, URLStrings.GET_START_IMAGE,
				splashImageView);
	}

	private void delayAndSwitchToMain() {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent i = new Intent(SplashActivity.this,
						IndexDisplayActivity.class);
				SplashActivity.this.startActivity(i);
				SplashActivity.this.finish();
				// overridePendingTransition(R.anim.zoomout, R.anim.zoomin);
			}
		}, SPLASH_DISPLAY_LENGHT);
	}

	@Override
	public void onBackPressed() {

	}

}
