package com.ruanko.activity;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ruanko.adapter.ImAdapter;
import com.ruanko.adapter.PhoneAdapter;
import com.ruanko.bussiness.ContactBussiness;
import com.ruanko.common.NameTypeInterface;
import com.ruanko.common.Utils;
import com.ruanko.contactbook.BaseActivity;
import com.ruanko.contactbook.R;
import com.ruanko.listener.DataBaseListener;
import com.ruanko.model.Contact;

public class DetailActivity extends BaseActivity {

	final static public String EXTRA_INPUT = "contact";

	private Contact mContact;

	private TextView mNameTextView, mAddrTextView, mEmailTextView;
	private ListView mPhoneListView, mImListView;
	private LinearLayout mEmailLayout, mAddrLayout;

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
		mImList.addAll(mContact.getIms());
		// Log.v("zhouyezi", "mPhoneListSize:"+mPhoneList.size());
		notifyDatasetChanged();
	}

	private void findView() {
		mNameTextView = (TextView) findViewById(R.id.activity_detail_name);
		mAddrTextView = (TextView) findViewById(R.id.activity_detail_address);
		mEmailTextView = (TextView) findViewById(R.id.activity_detail_email);
		mPhoneListView = (ListView) findViewById(R.id.activity_detail_phone_listView);
		mImListView = (ListView) findViewById(R.id.activity_detail_Im_listView);
		mEmailLayout = (LinearLayout) findViewById(R.id.activity_detail_email_layout);
		mAddrLayout = (LinearLayout) findViewById(R.id.activity_detail_address_layout);
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

		setOnRightBtnClickListener(new OnRightBtnClickListener() {

			@Override
			public void onClick(MenuItem item) {
				Intent intent = new Intent();
				intent.setClass(DetailActivity.this,
						CreateContactActivity.class);
				intent.putExtra(CreateContactActivity.EXTRA_INPUT, mContact);
				startActivity(intent);
				DetailActivity.this.finish();
			}
		});
	}

	private DialogInterface.OnClickListener l = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			ContactBussiness cb = new ContactBussiness();
			cb.deleteContact(DetailActivity.this, mContact,
					new DataBaseListener() {

						@Override
						public void onSuccess() {
							DetailActivity.this.finish();
							super.onSuccess();
						}

					});

		}
	};

	private void notifyDatasetChanged() {
		if (mContact != null) {
			if (TextUtils.isEmpty(mContact.getEmail())) {
				mEmailLayout.setVisibility(View.GONE);
			} else {
				mEmailLayout.setVisibility(View.VISIBLE);
				mEmailTextView.setText(mContact.getEmail());
			}
			if (TextUtils.isEmpty(mContact.getAddr())) {
				mAddrLayout.setVisibility(View.GONE);
			} else {
				mAddrLayout.setVisibility(View.VISIBLE);
				mAddrTextView.setText(mContact.getAddr());
			}
			Log.i("zhouyezi", "mContact.getAddr():" + mContact.getAddr());
			mNameTextView.setText(mContact.getName());
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
		setTitle("联系人信息");
		setRightResource(R.drawable.item_edit);
		hideSearchBtn();
		MenuItem deleteItem = menu.findItem(R.id.action_bar_delete_item);
		deleteItem.setVisible(true);
		deleteItem.setIcon(getResources().getDrawable(
				R.drawable.item_detail_delete));
		return result;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_bar_delete_item) {
			new AlertDialog.Builder(DetailActivity.this).setTitle("确认")
					.setMessage("确定吗？").setPositiveButton("是", l)// 添加删除功能或者不改变
					.setNegativeButton("否", null).show();
		}
		return super.onOptionsItemSelected(item);
	}

	public class LaunchScrollViewClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {

		}

	}

}
