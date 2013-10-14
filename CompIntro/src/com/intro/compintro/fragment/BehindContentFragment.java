package com.intro.compintro.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.intro.compintro.R;
import com.intro.compintro.adapter.BehindMenuAdapter;
import com.intro.compintro.datastruct.MenuItem;
import com.intro.compintro.util.Action;

public class BehindContentFragment extends SherlockListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.behind_menu, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String[] menuItemNamesZh = getResources().getStringArray(
				R.array.menu_items_name_zh);
		String[] menuItemNamesEn = getResources().getStringArray(
				R.array.menu_items_name_en);
		int[] icon = getResources().getIntArray(R.array.menu_item_icon);
		List<MenuItem> menuList = new ArrayList<MenuItem>();

		for (int i = 0; i < icon.length; i++) {
			menuList.add(new MenuItem(icon[i], menuItemNamesZh[i],
					menuItemNamesEn[i]));
		}

		ArrayAdapter<MenuItem> adapter = new BehindMenuAdapter(
				getSherlockActivity(), menuList);
		setListAdapter(adapter);
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
		getSherlockActivity().overridePendingTransition(R.anim.zoomin,
				R.anim.zoomout);
	}
	
}
