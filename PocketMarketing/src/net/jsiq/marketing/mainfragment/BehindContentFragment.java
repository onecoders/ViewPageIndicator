package net.jsiq.marketing.mainfragment;

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
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockListFragment;

public class BehindContentFragment extends SherlockListFragment {
	
	private BehindMenuAdapter adapter;
	private List<MenuItem> menuList;
	private Context context;

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
			new InitMenuAsyncTask().execute(URLStrings.GET_MENUS);
		} else {
			MessageToast.makeText(context, R.string.notConnected,
					Toast.LENGTH_SHORT).show();
		}
	}

	class InitMenuAsyncTask extends AsyncTask<String, Void, Void> {

		private ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			menuList = new ArrayList<MenuItem>();
			dialog = new ProgressDialog(context);
			dialog.setMessage("内容导入中...");
			dialog.show();
		}

		@Override
		protected Void doInBackground(String... params) {
			menuList = LoaderUtil.loadMenuItems(params[0]);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			adapter = new BehindMenuAdapter(context, menuList);
			setListAdapter(adapter);
			dialog.dismiss();
		}

	}

	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {
		SherlockFragment newContent = null;
		switch (position) {
		case 0:
			newContent = new BasicInfoFragment();
			break;
		case 1:
			newContent = new MarketingFragment();
			break;
		case 2:
			newContent = new MainProductFragment();
			break;
		case 3:
			newContent = new ContactsFragment();
			break;
		}
		if (newContent != null)
			switchFragment(newContent);
		adapter.setSelectItem(position);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	// the meat of switching the above fragment
	private void switchFragment(SherlockFragment fragment) {
		if (getSherlockActivity() == null)
			return;

		if (getSherlockActivity() instanceof MainActivity) {
			MainActivity fca = (MainActivity) getActivity();
			fca.switchContent(fragment);
		}
	}

	public BehindMenuAdapter getAdapter() {
		return adapter;
	}

}
