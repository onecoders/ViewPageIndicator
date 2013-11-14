package net.jsiq.marketing.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.jsiq.marketing.R;
import net.jsiq.marketing.db.ContactDBHelper;
import net.jsiq.marketing.model.ContactItem;
import net.jsiq.marketing.util.ViewHelper;
import net.jsiq.marketing.view.AlphaView;
import net.jsiq.marketing.view.AlphaView.OnAlphaChangedListener;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;

public class ContactsFragment extends SherlockFragment implements
		OnAlphaChangedListener {

	private ListView listView;
	private AlphaView alphaView;
	private TextView overlay;

	private WindowManager windowManager;
	private List<ContactItem> list;
	private ListAdapter adapter;
	private HashMap<String, Integer> alphaIndexer;
	private OverlayThread overlayThread;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getSherlockActivity().getSupportActionBar();
		ViewHelper.setActionBarCustomerViewContent(actionBar, "contact");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View convertView = inflater.inflate(R.layout.contacts_main, container,
				false);
		list = new ArrayList<ContactItem>();
		alphaIndexer = new HashMap<String, Integer>();
		overlayThread = new OverlayThread();
		listView = (ListView) convertView.findViewById(R.id.list_view);
		alphaView = (AlphaView) convertView.findViewById(R.id.alphaView);
		alphaView.setOnAlphaChangedListener(this);
		initOverlay();

		return convertView;
	}

	@Override
	public void onResume() {
		super.onResume();
		startQuery();
	}

	@Override
	public void onStop() {
		try {
			windowManager.removeViewImmediate(overlay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onStop();
	}

	private void initOverlay() {
		LayoutInflater inflater = LayoutInflater.from(getSherlockActivity());
		overlay = (TextView) inflater.inflate(R.layout.overlay, null);
		overlay.setVisibility(View.INVISIBLE);
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_APPLICATION,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
						| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
				PixelFormat.TRANSLUCENT);
		windowManager = (WindowManager) getSherlockActivity().getSystemService(
				Context.WINDOW_SERVICE);
		windowManager.addView(overlay, lp);
	}

	private void startQuery() {
		new ContactQuery().execute();
	}

	private class ContactQuery extends AsyncTask<Void, Void, Void> {

		List<ContactItem> items;
		ContactDBHelper DBHelper;

		public ContactQuery() {
			DBHelper = new ContactDBHelper(getSherlockActivity());
			DBHelper.open();
		}

		@Override
		protected Void doInBackground(Void... params) {
			items = DBHelper.queryAll();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			list.clear();
			if (items.size() > 0) {
				list.addAll(items);
			}
			if (list.size() > 0) {
				setAdapter();
			}
			DBHelper.close();
		}

	}

	private void setAdapter() {
		if (adapter == null) {
			adapter = new ListAdapter();
			listView.setAdapter(adapter);
		} else {
			adapter.notifyDataSetChanged();
		}
	}

	private class ListAdapter extends BaseAdapter {
		private LayoutInflater inflater;

		public ListAdapter() {
			this.inflater = LayoutInflater.from(getSherlockActivity());
			for (int i = 0; i < list.size(); i++) {
				String currentAlpha = list.get(i).getAlpha();
				String previewAlpha = (i - 1) >= 0 ? list.get(i - 1).getAlpha()
						: " ";
				if (!previewAlpha.equals(currentAlpha)) {
					String alpha = list.get(i).getAlpha();
					alphaIndexer.put(alpha, i);
				}
			}
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.list_item, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			ContactItem item = list.get(position);
			holder.name.setText(item.getName());

			String currentAlpha = list.get(position).getAlpha();
			String previewAlpha = (position - 1) >= 0 ? list.get(position - 1)
					.getAlpha() : " ";
			if (!previewAlpha.equals(currentAlpha)) {
				holder.alpha.setVisibility(View.VISIBLE);
				holder.alpha.setText(currentAlpha);
			} else {
				holder.alpha.setVisibility(View.GONE);
			}
			return convertView;
		}

	}

	private final class ViewHolder {
		TextView alpha;
		TextView name;

		public ViewHolder(View v) {
			alpha = (TextView) v.findViewById(R.id.alpha_text);
			name = (TextView) v.findViewById(R.id.name);
		}
	}

	private Handler handler = new Handler();

	private class OverlayThread implements Runnable {

		@Override
		public void run() {
			overlay.setVisibility(View.GONE);
		}

	}

	@Override
	public void OnAlphaChanged(String s, int index) {
		if (s != null && s.trim().length() > 0) {
			overlay.setText(s);
			overlay.setVisibility(View.VISIBLE);
			handler.removeCallbacks(overlayThread);
			handler.postDelayed(overlayThread, 700);
			if (alphaIndexer.get(s) != null) {
				int position = alphaIndexer.get(s);
				listView.setSelection(position);
			}
		}
	}

}
