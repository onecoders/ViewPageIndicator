package net.jsiq.marketing.activity;

import net.jsiq.marketing.R;
import net.jsiq.marketing.fragment.CatalogFragment;
import net.jsiq.marketing.fragment.LeftMenuFragment;
import net.jsiq.marketing.fragment.LeftMenuFragment.LOADSTATUS;
import net.jsiq.marketing.model.MenuItem;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class MainActivity extends BaseActivity {

	private int menuPos;
	private View loadingHintView, loadingFailedHintView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_frame);
		findViews();
		setListeners();
		menuPos = getIntent().getIntExtra(
				IndexDisplayActivity.MENU_SELECTED_POS, 0);
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

	public void switchCatalogByMenu(MenuItem item) {
		initNewCatalogFragmentByMenu(item);
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
				.replace(R.id.content_frame, fragment).commit();
	}

	public int getMenuPos() {
		return menuPos;
	}

}
