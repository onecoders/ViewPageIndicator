package net.jsiq.marketing.activity;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import net.jsiq.marketing.R;
import net.jsiq.marketing.fragment.CatalogFragment;
import net.jsiq.marketing.fragment.IndexFragment;
import net.jsiq.marketing.fragment.LeftMenuFragment;
import net.jsiq.marketing.fragment.LeftMenuFragment.LOADSTATUS;
import net.jsiq.marketing.model.MenuItem;
import net.jsiq.marketing.util.MessageToast;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

public class MainActivity extends BaseActivity {

	private Boolean isIndex;
	private static Boolean isExit = false;
	private MenuItem currentMenu;
	private List<MenuItem> menuItems;
	private View loadingHintView, loadingFailedHintView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_frame);
		findViews();
		setListeners();
	}

	private void findViews() {
		loadingHintView = findViewById(R.id.loadingHint);
		loadingFailedHintView = findViewById(R.id.loadingFailedHint);
	}

	private void setListeners() {
		loadingFailedHintView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (v.getId() == R.id.loadingFailedHint) {
			loadMenu();
		}
	}

	public void refreshMainStatus(LeftMenuFragment.LOADSTATUS loadStatus) {
		loadingHintView
				.setVisibility(loadStatus == LOADSTATUS.LOADING ? View.VISIBLE
						: View.GONE);
		loadingFailedHintView
				.setVisibility(loadStatus == LOADSTATUS.FAILED ? View.VISIBLE
						: View.GONE);
	}

	public void initIndexFragment(List<MenuItem> items) {
		isIndex = true;
		menuItems = items;
		switchToIndex();
	}

	private void switchToIndex() {
		currentMenu = null;
		actionBar.hide();
		sm.setSlidingEnabled(false);
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.content_frame, new IndexFragment());
		transaction.addToBackStack(null);
		transaction.commitAllowingStateLoss();
	}

	public void switchCatalogByMenu(MenuItem item) {
		isIndex = false;
		actionBar.show();
		sm.setSlidingEnabled(true);
		if (currentMenu == null || currentMenu.getMenuId() != item.getMenuId()) {
			currentMenu = item;
			initNewCatalogFragmentByMenu(item);
		}
		new Handler().post(new Runnable() {
			public void run() {
				sm.showContent();
			}
		});
	}

	private void initNewCatalogFragmentByMenu(MenuItem item) {
		CatalogFragment fragment = new CatalogFragment();
		Bundle extra = new Bundle();
		if (item != null) {
			extra.putInt(CatalogFragment.MENU_ID, item.getMenuId());
			extra.putString(CatalogFragment.CATALOG_TITLE, item.getMenuName());
		}
		fragment.setArguments(extra);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment)
				.commitAllowingStateLoss();
	}

	@Override
	public void onBackPressed() {
		if (isIndex == null) {
			exitApp();
		} else {
			if (isIndex) {
				exitBy2Click();
			} else {
				isIndex = true;
				switchToIndex();
			}
		}
	}

	private void exitBy2Click() {
		Timer tExit = null;
		if (isExit) {
			exitApp();
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

	private void exitApp() {
		finish();
		System.exit(0);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

}
