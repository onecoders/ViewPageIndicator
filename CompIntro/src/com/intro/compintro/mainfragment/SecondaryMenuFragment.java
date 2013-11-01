package com.intro.compintro.mainfragment;

import java.util.ArrayList;
import java.util.List;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.actionbarsherlock.app.SherlockFragment;
import com.intro.compintro.R;
import com.intro.compintro.adapter.SecondaryMenuAdapter;
import com.intro.compintro.model.SecondaryMenuItem;

public class SecondaryMenuFragment extends SherlockFragment implements
		OnItemClickListener {

	private GridView gridview;

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
		gridview = (GridView) convertView.findViewById(R.id.gridview);
		gridview.setAdapter(adapter);
		gridview.setOnItemClickListener(this);
		iconArray.recycle();
		return convertView;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
		switch (position) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			toOnekeyShare();
			break;
		default:
			break;
		}
	}

	private void toOnekeyShare() {
		OnekeyShare oks = new OnekeyShare();
		oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
		oks.setTitle(getString(R.string.app_name));
		oks.setText(getString(R.string.app_name));
		oks.setUrl("http://sharesdk.cn");
		oks.setSilent(true);
		oks.show(getSherlockActivity());
	}
}
