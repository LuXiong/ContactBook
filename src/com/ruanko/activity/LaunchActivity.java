package com.ruanko.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.ruanko.contactbook.BaseActivity;
import com.ruanko.contactbook.R;

public class LaunchActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_launch);
		findView();
		initView();
	}

	private void findView() {
	}

	private void initView() {
		(new Handler()).postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
				startActivity(intent);
				LaunchActivity.this.finish();
			}
		}, 500);
		initData();

	}

	private void initData() {

	}

	public class LaunchTextViewClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {

		}

	}
}
