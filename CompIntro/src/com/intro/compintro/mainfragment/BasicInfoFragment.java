package com.intro.compintro.mainfragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.intro.compintro.R;
import com.intro.compintro.innerfragment.SampleListFragment;
import com.intro.compintro.util.ViewHelper;
import com.viewpagerindicator.IconPagerAdapter;
import com.viewpagerindicator.TabPageIndicator;

public class BasicInfoFragment extends SherlockFragment {

	private static final String BASIC_INFO_POS = "basic_info_pos";
	private int currentPos = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getSherlockActivity().getSupportActionBar();
		ViewHelper.setActionBarContent(actionBar, R.drawable.basic_info_back,
				R.string.basic_info_title);
	}

	private FragmentPagerAdapter adapter;
	private ViewPager pager;
	private TabPageIndicator indicator;

	private static final String[] CONTENT = new String[] { "公司介绍", "发展历程",
			"公司实力", "资质荣誉", "联系我们" };
	private static final int[] ICONS = new int[] {
			R.drawable.perm_group_calendar, R.drawable.perm_group_calendar,
			R.drawable.perm_group_calendar, R.drawable.perm_group_calendar,
			R.drawable.perm_group_calendar, };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View convertView = inflater.inflate(R.layout.simple_tabs, container,
				false);
		adapter = new BasicInfoAdapter(getChildFragmentManager());

		pager = (ViewPager) convertView.findViewById(R.id.pager);
		pager.setAdapter(adapter);

		indicator = (TabPageIndicator) convertView.findViewById(R.id.indicator);
		indicator.setViewPager(pager);
		if (savedInstanceState != null) {
			currentPos = savedInstanceState.getInt(BASIC_INFO_POS);
		}
		pager.setCurrentItem(currentPos);

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
		return convertView;
	}

	class BasicInfoAdapter extends FragmentPagerAdapter implements
			IconPagerAdapter {
		public BasicInfoAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public SherlockFragment getItem(int position) {
			Bundle extra = new Bundle();
			extra.putString(SampleListFragment.CONTENT_KEY, CONTENT[position]);
			SampleListFragment fragment = new SampleListFragment();
			fragment.setArguments(extra);
			return fragment;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return CONTENT[position % CONTENT.length];
		}

		@Override
		public int getIconResId(int index) {
			return ICONS[index];
		}

		@Override
		public int getCount() {
			return CONTENT.length;
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		currentPos = pager.getCurrentItem();
		super.onSaveInstanceState(outState);
		outState.putInt(BASIC_INFO_POS, currentPos);
	}

}
