package net.jsiq.marketing.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {

	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (mConnectivityManager != null) {
				NetworkInfo mNetworkInfo = mConnectivityManager
						.getActiveNetworkInfo();
				if (mNetworkInfo != null) {
					return mNetworkInfo.isAvailable();
				}
			}
		}
		return false;
	}

	public static boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnecttivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (mConnecttivityManager != null) {
				NetworkInfo mWifiNetworkInfo = mConnecttivityManager
						.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
				if (mWifiNetworkInfo != null) {
					return mWifiNetworkInfo.isAvailable();
				}
			}
		}
		return false;
	}

	public static boolean isMobileConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnecttivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (mConnecttivityManager != null) {
				NetworkInfo mMobileNetworkInfo = mConnecttivityManager
						.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
				if (mMobileNetworkInfo != null) {
					return mMobileNetworkInfo.isAvailable();
				}
			}
		}
		return false;
	}

	public static int getConnectedType(Context context) {
		if (context != null) {
			ConnectivityManager mConnecttivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (mConnecttivityManager != null) {
				NetworkInfo mNetworkInfo = mConnecttivityManager
						.getActiveNetworkInfo();
				if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
					return mNetworkInfo.getType();
				}
			}
		}
		return -1;
	}

}
