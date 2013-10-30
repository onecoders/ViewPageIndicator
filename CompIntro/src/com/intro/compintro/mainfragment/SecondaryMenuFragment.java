package com.intro.compintro.mainfragment;

import java.util.ArrayList;
import java.util.List;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.actionbarsherlock.app.SherlockFragment;
import com.intro.compintro.R;
import com.intro.compintro.adapter.SecondaryMenuAdapter;
import com.intro.compintro.model.SecondaryMenuItem;

public class SecondaryMenuFragment extends SherlockFragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View convertView = inflater.inflate(R.layout.secondary_menu, null);
		String[] menuItemTitles = getResources().getStringArray(
				R.array.secondary_menu_title);
		TypedArray iconArray = getResources().obtainTypedArray(
				R.array.secondary_menu_icon);
		List<SecondaryMenuItem> menuList = new ArrayList<SecondaryMenuItem>();

		for (int i = 0; i < iconArray.length(); i++) {
			menuList.add(new SecondaryMenuItem(iconArray.getResourceId(i, 0),
					menuItemTitles[i]));
		}
		ArrayAdapter<SecondaryMenuItem> adapter = new SecondaryMenuAdapter(
				getSherlockActivity(), menuList);
		GridView gridview = (GridView) convertView.findViewById(R.id.gridview);
		gridview.setAdapter(adapter);
		iconArray.recycle();
		return convertView;
	}
}
