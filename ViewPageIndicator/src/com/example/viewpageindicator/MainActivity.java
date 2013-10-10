package com.example.viewpageindicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.Toast;

import com.viewpagerindicator.TabPageIndicator;

public class MainActivity extends FragmentActivity {
	/**
	 * Tab����
	 */
	private static final String[] TITLE = new String[] { "头条", "房产", "另一面",
			"女人", "财经", "数码", "情感", "科技" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ViewPager��adapter
		FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(
				getSupportFragmentManager());
		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(adapter);

		// ʵ��TabPageIndicatorȻ������ViewPager��֮����
		TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(pager);

		// �������Ҫ��ViewPager���ü�����indicator���þ�����
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
	 * ViewPager������
	 * 
	 * @author len
	 * 
	 */
	class TabPageIndicatorAdapter extends FragmentPagerAdapter {
		public TabPageIndicatorAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// �½�һ��Fragment��չʾViewPager item�����ݣ������ݲ���
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
