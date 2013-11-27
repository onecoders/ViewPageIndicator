package net.jsiq.marketing.adapter;

import java.util.List;

import net.jsiq.marketing.R;
import net.jsiq.marketing.util.LoaderUtil;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class IndexViewFlowAdapter extends ArrayAdapter<String> {

	private Context context;

	public IndexViewFlowAdapter(Context context, List<String> urls) {
		super(context, 0, urls);
		this.context = context;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final String url = getItem(position);
		if (null == convertView) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.index_viewflow_item, null);
		}
		ImageView viewFlow = ((ImageView) convertView
				.findViewById(R.id.index_imgView));
		LoaderUtil.displayImage(context, url, viewFlow);
		return convertView;
	}

}
