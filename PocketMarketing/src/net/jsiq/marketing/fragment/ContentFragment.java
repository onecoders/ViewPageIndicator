package net.jsiq.marketing.fragment;

import java.util.ArrayList;
import java.util.List;

import net.jsiq.marketing.R;
import net.jsiq.marketing.activity.ContentDisplayActivity;
import net.jsiq.marketing.adapter.ContentAdapter;
import net.jsiq.marketing.constants.URLStrings;
import net.jsiq.marketing.model.ContentItem;
import net.jsiq.marketing.util.LoaderUtil;
import net.jsiq.marketing.util.MessageToast;
import net.jsiq.marketing.util.NetworkUtils;
import net.jsiq.marketing.view.ViewFlowHeaderView;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;

public class ContentFragment extends SherlockFragment implements
		OnItemClickListener {

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
		listview = (ListView) convertView.findViewById(R.id.content_list);
		// top show images urls
		List<String> urls = new ArrayList<String>();
		urls.add("http://www.chinaunicom.com.cn/images/wpBananer.jpg");
		urls.add("http://www.chinaunicom.com.cn/images/wjtBanner.jpg");
		urls.add("http://www.chinaunicom.com.cn/images/wswBanner.jpg");
		// add header view
		View headerView = new ViewFlowHeaderView(context, urls);
		listview.addHeaderView(headerView);

		// TODO list addAll()
		adapter = new ContentAdapter(context, contentList);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);
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

	class LoadContentTask extends AsyncTask<String, Void, List<ContentItem>> {

		private List<ContentItem> contentItems;
		private ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			adapter.clear();
			dialog = new ProgressDialog(context);
			dialog.setMessage("内容导入中...");
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.show();
		}

		@Override
		protected List<ContentItem> doInBackground(String... params) {
			try {
				contentItems = LoaderUtil.loadContentItems(params[0]);
			} catch (Exception e) {
				MessageToast.showText(context, R.string.loadFailed);
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(List<ContentItem> result) {
			super.onPostExecute(result);
			adapter.addAll(contentItems);
			dialog.dismiss();
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long id) {
		int contentId = contentList.get(position - 1).getContentId();
		startContentDisplayActivityWithContentId(contentId);
	}

	private void startContentDisplayActivityWithContentId(int contentId) {
		Intent i = new Intent("android.intent.action.ContentDisplayActivity");
		i.putExtra(ContentDisplayActivity.CONTENT_ID, contentId);
		startActivity(i);
	}

}
