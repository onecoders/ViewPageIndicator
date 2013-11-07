package net.jsiq.marketing.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager {

	float startX;

	public MyViewPager(Context context, AttributeSet attrs) {
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
				if (0 == this.getCurrentItem()
						|| this.getCurrentItem() == this.getAdapter()
								.getCount() - 1) {
					getParent().requestDisallowInterceptTouchEvent(true);
				}
			} else if (startX > ev.getX()) {
				if (this.getCurrentItem() == this.getAdapter().getCount() - 1) {
					getParent().requestDisallowInterceptTouchEvent(false);
				}
			} else if (startX < ev.getX()) {
				if (this.getCurrentItem() == 0) {
					getParent().requestDisallowInterceptTouchEvent(false);
				}
			} else {
				getParent().requestDisallowInterceptTouchEvent(true);
			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			getParent().requestDisallowInterceptTouchEvent(false);
			break;
		default:
			break;
		}
		return super.onInterceptTouchEvent(ev);
	}

}
