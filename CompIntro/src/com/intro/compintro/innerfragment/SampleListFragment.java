package com.intro.compintro.innerfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.intro.compintro.R;

public class SampleListFragment extends SherlockFragment {

	public static final String CONTENT_KEY = "content_key";

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		String content = getArguments().getString(CONTENT_KEY);
		View convertView = inflater.inflate(R.layout.headerview_list, null);
		ListView listview = (ListView) convertView.findViewById(R.id.news_list);
		ImageView imageview = (ImageView) inflater.inflate(
				R.layout.listview_image_item, null);
		imageview.setImageResource(R.drawable.biz_plugin_weather_beijin);
		listview.addHeaderView(imageview);
		SampleAdapter adapter = new SampleAdapter(getActivity());
		for (int i = 0; i < 20; i++) {
			adapter.add(new SampleItem(content,
					android.R.drawable.ic_menu_search));
		}
		listview.setAdapter(adapter);

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

}
