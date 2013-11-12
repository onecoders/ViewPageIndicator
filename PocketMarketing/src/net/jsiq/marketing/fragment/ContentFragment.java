package net.jsiq.marketing.fragment;

import java.util.ArrayList;
import java.util.List;

import net.jsiq.marketing.R;
import net.jsiq.marketing.adapter.ContentAdapter;
import net.jsiq.marketing.constants.URLStrings;
import net.jsiq.marketing.model.ContentItem;
import net.jsiq.marketing.util.LoaderUtil;
import net.jsiq.marketing.util.MessageToast;
import net.jsiq.marketing.util.NetworkUtils;
import net.jsiq.marketing.view.ViewFlowHeaderView;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;

public class ContentFragment extends SherlockFragment {

	public static final String CATALOG_ID = "catalog_id";

	private Context context;

	private int catalogId;
	private List<ContentItem> contentList;

	private ListView listview;
	private ContentAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.context = getSherlockActivity();
		this.contentList = new ArrayList<ContentItem>();
		// get catalogId
		catalogId = getArguments().getInt(CATALOG_ID);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View convertView = inflater.inflate(R.layout.headerview_list, null);
		//
		listview = (ListView) convertView.findViewById(R.id.content_list);

		List<String> urls = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			urls.add("http://www.jsiq.net:6060/resource/menuIco/20131022164521.jpg");
		}
		// add header view
		View headerView = new ViewFlowHeaderView(context, urls);
		listview.addHeaderView(headerView);
		// init adapter
		adapter = new ContentAdapter(context, contentList);
		listview.setAdapter(adapter);
		return convertView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (NetworkUtils.isNetworkConnected(context)) {
			String loadContentUrl = URLStrings.GET_CONTENTS_BY_CATALOGID_PAGENO
					+ catalogId + "/1";
			new LoadContentTask().execute(loadContentUrl);
		} else {
			MessageToast.showText(context, R.string.notConnected);
		}
	}

	class LoadContentTask extends AsyncTask<String, Void, Void> {

		private ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(context);
			dialog.setMessage("内容导入中...");
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.show();
		}

		@Override
		protected Void doInBackground(String... params) {
			try {
				contentList = LoaderUtil.loadContentItems(params[0]);
			} catch (Exception e) {
				MessageToast.showText(context, R.string.loadFailed);
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			adapter.notifyDataSetChanged();
			dialog.dismiss();
		}

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

}
