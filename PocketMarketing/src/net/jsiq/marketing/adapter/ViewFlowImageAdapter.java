package net.jsiq.marketing.adapter;

import java.util.List;

import net.jsiq.marketing.R;
import net.jsiq.marketing.activity.ContentDisplayActivity;
import net.jsiq.marketing.model.ContentItem;
import net.jsiq.marketing.util.LoaderUtil;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class ViewFlowImageAdapter extends ArrayAdapter<ContentItem> {

	private Context context;

	public ViewFlowImageAdapter(Context context, List<ContentItem> items) {
		super(context, 0, items);
		this.context = context;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ContentItem item = getItem(position);
		if (null == convertView) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.viewflow_item, null);
		}
		ImageView viewFlow = ((ImageView) convertView
				.findViewById(R.id.imgView));
		LoaderUtil.displayImage(context, item.getContentTopPic(), viewFlow);
		viewFlow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startContentDisplayActivityWithContentId(item);
			}
		});
		return convertView;
	}

	private void startContentDisplayActivityWithContentId(ContentItem item) {
		Intent i = new Intent("android.intent.action.ContentDisplayActivity");
		i.putExtra(ContentDisplayActivity.CONTENT_INFO,
				new String[] { item.getContentId() + "",
						item.getContentTitle(), item.getContentSummary() });
		context.startActivity(i);
	}

}
