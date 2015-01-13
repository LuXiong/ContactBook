package com.ruanko.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;

import com.ruanko.contactbook.BaseActivity;
import com.ruanko.contactbook.R;

public class DetailActivity extends BaseActivity {
	
	private ScrollView scrollView;

	@Override
	protected void onCreate(Bundle bundle) {
		
		super.onCreate(bundle);
		setContentView(R.layout.activity_details);
		findView();
		initView();
	}

	
	private void findView() {
		scrollView = (ScrollView)this.findViewById(R.id.ScrollView1);
		
	}

    private void initView() {
		
		initData();
		bindEvent();
	}


	private void bindEvent() {

		
	}


	private void initData() {
		setTitle("hello world");
	}

	public class LaunchScrollViewClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			
		}

	}
	
}
