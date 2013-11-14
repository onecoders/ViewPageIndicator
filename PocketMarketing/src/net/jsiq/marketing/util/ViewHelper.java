package net.jsiq.marketing.util;

import net.jsiq.marketing.R;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;

public class ViewHelper {

	public static void initActionBarAndSetCustomerView(ActionBar actionBar,
			View customerView) {
		// set LayoutParams
		ActionBar.LayoutParams params = new ActionBar.LayoutParams(
				ActionBar.LayoutParams.WRAP_CONTENT,
				ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
		// Set display to custom next
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		// Do any other config to the action bar
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		// Now set custom view
		actionBar.setCustomView(customerView, params);
	}

	public static void setActionBarCustomerViewContent(ActionBar actionbar,
			String title) {
		View actionbarView = actionbar.getCustomView();
		if (actionbarView != null) {
			TextView tv = (TextView) actionbarView.findViewById(R.id.title);
			tv.setText(title);
		}
	}

}
