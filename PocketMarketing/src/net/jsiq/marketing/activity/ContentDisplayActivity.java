package net.jsiq.marketing.activity;

import net.jsiq.marketing.R;
import net.jsiq.marketing.constants.URLStrings;
import net.jsiq.marketing.db.CollectionDBHelper;
import net.jsiq.marketing.model.CollectionItem;
import net.jsiq.marketing.util.MessageToast;
import net.jsiq.marketing.util.NetworkUtils;
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
	private View loadingFailedHint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_display);
		DBHelper = new CollectionDBHelper(this);
		DBHelper.open();
		contentInfo = getIntent().getStringArrayExtra(CONTENT_INFO);
		contentId = Integer.valueOf(contentInfo[0]);
		initActionBar();

		contentUrl = URLStrings.GET_CONTENT_BY_CONTENT_ID + contentId;
		mWebView = (WebView) findViewById(R.id.content);
		loadingFailedHint = findViewById(R.id.loadingFailedHint);
		loadingFailedHint.setOnClickListener(this);
		loadContent();
	}

	private void loadContent() {
		if (NetworkUtils.isNetworkConnected(this)) {
			mWebView.loadUrl(contentUrl);
			mWebView.setVisibility(View.VISIBLE);
			loadingFailedHint.setVisibility(View.GONE);
		} else {
			mWebView.setVisibility(View.GONE);
			loadingFailedHint.setVisibility(View.VISIBLE);
			MessageToast.showText(this, R.string.notConnected);
		}
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
		if (existInCollections(contentId)) {
			setMenuCollecitonBg(R.drawable.already_collected);
		} else {
			setMenuCollecitonBg(R.drawable.not_collected);
		}
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
			if (existInCollections(contentId)) {
				cancelCollection();
			} else {
				addToCollection();
			}
			break;
		case R.id.menu_share:
			toOnekeyShare();
			break;
		case R.id.loadingFailedHint:
			loadContent();
			break;
		default:
			break;
		}
	}

	private boolean existInCollections(int contentId) {
		return DBHelper.queryById(contentId);
	}

	private void addToCollection() {
		boolean success = DBHelper.insert(new CollectionItem(contentId,
				contentInfo[1], contentInfo[2]));
		if (success) {
			MessageToast.showText(this, R.string.addCollectionSuccess);
			setMenuCollecitonBg(R.drawable.already_collected);
		} else {
			MessageToast.showText(this, R.string.addCollectionFail);
		}
	}

	private void cancelCollection() {
		boolean success = DBHelper.delete(contentId);
		if (success) {
			MessageToast.showText(this, R.string.cancelCollecitonSucced);
			setMenuCollecitonBg(R.drawable.not_collected);
		} else {
			MessageToast.showText(this, R.string.cancelCollecitonFailed);
		}
	}

	private void setMenuCollecitonBg(int res) {
		menuCollection.setBackgroundResource(res);
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
