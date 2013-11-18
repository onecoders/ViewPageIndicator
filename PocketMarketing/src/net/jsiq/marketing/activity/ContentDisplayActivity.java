package net.jsiq.marketing.activity;

import net.jsiq.marketing.R;
import net.jsiq.marketing.constants.URLStrings;
import net.jsiq.marketing.util.ViewHelper;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

public class ContentDisplayActivity extends SherlockActivity implements
		OnClickListener {

	public static final String CONTENT_ID = "content_id";
	private WebView mWebView;
	private ImageButton menuSetting;
	private PopupWindow popupWindow;
	private String[] title = { "设置", "分享" };

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
		menuSetting = (ImageButton) actionbarView
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
			int y = menuSetting.getBottom() * 3 / 2;
			int x = getWindowManager().getDefaultDisplay().getWidth() / 4;
			showPopupWindow(x, y);
			break;
		default:
			break;
		}
	}

	public void showPopupWindow(int x, int y) {
		LinearLayout layout = (LinearLayout) LayoutInflater.from(this).inflate(
				R.layout.popup_window_dialog, null);
		ListView listView = (ListView) layout.findViewById(R.id.lv_dialog);
		listView.setAdapter(new ArrayAdapter<String>(this,
				R.layout.popup_window_text, R.id.tv_text, title));

		popupWindow = new PopupWindow(this);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow
				.setWidth(getWindowManager().getDefaultDisplay().getWidth() / 2);
		popupWindow.setHeight(300);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setFocusable(true);
		popupWindow.setContentView(layout);
		// showAsDropDown会把里面的view作为参照物，所以要那满屏幕parent
		// popupWindow.showAsDropDown(findViewById(R.id.tv_title), x, 10);
		popupWindow.showAtLocation(findViewById(R.id.content_display),
				Gravity.RIGHT | Gravity.TOP, x, y);// 需要指定Gravity，默认情况是center.

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				popupWindow.dismiss();
				popupWindow = null;
			}
		});
	}

}
