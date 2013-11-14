package net.jsiq.marketing.view;

import java.util.List;

import net.jsiq.marketing.R;
import net.jsiq.marketing.adapter.ViewFlowImageAdapter;
import android.content.Context;
import android.widget.LinearLayout;

public class ViewFlowHeaderView extends LinearLayout {

	private Context context;

	public ViewFlowHeaderView(Context context) {
		super(context);
		this.context = context;
	}

	public ViewFlowHeaderView(Context context, List<String> urls) {
		this(context);
		inflate(context, R.layout.viewflow_display, this);
		initViewFlowImage(urls);
	}

	private void initViewFlowImage(List<String> urls) {
		ViewFlow viewFlow = (ViewFlow) findViewById(R.id.viewflow);
		viewFlow.setAdapter(new ViewFlowImageAdapter(context, urls));
		viewFlow.setmSideBuffer(urls.size());
		CircleFlowIndicator indic = (CircleFlowIndicator) findViewById(R.id.viewflowindic);
		viewFlow.setFlowIndicator(indic);
		viewFlow.setTimeSpan(4500);
		viewFlow.setSelection(0);
	}

}
