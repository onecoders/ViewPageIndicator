package net.jsiq.marketing.activity;

import net.jsiq.marketing.R;
import net.jsiq.marketing.constants.URLStrings;
import net.jsiq.marketing.db.CollectionDBHelper;
import net.jsiq.marketing.model.ContentCollection;
import net.jsiq.marketing.util.MessageToast;
import net.jsiq.marketing.util.ViewHelper;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageButton;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

public class ContentDisplayActivity extends SherlockActivity implements
		OnClickListener {

	public static final String CONTENT_INFO = "content_info";

	private WebView mWebView;
	private ImageButton menuCollection, menuShare;
	private String[] contentInfo;
	private int contentId;
	private CollectionDBHelper DBHelper;
	private String contentUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_display);
		initActionBar();
		DBHelper = new CollectionDBHelper(this);
		DBHelper.open();
		contentInfo = getIntent().getStringArrayExtra(CONTENT_INFO);

		contentId = Integer.valueOf(contentInfo[0]);

		contentUrl = URLStrings.GET_CONTENT_BY_CONTENT_ID + contentId;
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
		menuCollection = (ImageButton) actionbarView
				.findViewById(R.id.menu_collection);
		menuCollection.setOnClickListener(this);
		menuShare = (ImageButton) actionbarView.findViewById(R.id.menu_share);
		menuShare.setOnClickListener(this);
		return actionbarView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.menu_back:
			onBackPressed();
			break;
		case R.id.menu_collection:
			addToCollection();
			break;
		case R.id.menu_share:
			toOnekeyShare();
			break;
		default:
			break;
		}
	}

	private void addToCollection() {
		boolean success = DBHelper.insert(new ContentCollection(contentId,
				contentInfo[1], contentInfo[2]));
		if (success) {
			MessageToast.showText(this, R.string.addCollectionSuccess);
		} else {
			MessageToast.showText(this, R.string.addCollectionFail);
		}
	}

	private void toOnekeyShare() {
		OnekeyShare oks = new OnekeyShare();
		oks.setTitle(getString(R.string.app_name));
		oks.setText(getString(R.string.app_name));
		oks.setUrl(contentUrl);
		oks.setSilent(true);
		oks.show(this);
	}

	@Override
	protected void onDestroy() {
		DBHelper.close();
		super.onDestroy();
	}

}
