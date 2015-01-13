package com.ruanko.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ScrollView;

import com.ruanko.contactbook.BaseActivity;
import com.ruanko.contactbook.R;

public class EditActivity extends BaseActivity {
	
	private ScrollView scrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editor);
		scrollView = (ScrollView)this.scrollView;
	}
	
	
}
