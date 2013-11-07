package net.jsiq.marketing.mainfragment;

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
import net.jsiq.marketing.R;
import net.jsiq.marketing.innerfragment.SampleListFragment;
import net.jsiq.marketing.util.ViewHelper;

import com.viewpagerindicator.TabPageIndicator;

public class BasicInfoFragment extends SherlockFragment {

	private static final String BASIC_INFO_POS = "basic_info_pos";
	private int currentPos = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		ActionBar actionBar = getSherlockActivity().getSupportActionBar();
		ViewHelper.setActionBarCustomerViewContent(actionBar,
				R.drawable.basic_info_back, R.string.basic_info_title);
	}

	private FragmentPagerAdapter adapter;
	private ViewPager pager;
	private TabPageIndicator indicator;

	private static final String[] CONTENT = new String[] { "公司介绍", "发展历程",
			"公司实力", "资质荣誉", "联系我们" };

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
		indicator.setCurrentItem(currentPos);

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

	class BasicInfoAdapter extends FragmentPagerAdapter {

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
		public int getCount() {
			return CONTENT.length;
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		currentPos = pager.getCurrentItem();
		outState.putInt(BASIC_INFO_POS, currentPos);
	}

}
