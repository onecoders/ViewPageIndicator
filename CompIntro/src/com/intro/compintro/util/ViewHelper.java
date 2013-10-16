package com.intro.compintro.util;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.intro.compintro.R;

public class ViewHelper {

	public static void setActionBarContent(ActionBar actionbar, int backIconId,
			int titleId) {
		View actionbarView = actionbar.getCustomView();
		if (actionbarView != null) {
			ImageButton menuBtn = (ImageButton) actionbarView
					.findViewById(R.id.menu_btn);
			menuBtn.setBackgroundResource(backIconId);
			TextView tv = (TextView) actionbarView.findViewById(R.id.title);
			tv.setText(titleId);
		}
	}

}
