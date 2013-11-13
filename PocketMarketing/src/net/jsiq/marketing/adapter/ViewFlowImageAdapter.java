package net.jsiq.marketing.adapter;

import java.util.List;

import net.jsiq.marketing.R;
import net.jsiq.marketing.util.LoaderUtil;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class ViewFlowImageAdapter extends ArrayAdapter<String> {

	private Context context;

	public ViewFlowImageAdapter(Context context, List<String> uris) {
		super(context, 0, uris);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (null == convertView) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.viewflow_item, null);
		}
		ImageView viewFlow = ((ImageView) convertView
				.findViewById(R.id.imgView));
		LoaderUtil.displayImage(context, getItem(position), viewFlow);
		viewFlow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		return convertView;
	}

}
