package com.intro.compintro.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.actionbarsherlock.app.ActionBar;
import com.intro.compintro.R;
import com.intro.compintro.mainfragment.BehindContentFragment;
import com.intro.compintro.mainfragment.SecondaryMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class BaseActivity extends SlidingFragmentActivity implements
		OnClickListener {

	protected BehindContentFragment mFrag;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initActinBar();
		initSlidingMenu();
	}

	private void initSlidingMenu() {
		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setMode(SlidingMenu.LEFT_RIGHT);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		// set the Behind View
		setBehindContentView(R.layout.menu_frame);
		FragmentTransaction t = this.getSupportFragmentManager()
				.beginTransaction();
		mFrag = new BehindContentFragment();
		t.replace(R.id.menu_frame, mFrag);
		t.commit();

		// set the secondaryMenu
		sm.setSecondaryMenu(R.layout.menu_frame_two);
		sm.setSecondaryShadowDrawable(R.drawable.shadowright);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame_two, new SecondaryMenuFragment())
				.commit();
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
				R.layout.actionbar, null);
		ImageButton menuBtn = (ImageButton) actionbarView
				.findViewById(R.id.menu_btn);
		menuBtn.setOnClickListener(this);
		ImageButton personalBtn = (ImageButton) actionbarView
				.findViewById(R.id.personal_btn);
		personalBtn.setOnClickListener(this);
		// Now set custom view
		actionBar.setCustomView(actionbarView, params);
	}

	@Override
	public void onClick(View v) {
		SlidingMenu sm = getSlidingMenu();
		switch (v.getId()) {
		case R.id.menu_btn:
			if (sm.isMenuShowing()) {
				showContent();
			} else {
				getSlidingMenu().showMenu();
			}
			break;
		case R.id.personal_btn:
			if (sm.isSecondaryMenuShowing()) {
				showContent();
			} else {
				sm.showSecondaryMenu();
			}
			break;
		default:
			break;
		}
	}

	protected void resetSelectedItem() {
		mFrag.getAdapter().setSelectItem(0);
	}

}
