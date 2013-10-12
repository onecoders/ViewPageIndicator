package com.intro.compintro.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.intro.compintro.R;

public class ItemFragment extends SherlockFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View contextView = inflater.inflate(R.layout.fragment_item, container,
				false);
		TextView mTextView = (TextView) contextView.findViewById(R.id.textview);

		Bundle mBundle = getArguments();
		String title = mBundle.getString("arg");

		mTextView.setText(title);

		return contextView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

}