package learn2crack.xmlparsing;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ScrollListViewTest extends Activity {

	private ListView listview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);

		listview = (ListView) findViewById(R.id.listview);

		String[] array = { "test1", "test2", "test3", "test4", "test5",
				"test6", "test7", "test8", "test9", "test10", "test11",
				"test12", "test13", "test14", "test15", "test16", "test17",
				"test18" };

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, array);

		listview.setAdapter(adapter);
		setListViewHeightBasedOnChildren(listview);
	}

	private void setListViewHeightBasedOnChildren(ListView lv) {
		ListAdapter listAdapter = lv.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, lv);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = lv.getLayoutParams();
		params.height = totalHeight
				+ (lv.getDividerHeight() * (listAdapter.getCount() - 1));
		lv.setLayoutParams(params);
	}
}
