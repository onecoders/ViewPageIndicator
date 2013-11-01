package com.intro.compintro.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.intro.compintro.R;

public class SettingActivity extends SherlockPreferenceActivity implements
		OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initActinBar();
		addPreferencesFromResource(R.xml.preference);
	}

	public void initActinBar() {
		ActionBar actionBar = getSupportActionBar();
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
		// Get custom view
		View actionbarView = LayoutInflater.from(this).inflate(
				R.layout.actionbar_configure, null);
		ImageButton menuBtn = (ImageButton) actionbarView
				.findViewById(R.id.back_btn);
		menuBtn.setOnClickListener(this);
		TextView title = (TextView) actionbarView.findViewById(R.id.title);
		title.setText(R.string.configure);
		// Now set custom view
		actionBar.setCustomView(actionbarView, params);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.back_btn) {
			onBackPressed();
		}
	}
}
