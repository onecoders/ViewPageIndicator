package net.jsiq.marketing.adapter;

import java.util.List;

import net.jsiq.marketing.R;
import net.jsiq.marketing.model.CollectionItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CollectionAdapter extends ArrayAdapter<CollectionItem> {

	public CollectionAdapter(Context context, List<CollectionItem> objects) {
		super(context, 0, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CollectionItem collection = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.collection_item, null);
		}
		TextView title = (TextView) convertView.findViewById(R.id.title);
		title.setText(collection.getContentTitle());
		TextView summary = (TextView) convertView.findViewById(R.id.summary);
		summary.setText(collection.getContentSummary());
		return convertView;
	}

}
