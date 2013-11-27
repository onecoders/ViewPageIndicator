package net.jsiq.marketing.fragment;

import java.util.ArrayList;
import java.util.List;

import net.jsiq.marketing.R;
import net.jsiq.marketing.activity.MainActivity;
import net.jsiq.marketing.adapter.IndexViewFlowAdapter;
import net.jsiq.marketing.model.MenuItem;
import net.jsiq.marketing.util.LoaderUtil;
import net.jsiq.marketing.view.CircleFlowIndicator;
import net.jsiq.marketing.view.ViewFlow;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class IndexFragment extends SherlockFragment {

	private Context context;
	private MainActivity mainActivity;

	private ViewFlow viewFlow;
	private ImageView indexBottomLeft;
	private ImageView[] indexImageViews;
	private TextView[] indexTextViews;
	private CircleFlowIndicator indic;
	private int[][] resId = new int[][] {
			{ R.id.index_first_image, R.id.index_first_text },
			{ R.id.index_second_image, R.id.index_second_text },
			{ R.id.index_third_image, R.id.index_third_text },
			{ R.id.index_forth_image, R.id.index_forth_text },
			{ R.id.index_fifth_image, R.id.index_fifth_text } };

	private List<String> urls;
	private String bottomLeftUrl;
	private List<MenuItem> items;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof MainActivity) {
			mainActivity = (MainActivity) activity;
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mainActivity = null;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getSherlockActivity();
		urls = new ArrayList<String>();
		items = mainActivity.getMenuItems();
		indexImageViews = new ImageView[5];
		indexTextViews = new TextView[5];
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View convertView = inflater.inflate(R.layout.index, null);
		findViewsByConvertView(convertView);
		return convertView;
	}

	private void findViewsByConvertView(View convertView) {
		viewFlow = (ViewFlow) convertView.findViewById(R.id.index_viewflow);
		indexBottomLeft = (ImageView) convertView
				.findViewById(R.id.index_bottom_left);
		for (int i = 0; i < resId.length; i++) {
			indexImageViews[i] = (ImageView) convertView
					.findViewById(resId[i][0]);
			indexTextViews[i] = (TextView) convertView
					.findViewById(resId[i][1]);
		}
		indic = (CircleFlowIndicator) convertView
				.findViewById(R.id.viewflowindic);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		urls.add("http://f.hiphotos.baidu.com/image/w%3D1366%3Bcrop%3D0%2C0%2C1366%2C768/sign=7266c1c3abec8a13141a53e3c135aaec/aa64034f78f0f7368a89a92d0855b319ebc413a2.jpg");
		urls.add("http://d.hiphotos.baidu.com/image/w%3D1366%3Bcrop%3D0%2C0%2C1366%2C768/sign=83a450cbaa18972ba33a04c9d0fb40ea/6d81800a19d8bc3ee2876a15838ba61ea9d34565.jpg");
		urls.add("http://e.hiphotos.baidu.com/image/w%3D1366%3Bcrop%3D0%2C0%2C1366%2C768/sign=842e83c40df3d7ca0cf63b75c429856a/f9198618367adab456e1dfb98ad4b31c8701e413.jpg");
		bottomLeftUrl = "http://h.hiphotos.baidu.com/image/w%3D2048/sign=369a40aadc54564ee565e33987e69d82/738b4710b912c8fcbc008a74fd039245d7882193.jpg";
		initIndexViewFlow();
		initBottomLeft();
		initIndexFiveParts();
	}

	private void initIndexViewFlow() {
		viewFlow.setAdapter(new IndexViewFlowAdapter(context, urls));
		viewFlow.setmSideBuffer(urls.size());
		viewFlow.setFlowIndicator(indic);
		viewFlow.setTimeSpan(4500);
		viewFlow.setSelection(0);
	}

	private void initBottomLeft() {
		LoaderUtil.displayImage(context, bottomLeftUrl, indexBottomLeft);
	}

	private void initIndexFiveParts() {
		for (int i = 0; i < indexImageViews.length; i++) {
			final MenuItem item = items.get(i);
			if (item != null) {
				LoaderUtil.displayImage(context, item.getMenuIcon(),
						indexImageViews[i]);
				indexTextViews[i].setText(item.getMenuName());
				View parent = (View) indexImageViews[i].getParent();
				parent.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						mainActivity.switchCatalogByMenu(item);
					}
				});
			}
		}
	}
}
