package com.ruanko.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.ruanko.contactbook.BaseActivity;
import com.ruanko.contactbook.R;

public class LaunchActivity extends BaseActivity {
	

	private TextView launchTextView;
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_launch);
		findView();
		initView();
	}

	private void findView() {
		launchTextView = (TextView) findViewById(R.id.activity_launch_text);
	}

	private void initView() {
		initData();
		bindEvent();
	}

	private void initData() {
		launchTextView.setText("hello world");
		
	}

	private void bindEvent() {
		launchTextView.setOnClickListener(new LaunchTextViewClickListener());
	}
	public class LaunchTextViewClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			
		}

	}
}
