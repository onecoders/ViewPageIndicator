package net.jsiq.marketing.fragment;

import java.util.ArrayList;
import java.util.List;

import net.jsiq.marketing.R;
import net.jsiq.marketing.activity.CollectionsActivity;
import net.jsiq.marketing.activity.SearchActivity;
import net.jsiq.marketing.activity.SettingActivity;
import net.jsiq.marketing.adapter.RightMenuAdapter;
import net.jsiq.marketing.constants.URLStrings;
import net.jsiq.marketing.model.SecondaryMenuItem;
import net.jsiq.marketing.util.LoaderUtil;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockFragment;

public class RightMenuFragment extends SherlockFragment implements
		OnItemClickListener {

	private GridView gridview;
	private Context context;

	private static final int POS_SEARCH = 0;
	private static final int POS_COLLECTION = 1;
	private static final int POS_CONFIG = 2;

	private List<SecondaryMenuItem> menuList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	private void init() {
		context = getSherlockActivity();
		String[] menuItemTitles = getResources().getStringArray(
				R.array.secondary_menu_title);
		TypedArray iconArray = getResources().obtainTypedArray(
				R.array.secondary_menu_icon);
		menuList = new ArrayList<SecondaryMenuItem>();
		for (int i = 0; i < iconArray.length(); i++) {
			menuList.add(new SecondaryMenuItem(iconArray.getResourceId(i, 0),
					menuItemTitles[i]));
		}
		iconArray.recycle();
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View convertView = inflater.inflate(R.layout.secondary_menu, null);

		initBarCodeImageView(convertView);
		initGridMenu(convertView);
		return convertView;
	}

	private void initBarCodeImageView(View convertView) {
		ImageView barCode = (ImageView) convertView.findViewById(R.id.bar_code);
		LoaderUtil.displayImage(context, URLStrings.GET_BAR_CODE, barCode);
	}

	private void initGridMenu(View convertView) {
		ArrayAdapter<SecondaryMenuItem> adapter = new RightMenuAdapter(context,
				menuList);
		gridview = (GridView) convertView.findViewById(R.id.gridview);
		gridview.setAdapter(adapter);
		gridview.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
		Class<?> cls = null;
		switch (position) {
		case POS_SEARCH:
			cls = SearchActivity.class;
			break;
		case POS_COLLECTION:
			cls = CollectionsActivity.class;
			break;
		case POS_CONFIG:
			cls = SettingActivity.class;
			break;
		default:
			break;
		}
		if (cls != null) {
			startActivity(new Intent(context, cls));
		}
	}
}
