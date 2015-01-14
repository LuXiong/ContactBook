package com.ruanko.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;

import com.ruanko.contactbook.BaseActivity;
import com.ruanko.contactbook.R;

public class EditActivity extends BaseActivity {

	private ScrollView scrollView;
	private MenuItem item;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_editor);
		setOnRightBtnClickListener(new OnRightBtnClickListener() {

			@Override
			public void onClick(MenuItem item) {
				Intent intent = new Intent();
				intent.setClass(EditActivity.this, DetailActivity.class);
				startActivity(intent);
				EditActivity.this.finish();
			}
		});
		scrollView = (ScrollView) this.scrollView;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		setRightTitle("±£´æ");
		hideSearchBtn();
		return result;
	}

}
