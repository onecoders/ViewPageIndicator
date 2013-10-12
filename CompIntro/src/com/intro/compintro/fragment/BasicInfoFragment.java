package com.intro.compintro.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.intro.compintro.view.BasicInfoView;

public class BasicInfoFragment extends SherlockFragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return new BasicInfoView(getSherlockActivity(), this);
	}

}
