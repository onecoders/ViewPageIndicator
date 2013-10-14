package com.intro.compintro.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.intro.compintro.R;
import com.intro.compintro.util.Action;

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
		switch (position) {
		case 0:
			i = new Intent(Action.ACTION_BASIC_INFO);
			break;
		case 1:
			i = new Intent(Action.ACTION_MARKETING);
			break;
		case 2:
			i = new Intent(Action.ACTION_MAIN_PRODUCT);
			break;
		case 3:
			i = new Intent(Action.ACTION_OTHER_PRODUCT);
			break;
		}
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
	}

}
