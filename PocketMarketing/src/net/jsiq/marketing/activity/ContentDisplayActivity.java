package net.jsiq.marketing.activity;

import net.jsiq.marketing.R;
import net.jsiq.marketing.constants.URLStrings;
import net.jsiq.marketing.util.ViewHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

public class ContentDisplayActivity extends SherlockActivity {

	public static final String CONTENT_ID = "content_id";
	private WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_display);
		initActionBar();

		String contentUrl = URLStrings.GET_CONTENT_BY_CONTENT_ID
				+ getIntent().getStringExtra(CONTENT_ID);

		mWebView = (WebView) findViewById(R.id.content);

		mWebView.loadUrl(contentUrl);
	}

	private void initActionBar() {
		ActionBar actionBar = getSupportActionBar();
		// Get custom view
		View customerView = loadCustomerView();
		// Now set custom view
		ViewHelper.initActionBarAndSetCustomerView(actionBar, customerView);
	}

	private View loadCustomerView() {
		View actionbarView = LayoutInflater.from(this).inflate(
				R.layout.actionbar_content_display, null);
		ImageButton menuBtn = (ImageButton) actionbarView
				.findViewById(R.id.menu_back);
		// menuBtn.setOnClickListener(this);
		ImageButton personalBtn = (ImageButton) actionbarView
				.findViewById(R.id.menu_setting);
		// personalBtn.setOnClickListener(this);
		return actionbarView;
	}

}
