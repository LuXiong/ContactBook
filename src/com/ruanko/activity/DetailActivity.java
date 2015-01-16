package com.ruanko.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ruanko.contactbook.BaseActivity;
import com.ruanko.contactbook.R;
import com.ruanko.model.Contact;

public class DetailActivity extends BaseActivity {

	final static public String EXTRA_INPUT = "contact";

	private Contact mContact;

	private ScrollView mScrollView;
	private ImageButton mCallBtn, mMessageBtn, mDelBtn;
	private TextView mNameTextView, mPhoneTextView, mAddrTextView,
			mEmailTextView;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_details);
		findView();
		initView();
		loadData();
	}

	private void loadData() {
		mContact = (Contact) getIntent().getSerializableExtra(
				DetailActivity.EXTRA_INPUT);
		notifyDatasetChanged();
	}

	private void findView() {
		mScrollView = (ScrollView) findViewById(R.id.activity_detail_scrollView);
		mCallBtn = (ImageButton) findViewById(R.id.activity_detail_callBtn);
		mMessageBtn = (ImageButton) findViewById(R.id.activity_detail_messageBtn);
		mDelBtn = (ImageButton) findViewById(R.id.activity_detail_delBtn);
		mNameTextView = (TextView) findViewById(R.id.activity_detail_name);
		mAddrTextView = (TextView) findViewById(R.id.activity_detail_address);
		mEmailTextView = (TextView) findViewById(R.id.activity_detail_email);
		mPhoneTextView = (TextView) findViewById(R.id.activity_detail_phone);
	}

	private void initView() {
		bindEvent();
	}

	private void bindEvent() {
		mCallBtn.setOnClickListener(new CallBtnClickListener());
		mMessageBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String phoneNumber = "15072334868";
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"
						+ phoneNumber));// get(position);
				intent.setType("vnd.android-dir/mms-sms");
				// intent.setData(Uri.parse("content://mms-sms/conversations/"));//此为号码
				startActivity(intent);

			}
		});
		mDelBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(DetailActivity.this).setTitle("确认")
						.setMessage("确定吗？").setPositiveButton("是", null)// 添加删除功能或者不改变
						.setNegativeButton("否", null).show();

			}
		});
		setOnRightBtnClickListener(new OnRightBtnClickListener() {

			@Override
			public void onClick(MenuItem item) {
				Intent intent = new Intent();
				intent.setClass(DetailActivity.this, EditActivity.class);
				startActivity(intent);

			}
		});
	}

	private void notifyDatasetChanged() {
		if(mContact!=null){
			mNameTextView.setText(mContact.getName());
			mAddrTextView.setText(mContact.getAddr());
		}
//		mPhoneTextView.setText((CharSequence) mContact.getPhones());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		setRightTitle("编辑");
		hideSearchBtn();
		return result;
	}

	public class LaunchScrollViewClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {

		}

	}

	public class CallBtnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			String phoneNumber = "15072334868";
			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
					+ phoneNumber));
			startActivity(intent);
		}

	}

}
