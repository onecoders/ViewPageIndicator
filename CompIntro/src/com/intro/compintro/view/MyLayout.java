package com.intro.compintro.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class MyLayout extends RelativeLayout {

	ViewPager childViewpager;
	float startX;

	public MyLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			startX = ev.getX();
			getParent().requestDisallowInterceptTouchEvent(true);
			break;
		case MotionEvent.ACTION_MOVE:
			if (startX == ev.getX()) {
				if (0 == childViewpager.getCurrentItem()
						|| childViewpager.getCurrentItem() == childViewpager
								.getAdapter().getCount() - 1) {
					getParent().requestDisallowInterceptTouchEvent(false);
				}
			} else if (startX > ev.getX()) {
				if (childViewpager.getCurrentItem() == childViewpager
						.getAdapter().getCount() - 1) {
					getParent().requestDisallowInterceptTouchEvent(false);
				}
			} else if (startX < ev.getX()) {
				if (childViewpager.getCurrentItem() == 0) {
					getParent().requestDisallowInterceptTouchEvent(false);
				}
			} else {
				getParent().requestDisallowInterceptTouchEvent(true);
			}
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			getParent().requestDisallowInterceptTouchEvent(false);
		default:
			break;
		}

		return false;
	}

	public void setChildViewpager(ViewPager childViewpager) {
		this.childViewpager = childViewpager;
	}

}
