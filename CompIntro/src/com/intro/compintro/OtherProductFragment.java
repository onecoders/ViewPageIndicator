package com.intro.compintro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.viewpagerindicator.TabPageIndicator;

public class OtherProductFragment extends SherlockFragment {

	private static final String[] TITLE = new String[] { "A产品", "B产品", "C产品",
			"D产品", "E产品" };

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.index_main, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(
				getChildFragmentManager());
		ViewPager pager = (ViewPager) getActivity().findViewById(R.id.pager);
		pager.setAdapter(adapter);

		TabPageIndicator indicator = (TabPageIndicator) getActivity()
				.findViewById(R.id.indicator);
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

	/**
	 * 
	 * @author silicon
	 * 
	 */
	class TabPageIndicatorAdapter extends FragmentPagerAdapter {
		public TabPageIndicatorAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = new ItemFragment();
			Bundle args = new Bundle();
			args.putString("arg", TITLE[position]);
			fragment.setArguments(args);

			return fragment;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLE[position % TITLE.length];
		}

		@Override
		public int getCount() {
			return TITLE.length;
		}
	}
}
