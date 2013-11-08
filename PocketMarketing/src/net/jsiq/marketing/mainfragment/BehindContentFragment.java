package net.jsiq.marketing.mainfragment;

import java.util.List;

import net.jsiq.marketing.R;
import net.jsiq.marketing.activity.MainActivity;
import net.jsiq.marketing.adapter.BehindMenuAdapter;
import net.jsiq.marketing.model.MenuItem;
import net.jsiq.marketing.util.JSONParser;
import net.jsiq.marketing.util.JsonHttpUtils;
import net.jsiq.marketing.util.URLStrings;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockListFragment;

public class BehindContentFragment extends SherlockListFragment {

	private BehindMenuAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.behind_menu, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		new InitMenuAsyncTask().execute(URLStrings.GET_MENUS);
	}

	class InitMenuAsyncTask extends AsyncTask<String, Void, List<MenuItem>> {

		@Override
		protected List<MenuItem> doInBackground(String... params) {
			return getMenuItems(params[0]);
		}

		@Override
		protected void onPostExecute(List<MenuItem> result) {
			super.onPostExecute(result);
			adapter = new BehindMenuAdapter(getSherlockActivity(), result);
			setListAdapter(adapter);
		}

	}

	private List<MenuItem> getMenuItems(String url) {
		try {
			String json = JsonHttpUtils.getRequest(url);
			return JSONParser.JSON2MenuItems(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
