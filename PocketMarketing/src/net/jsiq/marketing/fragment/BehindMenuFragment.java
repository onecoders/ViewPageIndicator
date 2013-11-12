package net.jsiq.marketing.fragment;

import java.util.ArrayList;
import java.util.List;

import net.jsiq.marketing.R;
import net.jsiq.marketing.activity.MainActivity;
import net.jsiq.marketing.adapter.BehindMenuAdapter;
import net.jsiq.marketing.constants.URLStrings;
import net.jsiq.marketing.model.MenuItem;
import net.jsiq.marketing.util.LoaderUtil;
import net.jsiq.marketing.util.MessageToast;
import net.jsiq.marketing.util.NetworkUtils;
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
import com.actionbarsherlock.app.SherlockListFragment;

public class BehindMenuFragment extends SherlockListFragment {

	private Context context;

	private BehindMenuAdapter adapter;
	private List<MenuItem> menuList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.context = getSherlockActivity();
		View behindContentView = inflater.inflate(R.layout.behind_menu, null);
		return behindContentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (NetworkUtils.isNetworkConnected(context)) {
			new LoadMenuTask().execute(URLStrings.GET_MENUS);
		} else {
			MessageToast.showText(context, R.string.notConnected);
		}
	}

	class LoadMenuTask extends AsyncTask<String, Void, Void> {

		private ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			menuList = new ArrayList<MenuItem>();
			dialog = new ProgressDialog(context);
			dialog.setMessage("内容导入中...");
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.show();
		}

		@Override
		protected Void doInBackground(String... params) {
			try {
				menuList = LoaderUtil.loadMenuItems(params[0]);
			} catch (Exception e) {
				MessageToast.showText(context, R.string.loadFailed);
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			adapter = new BehindMenuAdapter(context, menuList);
			setListAdapter(adapter);
			((MainActivity) getSherlockActivity()).setFirstFragment(menuList.get(
					0));
			dialog.dismiss();
		}

	}

	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {
		MenuItem selectedItem = menuList.get(position);
		SherlockFragment newContent = getNewCatalogFragmentByMenu(selectedItem);
		if (newContent != null)
			switchFragment(newContent);
		adapter.setSelectItem(position);
	}

	private SherlockFragment getNewCatalogFragmentByMenu(MenuItem item) {
		Bundle extra = new Bundle();
		extra.putInt(CatalogFragment.MENU_ID, item.getMenuId());
		extra.putString(CatalogFragment.CATALOG_TITLE, item.getMenuName());
		CatalogFragment fragment = new CatalogFragment();
		fragment.setArguments(extra);
		return fragment;
	}

	// the meat of switching the above fragment
	private void switchFragment(SherlockFragment fragment) {
		if (getSherlockActivity() == null)
			return;
		if (getSherlockActivity() instanceof MainActivity) {
			MainActivity fca = (MainActivity) getSherlockActivity();
			fca.switchContent(fragment);
		}
	}

	public BehindMenuAdapter getAdapter() {
		return adapter;
	}

}
