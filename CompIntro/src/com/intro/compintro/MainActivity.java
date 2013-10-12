package com.intro.compintro;

import android.os.Bundle;

public class MainActivity extends BaseActivity {

	public MainActivity() {
		super(R.string.hello_world);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_frame);

		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, new SampleListFragment()).commit();
	}

}
