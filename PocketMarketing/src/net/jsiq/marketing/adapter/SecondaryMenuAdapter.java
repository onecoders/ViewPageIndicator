package net.jsiq.marketing.adapter;

import java.util.List;

import net.jsiq.marketing.R;
import net.jsiq.marketing.model.SecondaryMenuItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondaryMenuAdapter extends ArrayAdapter<SecondaryMenuItem> {

	private Context context;
	private List<SecondaryMenuItem> menuList;

	public SecondaryMenuAdapter(Context context,
			List<SecondaryMenuItem> menuList) {
		super(context, 0, menuList);
		this.context = context;
		this.menuList = menuList;
	}

	@Override
	public SecondaryMenuItem getItem(int position) {
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SecondaryMenuItem item = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.secondary_menu_item, null);
		}
		((ImageView) convertView.findViewById(R.id.gridview_icon))
				.setImageResource(item.getIconId());
		((TextView) convertView.findViewById(R.id.gridview_title)).setText(item
				.getTitleId());
		return convertView;
	}

}
