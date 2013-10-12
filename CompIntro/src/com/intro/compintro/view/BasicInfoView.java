package com.intro.compintro.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockFragment;
import com.intro.compintro.R;
import com.intro.compintro.fragment.ItemFragment;
import com.viewpagerindicator.TabPageIndicator;

public class BasicInfoView extends LinearLayout {

	private SherlockFragment fragment;

	private static final String[] CONTENT = new String[] { /*"公司介绍", "发展历程",
			"公司实力", "资质容易", "联系我们"*/"头条", "房产", "另一面",
			"女人", "财经", "数码", "情感", "科技" };

	private BasicInfoView(Context context) {
		super(context);
	}

	public BasicInfoView(Context context, SherlockFragment fragment) {
		this(context);
		this.fragment = fragment;
		inflate(context, R.layout.index_main, this);
		init();
	}

	private void init() {
		FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(
				fragment.getChildFragmentManager());
		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(adapter);
		
		TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(pager);

		indicator.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				//
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	class TabPageIndicatorAdapter extends FragmentPagerAdapter {

		public TabPageIndicatorAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = new ItemFragment();
			Bundle args = new Bundle();
			args.putString("arg", CONTENT[position]);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return CONTENT[position % CONTENT.length];
		}

		@Override
		public int getCount() {
			return CONTENT.length;
		}
	}

}
