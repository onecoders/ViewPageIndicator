package net.jsiq.marketing.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockListActivity;
import net.jsiq.marketing.R;
import net.jsiq.marketing.util.ViewHelper;

public class CollectionsActivity extends SherlockListActivity implements
		OnClickListener {

	private List<String> collections = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collection_listview);
		initActinBar();
		// simulation load collections from database
		for (int i = 0; i < 10; i++) {
			collections.add("收藏" + i);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				collections);
		setListAdapter(adapter);
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

			}
		});
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

}
