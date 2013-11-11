package net.jsiq.marketing.adapter;

import java.util.List;

import net.jsiq.marketing.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ViewFlowImageAdapter extends ArrayAdapter<String> {

	private List<String> urls;

	public ViewFlowImageAdapter(Context context, List<String> urls) {
		super(context, 0, 0, urls);
		this.urls = urls;
	}

	@Override
	public int getCount() {
		return urls.size();
	}

	@Override
	public String getItem(int position) {
		return urls.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (null == convertView) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.viewflow_item, null);
		}
		
		return super.getView(position, convertView, parent);
	}

}
