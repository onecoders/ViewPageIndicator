package net.jsiq.marketing.activity;

import java.util.Timer;
import java.util.TimerTask;

import net.jsiq.marketing.R;
import net.jsiq.marketing.fragment.CatalogFragment;
import net.jsiq.marketing.model.MenuItem;
import net.jsiq.marketing.util.MessageToast;
import android.os.Bundle;
import android.os.Handler;

import com.actionbarsherlock.app.SherlockFragment;

public class MainActivity extends BaseActivity {

	private SherlockFragment mFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_frame);
	}

	@Override
	public void onBackPressed() {
		/*
		 * if (mFragment instanceof CatalogFragment) { if
		 * (!getSlidingMenu().isMenuShowing()) { exitBy2Click(); } } else {
		 * switchContent(new CatalogFragment()); resetSelectedItem(); }
		 */
		exitBy2Click();
	}

	private static Boolean isExit = false;

	private void exitBy2Click() {
		Timer tExit = null;
		if (isExit) {
			finish();
			System.exit(0);
		} else {
			isExit = true;
			MessageToast.showText(this, R.string.clickAgain);
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

	public void setFirstFragment(MenuItem item) {
		Bundle extra = new Bundle();
		extra.putInt(CatalogFragment.MENU_ID, item.getMenuId());
		extra.putString(CatalogFragment.CATALOG_TITLE, item.getMenuName());
		mFragment = new CatalogFragment();
		mFragment.setArguments(extra);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, mFragment).commit();
	}

}
