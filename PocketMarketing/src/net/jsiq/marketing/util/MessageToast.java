package net.jsiq.marketing.util;

import android.content.Context;
import android.widget.Toast;

public class MessageToast {

	private static Toast toast = null;

	public static Toast makeText(Context context, int resId, int duration) {
		if (toast == null) {
			toast = Toast.makeText(context, resId, duration);
		} else {
			toast.setText(resId);
		}
		return toast;
	}

	public static void show() {
		toast.show();
	}

}
