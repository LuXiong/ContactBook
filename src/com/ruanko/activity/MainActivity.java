package com.ruanko.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.ruanko.contactbook.BaseActivity;
import com.ruanko.contactbook.R;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.v("wq","hi contactbook");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
