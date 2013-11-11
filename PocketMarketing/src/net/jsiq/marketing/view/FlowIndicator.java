package net.jsiq.marketing.view;

import net.jsiq.marketing.view.ViewFlow.ViewSwitchListener;

public interface FlowIndicator extends ViewSwitchListener {

	public void setViewFlow(ViewFlow view);

	public void onScrolled(int h, int v, int oldh, int oldv);
}
