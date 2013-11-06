package net.jsip.market.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import net.jsip.market.R;
import net.jsip.market.mainfragment.BasicInfoFragment;
import net.jsip.market.util.MessageToast;


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
			resetSelectedItem();
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
			MessageToast
					.makeText(this, R.string.clickAgain, Toast.LENGTH_SHORT)
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
		new Handler().post(new Runnable() {
			public void run() {
				getSlidingMenu().showContent();
			}
		});
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

}