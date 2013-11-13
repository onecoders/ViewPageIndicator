package net.jsiq.marketing.adapter;

import java.util.List;

import net.jsiq.marketing.R;
import net.jsiq.marketing.model.MenuItem;
import net.jsiq.marketing.util.LoaderUtil;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LeftMenuAdapter extends ArrayAdapter<MenuItem> {

	private Context context;

	public LeftMenuAdapter(Context context, List<MenuItem> menuList) {
		super(context, 0, menuList);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MenuItem item = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.behind_menu_item, null);
		}
		LoaderUtil.displayImage(context, item.getMenuIcon(),
				((ImageView) convertView.findViewById(R.id.menu_icon)));
		((TextView) convertView.findViewById(R.id.main_title)).setText(item
				.getMenuName());
		return convertView;
	}

}
