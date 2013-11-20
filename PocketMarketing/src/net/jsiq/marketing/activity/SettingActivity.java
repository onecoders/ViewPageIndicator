package net.jsiq.marketing.activity;

import net.jsiq.marketing.R;
import net.jsiq.marketing.constants.Constants;
import net.jsiq.marketing.util.LoaderUtil;
import net.jsiq.marketing.util.MessageToast;
import net.jsiq.marketing.util.VersionUtil;
import net.jsiq.marketing.util.ViewHelper;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

public class SettingActivity extends SherlockPreferenceActivity implements
		OnClickListener, OnPreferenceClickListener {

	private static final String PREFS_NAME = "compintro";
	private SharedPreferences m_prefs;
	private Editor editor;
	private Preference clearCache, feedback, versionInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initActionBar();
		m_prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		editor = m_prefs.edit();
		String versionName = getVersionName();
		addPreferencesFromResource(R.xml.preference);

		clearCache = findPreference("clear_cache");
		clearCache.setOnPreferenceClickListener(this);

		feedback = findPreference("feedback");
		feedback.setOnPreferenceClickListener(this);

		versionInfo = findPreference("version_info");
		versionInfo.setSummary(versionName);
	}

	private String getVersionName() {
		String version = getString(R.string.version_info_sum);
		try {
			version += VersionUtil.getVersionName(this);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			version = getString(R.string.detect_verison_failed);
		}
		return version;
	}

	private void initActionBar() {
		ActionBar actionBar = getSupportActionBar();
		// Get custom view
		View customerView = loadCustomerView();
		// Now set custom view
		ViewHelper.initActionBarAndSetCustomerView(actionBar, customerView);
	}

	private View loadCustomerView() {
		View actionbarView = LayoutInflater.from(this).inflate(
				R.layout.actionbar_configure, null);
		ImageButton menuBtn = (ImageButton) actionbarView
				.findViewById(R.id.back_btn);
		menuBtn.setOnClickListener(this);
		TextView title = (TextView) actionbarView.findViewById(R.id.title);
		title.setText(R.string.configure);
		return actionbarView;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.back_btn) {
			onBackPressed();
		}
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		if (preference.getKey().equals("clear_cache")) {
			showClearCacheDialog();
		} else if (preference.getKey().equals("feedback")) {
			sendFeedback();
		}
		return false;
	}

	private void showClearCacheDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.clearCacheTitle);
		builder.setMessage(R.string.comfirmToClearCache);
		builder.setPositiveButton(R.string.OK,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						clearCache();
					}

				});
		builder.setNegativeButton(R.string.cancle, null);
		builder.create().show();
	}

	private void clearCache() {
		ImageLoader imageLoader = LoaderUtil.getImageLoader(this);
		imageLoader.clearDiscCache();
		imageLoader.clearMemoryCache();
	}

	private void sendFeedback() {
		Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",
				Constants.FEEDBACK_ADDRESS, null));
		try {
			startActivity(Intent.createChooser(i,
					getResources().getString(R.string.sendMail)));
		} catch (ActivityNotFoundException e) {
			MessageToast.showText(this, R.string.sendError);
		}
	}
}
