package net.jsiq.marketing.fragment;

import java.util.ArrayList;
import java.util.List;

import net.jsiq.marketing.R;
import net.jsiq.marketing.constants.URLStrings;
import net.jsiq.marketing.model.CatalogItem;
import net.jsiq.marketing.util.LoaderUtil;
import net.jsiq.marketing.util.MessageToast;
import net.jsiq.marketing.util.NetworkUtils;
import net.jsiq.marketing.util.ViewHelper;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.viewpagerindicator.TabPageIndicator;

public class CatalogFragment extends SherlockFragment {

	public static final String MENU_ID = "menu_id";
	public static final String CATALOG_TITLE = "catalog_title";
	private static final String BASIC_INFO_POS = "basic_info_pos";

	private Context context;

	private FragmentPagerAdapter adapter;
	private ViewPager pager;
	private TabPageIndicator indicator;

	private int currentPos = 0;
	private List<CatalogItem> catalogList;
	private int menuId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		catalogList = new ArrayList<CatalogItem>();
		this.context = getSherlockActivity();
		menuId = getArguments().getInt(MENU_ID);
		ActionBar actionBar = getSherlockActivity().getSupportActionBar();
		ViewHelper.setActionBarCustomerViewContent(actionBar,
				R.drawable.basic_info_back,
				getArguments().getString(CATALOG_TITLE));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View convertView = inflater.inflate(R.layout.simple_tabs, container,
				false);
		adapter = new CatalogAdapter(getChildFragmentManager());

		pager = (ViewPager) convertView.findViewById(R.id.pager);
		pager.setAdapter(adapter);

		indicator = (TabPageIndicator) convertView.findViewById(R.id.indicator);
		indicator.setViewPager(pager);
		if (savedInstanceState != null) {
			currentPos = savedInstanceState.getInt(BASIC_INFO_POS);
		}
		indicator.setCurrentItem(currentPos);
		return convertView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (NetworkUtils.isNetworkConnected(context)) {
			String getCatalogUrl = URLStrings.GET_CATALOGS_BY_MENUID + menuId;
			new LoadCatalogTask().execute(getCatalogUrl);
		} else {
			MessageToast.showText(context, R.string.notConnected);
		}
	}

	class LoadCatalogTask extends AsyncTask<String, Void, Void> {

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
				catalogList = LoaderUtil.loadCatalogItems(params[0]);
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
			indicator.notifyDataSetChanged();
			dialog.dismiss();
		}

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		currentPos = pager.getCurrentItem();
		outState.putInt(BASIC_INFO_POS, currentPos);
	}

	class CatalogAdapter extends FragmentPagerAdapter {

		public CatalogAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public SherlockFragment getItem(int position) {
			Bundle extra = new Bundle();
			extra.putInt(ContentFragment.CATALOG_ID, catalogList.get(position)
					.getCatalogId());
			ContentFragment fragment = new ContentFragment();
			fragment.setArguments(extra);
			return fragment;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return catalogList.get(position).getCatalogName();
		}

		@Override
		public int getCount() {
			return catalogList.size();
		}
	}

}
