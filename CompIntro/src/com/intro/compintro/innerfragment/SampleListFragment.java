package com.intro.compintro.innerfragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.intro.compintro.R;
import com.intro.compintro.util.NetworkUtils;

public class SampleListFragment extends SherlockFragment implements
		OnScrollListener, OnClickListener {

	public static final String CONTENT_KEY = "content_key";

	private String content;
	private int lastVisibleIndex = 0;
	private SampleAdapter adapter;
	private View loadingLayout;
	private LinearLayout loadingProgress;
	private Button loadingAgain;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View convertView = inflater.inflate(R.layout.headerview_list, null);
		// get content
		content = getArguments().getString(CONTENT_KEY);
		//
		ListView listview = (ListView) convertView.findViewById(R.id.news_list);
		// add header view
		ImageView imageview = (ImageView) inflater.inflate(
				R.layout.listview_image_item, null);
		imageview.setImageResource(R.drawable.biz_plugin_weather_beijin);
		listview.addHeaderView(imageview);
		// init footer view
		loadingLayout = inflater
				.inflate(R.layout.slip_to_padding_refresh, null);
		listview.addFooterView(loadingLayout);
		// footer view
		loadingProgress = (LinearLayout) loadingLayout
				.findViewById(R.id.loadingProgress);
		loadingAgain = (Button) loadingLayout.findViewById(R.id.loadingAgain);
		loadingAgain.setOnClickListener(this);
		// init adapter
		adapter = new SampleAdapter(getSherlockActivity());
		// get from server, do task in background
		new LoadingDataAsyncTask().execute();
		listview.setAdapter(adapter);
		listview.setOnScrollListener(this);

		return convertView;
	}

	private class SampleItem {
		public String tag;
		public int iconRes;

		public SampleItem(String tag, int iconRes) {
			this.tag = tag;
			this.iconRes = iconRes;
		}
	}

	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.row, null);
			}
			ImageView icon = (ImageView) convertView
					.findViewById(R.id.row_icon);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView
					.findViewById(R.id.row_title);
			title.setText(getItem(position).tag);

			return convertView;
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// header view considered, firstVisibleItem is header view
		lastVisibleIndex = firstVisibleItem + visibleItemCount - 2;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (lastVisibleIndex == adapter.getCount()
				&& scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
			// get more 20 from server (do this task in other thread)
			new LoadingDataAsyncTask().execute();
		}
	}

	class LoadingDataAsyncTask extends AsyncTask<Void, Void, Boolean> {

		private List<SampleItem> moreItems;

		@Override
		protected Boolean doInBackground(Void... params) {
			// also consider the net connection, if not, failure
			if (!NetworkUtils.isNetworkConnected(getSherlockActivity())) {
				return false;
			} else {
				try {
					// Simulation
					moreItems = new ArrayList<SampleItem>();
					int count = adapter.getCount() + 1;
					for (int i = count; i < count + 20; i++) {
						moreItems.add(new SampleItem(content + i,
								android.R.drawable.ic_menu_search));
					}
					return true;
				} catch (Exception e) {
					return false;
				}
			}
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				loadingProgress.setVisibility(View.VISIBLE);
				loadingAgain.setVisibility(View.GONE);
				adapter.addAll(moreItems);
				adapter.notifyDataSetChanged();
			} else {
				Toast.makeText(getSherlockActivity(), R.string.netUnavailable,
						Toast.LENGTH_SHORT).show();
				loadingProgress.setVisibility(View.GONE);
				loadingAgain.setVisibility(View.VISIBLE);
			}
		}

	}

	@Override
	public void onClick(View v) {
		new LoadingDataAsyncTask().execute();
	}

}
