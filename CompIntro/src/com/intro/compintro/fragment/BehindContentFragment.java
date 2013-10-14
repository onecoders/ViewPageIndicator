package com.intro.compintro.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.intro.compintro.R;
import com.intro.compintro.activity.BasicInfoActivity;
import com.intro.compintro.activity.MainProductActivity;
import com.intro.compintro.activity.MarketingActivity;
import com.intro.compintro.activity.OtherProductActivity;

public class BehindContentFragment extends SherlockListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String[] colors = getResources().getStringArray(R.array.color_names);
		ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_list_item_1,
				android.R.id.text1, colors);
		setListAdapter(colorAdapter);
	}

	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {
		Intent i = null;
		Context context = getSherlockActivity();
		switch (position) {
		case 0:
			i = new Intent(context, BasicInfoActivity.class);
			break;
		case 1:
			i = new Intent(context, MarketingActivity.class);
			break;
		case 2:
			i = new Intent(context, MainProductActivity.class);
			break;
		case 3:
			i = new Intent(context, OtherProductActivity.class);
			break;
		}
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
	}

}
