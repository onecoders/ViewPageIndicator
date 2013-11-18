package net.jsiq.marketing.activity;

import java.util.List;

import net.jsiq.marketing.R;
import net.jsiq.marketing.adapter.CollectionAdapter;
import net.jsiq.marketing.db.CollectionDBHelper;
import net.jsiq.marketing.model.ContentCollection;
import net.jsiq.marketing.util.MessageToast;
import net.jsiq.marketing.util.ViewHelper;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockListActivity;

public class CollectionsActivity extends SherlockListActivity implements
		OnClickListener, OnItemClickListener {

	private List<ContentCollection> collections;
	private CollectionDBHelper DBHelper;
	private CollectionAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collection_listview);
		initActinBar();
		DBHelper = new CollectionDBHelper(this);
		DBHelper.open();
		collections = DBHelper.queryAll();
		adapter = new CollectionAdapter(this, collections);
		setListAdapter(adapter);
		getListView().setOnItemClickListener(this);
		registerForContextMenu(getListView());
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		menu.setHeaderTitle(R.string.options);
		inflater.inflate(R.menu.context_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		boolean success = false;
		switch (item.getItemId()) {
		case R.id.delete:
			int pos = (int) getListAdapter().getItemId(menuInfo.position);
			success = DBHelper.delete(collections.get(pos).get_id());
			collections.remove(pos);
			adapter.notifyDataSetChanged();
			break;
		case R.id.deleteAll:
			success = DBHelper.deleteAll();
			collections.clear();
			adapter.notifyDataSetChanged();
			break;
		default:
			break;
		}
		if (success) {
			MessageToast.showText(this, R.string.operatSucceed);
		} else {
			MessageToast.showText(this, R.string.operatFailed);
		}
		return true;
	}

	private void initActinBar() {
		ActionBar actionBar = getSupportActionBar();
		// Get custom view
		View customerView = loadCustomerView();
		// Now set custom view
		ViewHelper.initActionBarAndSetCustomerView(actionBar, customerView);
	}

	private View loadCustomerView() {
		View actionbarView = LayoutInflater.from(this).inflate(
				R.layout.actionbar_configure, null);
		ImageButton menuBtn = (ImageButton) actionbarView
				.findViewById(R.id.back_btn);
		menuBtn.setOnClickListener(this);
		TextView title = (TextView) actionbarView.findViewById(R.id.title);
		title.setText(R.string.collections);
		return actionbarView;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.back_btn) {
			onBackPressed();
		}
	}

	private void startContentDisplayActivityWithContentId(
			ContentCollection collection) {
		Intent i = new Intent("android.intent.action.ContentDisplayActivity");
		i.putExtra(ContentDisplayActivity.CONTENT_INFO, new String[] {
				collection.getContentId() + "", collection.getContentTitle(),
				collection.getContentSummary() });
		startActivity(i);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		DBHelper.close();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		startContentDisplayActivityWithContentId(collections.get(arg2));
	}

}
