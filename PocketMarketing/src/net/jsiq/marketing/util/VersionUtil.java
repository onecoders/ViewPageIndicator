package net.jsiq.marketing.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class VersionUtil {

	public static String getVersionName(Context context)
			throws NameNotFoundException {
		PackageManager packageManager = context.getPackageManager();
		PackageInfo packInfo = packageManager.getPackageInfo(
				context.getPackageName(), 0);
		return packInfo.versionName;
	}

}
