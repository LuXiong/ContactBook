package com.ruanko.activity;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.DeletedContacts;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ruanko.adapter.ContactListAdapter;
import com.ruanko.adapter.ImAdapter;
import com.ruanko.adapter.PhoneAdapter;
import com.ruanko.bussiness.ContactBussiness;
import com.ruanko.common.ContactItemInterface;
import com.ruanko.common.NameTypeInterface;
import com.ruanko.common.Utils;
import com.ruanko.contactbook.BaseActivity;
import com.ruanko.contactbook.R;
import com.ruanko.model.Contact;

public class DetailActivity extends BaseActivity {

	final static public String EXTRA_INPUT = "contact";

	private Contact mContact;

	private ScrollView mScrollView;
	private ImageButton mDelBtn;
	private TextView mNameTextView, mAddrTextView, mEmailTextView;
	private ListView mPhoneListView, mImListView;

	private PhoneAdapter mPhoneAdapter;
	private ImAdapter mImAdapter;

	private ArrayList<NameTypeInterface> mPhoneList, mImList;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_detail);
		findView();
		initView();
		loadData();
	}

	private void loadData() {
		mContact = (Contact) getIntent().getSerializableExtra(
				DetailActivity.EXTRA_INPUT);
		mPhoneList.clear();
		mPhoneList.addAll(mContact.getPhones());
		mImList.clear();
		mPhoneList.addAll(mContact.getIms());
		// Log.v("zhouyezi", "mPhoneListSize:"+mPhoneList.size());
		notifyDatasetChanged();
	}

	private void findView() {
		mScrollView = (ScrollView) findViewById(R.id.activity_detail_scrollView);
		mDelBtn = (ImageButton) findViewById(R.id.activity_detail_delBtn);
		mNameTextView = (TextView) findViewById(R.id.activity_detail_name);
		mAddrTextView = (TextView) findViewById(R.id.activity_detail_address);
		mEmailTextView = (TextView) findViewById(R.id.activity_detail_email);
		mPhoneListView = (ListView) findViewById(R.id.activity_detail_phone_listView);
		mImListView = (ListView) findViewById(R.id.activity_detail_Im_listView);
	}

	private void initView() {

		mPhoneList = new ArrayList<NameTypeInterface>();
		mPhoneAdapter = new PhoneAdapter(mPhoneList, this);
		mPhoneListView.setAdapter(mPhoneAdapter);
		mPhoneListView.setFastScrollEnabled(true);

		mImList = new ArrayList<NameTypeInterface>();
		mImAdapter = new ImAdapter(mImList, this);
		mImListView.setAdapter(mImAdapter);
		mImListView.setFastScrollEnabled(true);

		bindEvent();
	}

	private void bindEvent() {
		mDelBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(DetailActivity.this).setTitle("确认")
						.setMessage("确定吗？").setPositiveButton("是", l)// 添加删除功能或者不改变
						.setNegativeButton("否", null).show();

			}
		});
		setOnRightBtnClickListener(new OnRightBtnClickListener() {

			@Override
			public void onClick(MenuItem item) {
				Intent intent = new Intent();
				intent.setClass(DetailActivity.this,
						CreateContactActivity.class);
				intent.putExtra(CreateContactActivity.EXTRA_INPUT, mContact);
				startActivity(intent);

			}
		});
	}

	private DialogInterface.OnClickListener l = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			ContactBussiness cb = new ContactBussiness();
			cb.deleteContact(DetailActivity.this, mContact);
			DetailActivity.this.finish();

		}
	};

	private void notifyDatasetChanged() {
		if (mContact != null) {
			mNameTextView.setText(mContact.getName());
			mAddrTextView.setText(mContact.getAddr());
			mEmailTextView.setText(mContact.getEmail());
			mPhoneAdapter.notifyDataSetChanged();
			mImAdapter.notifyDataSetChanged();
			Utils.setListViewHeightBasedOnChildren(mPhoneListView);
			Utils.setListViewHeightBasedOnChildren(mImListView);
			/**
			 * mImAdapter.notifyDataSetChanged();
			 * mImListView.setAdapter(mImAdapter);
			 **/
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		setRightResource(R.drawable.item_edit);
		hideSearchBtn();
		return result;
	}

	public class LaunchScrollViewClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {

		}

	}

}
