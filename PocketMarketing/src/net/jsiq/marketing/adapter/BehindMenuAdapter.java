package net.jsiq.marketing.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.jsiq.marketing.R;
import net.jsiq.marketing.model.MenuItem;

public class BehindMenuAdapter extends ArrayAdapter<MenuItem> {

	private List<MenuItem> menuList;
	private int selectItem = 0;

	public BehindMenuAdapter(Context context, List<MenuItem> menuList) {
		super(context, 0, menuList);
		this.menuList = menuList;
	}

	@Override
	public MenuItem getItem(int position) {
		return menuList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getCount() {
		return menuList.size();
	}

	public void setSelectItem(int selectItem) {
		this.selectItem = selectItem;
		notifyDataSetInvalidated();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MenuItem item = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.behind_menu_item, null);
		}
		((ImageView) convertView.findViewById(R.id.menu_icon))
				.setImageResource(item.getIconId());
		((TextView) convertView.findViewById(R.id.main_title)).setText(item
				.getTitleZh());
		((TextView) convertView.findViewById(R.id.second_title)).setText(item
				.getTitleEn());
		if (position == selectItem) {
			convertView.setBackgroundResource(R.color.pressed_w);
		} else {
			convertView.setBackgroundResource(android.R.color.transparent);
		}
		return convertView;
	}

}
