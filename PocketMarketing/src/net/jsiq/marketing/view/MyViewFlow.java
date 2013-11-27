package net.jsiq.marketing.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;

public class MyViewFlow extends ViewFlow {

	private float mLastMotionX;
	private ViewPager pager;

	public MyViewFlow(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		ViewParent viewParent = getParent().getParent().getParent().getParent()
				.getParent().getParent().getParent();
		if (viewParent instanceof ViewPager) {
			pager = (ViewPager) viewParent;
		}

		final int action = ev.getAction();
		final float x = ev.getX();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			mLastMotionX = x;

			getParent().requestDisallowInterceptTouchEvent(true);
			pager.requestDisallowInterceptTouchEvent(true);
			break;

		case MotionEvent.ACTION_MOVE:
			if (mLastMotionX == x) {
				if (0 == getSelectedItemPosition()
						|| getSelectedItemPosition() == getChildCount() - 1) {
					pager.requestDisallowInterceptTouchEvent(true);
					pager.getParent().requestDisallowInterceptTouchEvent(true);
				}
			} else if (mLastMotionX > x) {
				if (getSelectedItemPosition() == getChildCount() - 1) {
					if (pager.getCurrentItem() == pager.getAdapter().getCount() - 1) {
						pager.requestDisallowInterceptTouchEvent(true);
						pager.getParent().requestDisallowInterceptTouchEvent(
								false);
					} else {
						pager.requestDisallowInterceptTouchEvent(false);
						pager.getParent().requestDisallowInterceptTouchEvent(
								true);
					}
				}
			} else if (mLastMotionX < x) {
				if (getSelectedItemPosition() == 0) {
					if (pager.getCurrentItem() == 0) {
						pager.requestDisallowInterceptTouchEvent(true);
						pager.getParent().requestDisallowInterceptTouchEvent(
								false);
					} else {
						pager.requestDisallowInterceptTouchEvent(false);
						pager.getParent().requestDisallowInterceptTouchEvent(
								true);
					}
				}
			} else {
				pager.requestDisallowInterceptTouchEvent(true);
				pager.getParent().requestDisallowInterceptTouchEvent(true);
			}

			break;

		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			pager.requestDisallowInterceptTouchEvent(true);
			pager.getParent().requestDisallowInterceptTouchEvent(true);
		}
		return super.onInterceptTouchEvent(ev);
	}

}