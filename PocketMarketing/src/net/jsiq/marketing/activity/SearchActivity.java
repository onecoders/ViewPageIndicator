package net.jsiq.marketing.activity;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import net.jsiq.marketing.R;
import net.jsiq.marketing.adapter.ContentAdapter;
import net.jsiq.marketing.constants.URLStrings;
import net.jsiq.marketing.model.ContentItem;
import net.jsiq.marketing.util.LoaderUtil;
import net.jsiq.marketing.util.MessageToast;
import net.jsiq.marketing.util.NetworkUtils;
import net.jsiq.marketing.util.ViewHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

public class SearchActivity extends RightMenuBaseActivity implements
		OnQueryTextListener, OnItemClickListener {

	private SearchView searchView;
	private ListView resultListView;
	private ContentAdapter adapter;
	private List<ContentItem> contentList;
	private View loadingHint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_page);
		init();
	}

	private void init() {
		findViews();
		setListeners();
		initResultListView();
	}

	private void findViews() {
		searchView = (SearchView) findViewById(R.id.searchview);
		resultListView = (ListView) findViewById(android.R.id.list);
		loadingHint = findViewById(R.id.loadingHint);
	}

	private void setListeners() {
		searchView.setOnQueryTextListener(this);
		searchView.setSubmitButtonEnabled(true);
		resultListView.setOnItemClickListener(this);
	}

	private void initResultListView() {
		contentList = new ArrayList<ContentItem>();
		resultListView.setEmptyView(findViewById(android.R.id.empty));
		adapter = new ContentAdapter(this, contentList);
		resultListView.setAdapter(adapter);
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		if (TextUtils.isEmpty(newText)) {
			MessageToast.showText(this, R.string.invalid);
		}
		return true;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		if (query != null && !query.trim().equals("")) {
			queryByKeywords(query);
		} else {
			MessageToast.showText(this, R.string.invalid);
		}
		return false;
	}

	private void queryByKeywords(String query) {
		String searchUrl = URLStrings.GET_SEARCH_BY_SEARCH_KEY
				+ URLEncoder.encode(query);
		resultListView.setVisibility(View.GONE);
		if (NetworkUtils.isNetworkConnected(this)) {
			loadingHint.setVisibility(View.VISIBLE);
			new SearchContentTask().execute(searchUrl);
		} else {
			MessageToast.showText(this, R.string.notConnected);
		}
	}

	class SearchContentTask extends AsyncTask<String, Void, List<ContentItem>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			contentList.clear();
		}

		@Override
		protected List<ContentItem> doInBackground(String... params) {
			try {
				return LoaderUtil.loadContentItems(params[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(List<ContentItem> result) {
			super.onPostExecute(result);
			if (result == null) {
				MessageToast.showText(SearchActivity.this, R.string.loadFailed);
			} else if (result.size() == 0) {
				MessageToast.showText(SearchActivity.this,
						R.string.searchNoResults);
			} else {
				contentList.addAll(result);
				adapter.notifyDataSetChanged();
				resultListView.setVisibility(View.VISIBLE);
			}
			loadingHint.setVisibility(View.GONE);
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		ViewHelper.startContentDisplayActivityByContent(this,
				contentList.get(arg2));
	}

	@Override
	protected void setTitle(TextView title) {
		title.setText(R.string.search);
	}

}
