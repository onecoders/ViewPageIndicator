package net.jsiq.marketing.adapter;

import java.util.List;

import net.jsiq.marketing.fragment.ContentFragment;
import net.jsiq.marketing.model.CatalogItem;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.actionbarsherlock.app.SherlockFragment;

public class CatalogAdapter extends FragmentPagerAdapter {

	private List<CatalogItem> catalogItems;

	public CatalogAdapter(FragmentManager fm, List<CatalogItem> catalogItems) {
		super(fm);
		this.catalogItems = catalogItems;
	}

	@Override
	public SherlockFragment getItem(int position) {
		Bundle extra = new Bundle();
		extra.putInt(ContentFragment.CATALOG_ID, catalogItems.get(position)
				.getCatalogId());
		ContentFragment fragment = new ContentFragment();
		fragment.setArguments(extra);
		return fragment;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return catalogItems.get(position).getCatalogName();
	}

	@Override
	public int getCount() {
		return catalogItems.size();
	}

}
