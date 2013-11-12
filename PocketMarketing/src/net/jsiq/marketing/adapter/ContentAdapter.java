package net.jsiq.marketing.adapter;

import java.util.List;

import net.jsiq.marketing.R;
import net.jsiq.marketing.model.ContentItem;
import net.jsiq.marketing.util.LoaderUtil;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContentAdapter extends ArrayAdapter<ContentItem> {

	private List<ContentItem> contentItems;

	public ContentAdapter(Context context, List<ContentItem> contentItems) {
		super(context, 0);
		this.contentItems = contentItems;
	}

	@Override
	public int getCount() {
		return contentItems.size();
	}

	@Override
	public ContentItem getItem(int position) {
		return contentItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ContentItem item = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.row, null);
		}
		ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
		LoaderUtil.displayImage(getContext(), item.getContentListPic(), icon);
		TextView title = (TextView) convertView.findViewById(R.id.row_title);
		title.setText(item.getContentTitle());
		TextView content = (TextView) convertView
				.findViewById(R.id.row_content);
		content.setText(item.getContentSummary());
		return convertView;
	}

}
