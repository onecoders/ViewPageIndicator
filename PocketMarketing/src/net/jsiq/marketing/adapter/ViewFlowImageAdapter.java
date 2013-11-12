package net.jsiq.marketing.adapter;

import java.util.List;

import net.jsiq.marketing.R;
import net.jsiq.marketing.util.ImageLoaderUtil;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class ViewFlowImageAdapter extends ArrayAdapter<String> {

	private Context context;
	private List<String> uris;

	public ViewFlowImageAdapter(Context context, List<String> uris) {
		super(context, 0, uris);
		this.context = context;
		this.uris = uris;
	}

	@Override
	public int getCount() {
		return uris.size();
	}

	@Override
	public String getItem(int position) {
		return uris.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (null == convertView) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.viewflow_item, null);
		}
		ImageView viewFlow = ((ImageView) convertView
				.findViewById(R.id.imgView));
		ImageLoaderUtil.displayImage(context, getItem(position), viewFlow);
		viewFlow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		return convertView;
	}

}
