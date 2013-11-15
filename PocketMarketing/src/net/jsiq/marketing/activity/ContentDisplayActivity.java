package net.jsiq.marketing.activity;

import net.jsiq.marketing.R;
import net.jsiq.marketing.constants.URLStrings;
import net.jsiq.marketing.util.ViewHelper;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageButton;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

public class ContentDisplayActivity extends SherlockActivity implements
		OnClickListener {

	public static final String CONTENT_ID = "content_id";
	private WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		setContentView(R.layout.content_display);
		initActionBar();

		String contentUrl = URLStrings.GET_CONTENT_BY_CONTENT_ID
				+ getIntent().getIntExtra(CONTENT_ID, 1);
		System.out.println(contentUrl);
		mWebView = (WebView) findViewById(R.id.content);

		mWebView.loadUrl(contentUrl);
	}

	private void initActionBar() {
		ActionBar actionBar = getSupportActionBar();
		ColorDrawable drawable = new ColorDrawable(
				Color.parseColor("#88F0F0F0"));
		actionBar.setBackgroundDrawable(drawable);
		actionBar.setSplitBackgroundDrawable(drawable);
		// Get custom view
		View customerView = loadCustomerView();
		// Now set custom view
		ViewHelper.initActionBarAndSetCustomerView(actionBar, customerView);
	}

	private View loadCustomerView() {
		View actionbarView = LayoutInflater.from(this).inflate(
				R.layout.actionbar_content_display, null);
		ImageButton menuBack = (ImageButton) actionbarView
				.findViewById(R.id.menu_back);
		menuBack.setOnClickListener(this);
		ImageButton menuSetting = (ImageButton) actionbarView
				.findViewById(R.id.menu_setting);
		menuSetting.setOnClickListener(this);
		return actionbarView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.menu_back:
			onBackPressed();
			break;
		case R.id.menu_setting:

			break;
		default:
			break;
		}
	}

}
