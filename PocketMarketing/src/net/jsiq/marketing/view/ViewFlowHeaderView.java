package net.jsiq.marketing.view;

import java.util.List;

import net.jsiq.marketing.R;
import net.jsiq.marketing.adapter.ViewFlowImageAdapter;
import net.jsiq.marketing.model.ContentItem;
import android.content.Context;
import android.widget.LinearLayout;

public class ViewFlowHeaderView extends LinearLayout {

	private Context context;

	public ViewFlowHeaderView(Context context) {
		super(context);
		this.context = context;
	}

	public ViewFlowHeaderView(Context context, List<ContentItem> items) {
		this(context);
		inflate(context, R.layout.viewflow_display, this);
		initViewFlowImage(items);
	}

	private void initViewFlowImage(List<ContentItem> items) {
		ViewFlow viewFlow = (ViewFlow) findViewById(R.id.viewflow);
		viewFlow.setAdapter(new ViewFlowImageAdapter(context, items));
		viewFlow.setmSideBuffer(items.size());
		CircleFlowIndicator indic = (CircleFlowIndicator) findViewById(R.id.viewflowindic);
		viewFlow.setFlowIndicator(indic);
		viewFlow.setTimeSpan(4500);
		viewFlow.setSelection(0);
	}

}
