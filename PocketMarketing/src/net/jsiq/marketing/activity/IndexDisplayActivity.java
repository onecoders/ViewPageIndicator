package net.jsiq.marketing.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import net.jsiq.marketing.R;
import net.jsiq.marketing.adapter.IndexViewFlowAdapter;
import net.jsiq.marketing.constants.URLStrings;
import net.jsiq.marketing.model.MenuItem;
import net.jsiq.marketing.model.Weather;
import net.jsiq.marketing.util.BitmapUtil;
import net.jsiq.marketing.util.LoaderUtil;
import net.jsiq.marketing.util.MessageToast;
import net.jsiq.marketing.util.NetworkUtils;
import net.jsiq.marketing.view.CircleFlowIndicator;
import net.jsiq.marketing.view.ViewFlow;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;

public class IndexDisplayActivity extends SherlockActivity implements
		OnClickListener {

	private static final int MENU_PARTY_COUNT = 5;
	public static final String MENU_SELECTED_POS = "menu_selected_pos";

	private ViewFlow viewFlow;
	private TextView date_y, week, city, temp1;
	private ImageView weather_image;
	private ImageView[] indexImageViews;
	private TextView[] indexTextViews;
	private CircleFlowIndicator indic;

	private View indexContainer, loadingHintView, loadingFailedHintView;

	private int[][] resId = new int[][] {
			{ R.id.index_first_image, R.id.index_first_text },
			{ R.id.index_second_image, R.id.index_second_text },
			{ R.id.index_third_image, R.id.index_third_text },
			{ R.id.index_forth_image, R.id.index_forth_text },
			{ R.id.index_fifth_image, R.id.index_fifth_text } };

	private List<String> urls;
	private List<MenuItem> menuItems;
	private Weather weather;

	private static Boolean isExit = false;
	private boolean currentNetworkConnected;

	private BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			boolean connected = NetworkUtils.isNetworkConnected(context);
			if (currentNetworkConnected) {
				if (!connected) {
					MessageToast.showText(context, R.string.networkInterupt);
				}
			} else {
				if (connected) {
					MessageToast.showText(context, R.string.networkConnected);
					if (loadingFailedHintView.isShown()) {
						loadContents();
					}
				}
			}
			currentNetworkConnected = connected;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index);
		init();
	}

	private void init() {
		currentNetworkConnected = NetworkUtils.isNetworkConnected(this);
		urls = new ArrayList<String>();
		// simulation to load data
		urls.add("http://f.hiphotos.baidu.com/image/w%3D1366%3Bcrop%3D0%2C0%2C1366%2C768/sign=7266c1c3abec8a13141a53e3c135aaec/aa64034f78f0f7368a89a92d0855b319ebc413a2.jpg");
		urls.add("http://d.hiphotos.baidu.com/image/w%3D1366%3Bcrop%3D0%2C0%2C1366%2C768/sign=83a450cbaa18972ba33a04c9d0fb40ea/6d81800a19d8bc3ee2876a15838ba61ea9d34565.jpg");
		urls.add("http://e.hiphotos.baidu.com/image/w%3D1366%3Bcrop%3D0%2C0%2C1366%2C768/sign=842e83c40df3d7ca0cf63b75c429856a/f9198618367adab456e1dfb98ad4b31c8701e413.jpg");

		registerReceiver();
		findViews();
		setListeners();
		loadContents();
	}

	private void findViews() {
		indexContainer = findViewById(R.id.index_container);
		viewFlow = (ViewFlow) findViewById(R.id.index_viewflow);
		// TextView date_y, week, city, temp1;
		date_y = (TextView) findViewById(R.id.date_y);
		week = (TextView) findViewById(R.id.week);
		city = (TextView) findViewById(R.id.city);
		temp1 = (TextView) findViewById(R.id.temp1);
		// weather image
		weather_image = (ImageView) findViewById(R.id.weather_image);
		// five menu parts
		indexImageViews = new ImageView[MENU_PARTY_COUNT];
		indexTextViews = new TextView[MENU_PARTY_COUNT];
		for (int i = 0; i < MENU_PARTY_COUNT; i++) {
			indexImageViews[i] = (ImageView) findViewById(resId[i][0]);
			indexTextViews[i] = (TextView) findViewById(resId[i][1]);
		}
		indic = (CircleFlowIndicator) findViewById(R.id.viewflowindic);
		loadingHintView = findViewById(R.id.loadingHint);
		loadingFailedHintView = findViewById(R.id.loadingFailedHint);
	}

	private void setListeners() {
		loadingFailedHintView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.loadingFailedHint:
			loadContents();
			break;
		default:
			break;
		}
	}

	private void loadContents() {
		indexContainer.setVisibility(View.GONE);
		if (NetworkUtils.isNetworkConnected(this)) {
			new LoadIndexContentTask().execute(URLStrings.GET_WEATHER_INFO,
					URLStrings.GET_MENUS);
		} else {
			loadingFailedHintView.setVisibility(View.VISIBLE);
			MessageToast.showText(this, R.string.notConnected);
		}
	}

	class LoadIndexContentTask extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			loadingFailedHintView.setVisibility(View.GONE);
			loadingHintView.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(String... params) {
			try {
				weather = LoaderUtil.loadWeatherData(params[0]);
				menuItems = LoaderUtil.loadMenuItems(params[1]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			loadingHintView.setVisibility(View.GONE);
			showWeatherInfo();
			if (menuItems == null) {
				loadingFailedHintView.setVisibility(View.VISIBLE);
				MessageToast.showText(IndexDisplayActivity.this,
						R.string.loadFailed);
			} else {
				initIndexViewFlow();
				initIndexFiveParts();
				indexContainer.setVisibility(View.VISIBLE);
			}
		}
	}

	private void initIndexViewFlow() {
		viewFlow.setAdapter(new IndexViewFlowAdapter(this, urls));
		viewFlow.setmSideBuffer(urls.size());
		viewFlow.setFlowIndicator(indic);
		viewFlow.setTimeSpan(4500);
		viewFlow.setSelection(0);
	}

	private void showWeatherInfo() {
		if (weather != null) {
			date_y.setText(weather.getDate_y());
			week.setText(weather.getWeek());
			city.setText(weather.getCity());
			temp1.setText(weather.getTemp1());
			String weatherImageUrl = BitmapUtil
					.getWeatherImg(weather.getImg1());
			LoaderUtil.displayImage(this, weatherImageUrl, weather_image);
		} else {
			MessageToast.showText(this, R.string.loadWeatherError);
		}
	}

	private void initIndexFiveParts() {
		for (int i = 0; i < indexImageViews.length; i++) {
			MenuItem item = menuItems.get(i);
			if (item != null) {
				final int menuPos = i;
				LoaderUtil.displayImage(this, item.getMenuIcon(),
						indexImageViews[i]);
				indexTextViews[i].setText(item.getMenuName());
				View parent = (View) indexImageViews[i].getParent();
				parent.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = new Intent(IndexDisplayActivity.this,
								MainActivity.class);
						intent.putExtra(MENU_SELECTED_POS, menuPos);
						startActivity(intent);
					}
				});
			}
		}
	}

	@Override
	public void onBackPressed() {
		exitBy2Click();
	}

	private void exitBy2Click() {
		Timer tExit = null;
		if (isExit) {
			finish();
			System.exit(0);
		} else {
			isExit = true;
			MessageToast.showText(this, R.string.clickAgain);
			tExit = new Timer();
			tExit.schedule(new TimerTask() {

				@Override
				public void run() {
					isExit = false;
				}
			}, 2000);
		}
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver();
		super.onDestroy();
	}

	private void registerReceiver() {
		IntentFilter filter = new IntentFilter();
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		if (null != receiver) {
			registerReceiver(receiver, filter);
		}
	}

	private void unregisterReceiver() {
		if (null != receiver) {
			unregisterReceiver(receiver);
		}
	}

}
