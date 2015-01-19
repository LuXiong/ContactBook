package com.ruanko.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ruanko.adapter.NameTypeEditListAdapter;
import com.ruanko.adapter.NameTypeEditListAdapter.AddItemClickListener;
import com.ruanko.adapter.NameTypeEditListAdapter.DeleteItemClickListener;
import com.ruanko.adapter.NameTypeEditListAdapter.EditTextChangedListener;
import com.ruanko.common.NameTypeInterface;
import com.ruanko.common.PhoneConstant;
import com.ruanko.common.Utils;
import com.ruanko.contactbook.BaseActivity;
import com.ruanko.contactbook.R;

public class CreateContactActivity extends BaseActivity {

	private EditText mNameEditText, mEmailEditText, mAddrEditText;
	private ListView mPhoneListView, mImListView;
	private NameTypeEditListAdapter mPhoneAdapter, mImAdapter;
	private RelativeLayout mPhoneFooterLayout, mImFooterLayout;

	private ArrayList<NameTypeInterface> mPhoneList;
	private ArrayList<NameTypeInterface> mImList;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_create_contact);
		findView();
		initView();
	}

	private void findView() {
		mNameEditText = (EditText) findViewById(R.id.activity_create_contact_name_edit);
		mEmailEditText = (EditText) findViewById(R.id.activity_create_contact_email_edit);
		mAddrEditText = (EditText) findViewById(R.id.activity_create_contact_addr_edit);
		mPhoneListView = (ListView) findViewById(R.id.activity_create_contact_phone_list);
		mImListView = (ListView) findViewById(R.id.activity_create_contact_im_list);
		mPhoneFooterLayout = (RelativeLayout) LayoutInflater.from(this)
				.inflate(R.layout.item_create_contact_footer, null);
		mImFooterLayout = (RelativeLayout) LayoutInflater.from(this).inflate(
				R.layout.item_create_contact_footer, null);
	}

	private void initView() {
		mPhoneList = new ArrayList<NameTypeInterface>();
		mImList = new ArrayList<NameTypeInterface>();
		mPhoneAdapter = new NameTypeEditListAdapter(mPhoneList, this);
		mImAdapter = new NameTypeEditListAdapter(mImList, this);
		mPhoneListView.setAdapter(mPhoneAdapter);
		mImListView.setAdapter(mImAdapter);
		mPhoneListView.addFooterView(mPhoneFooterLayout);
		mPhoneFooterLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mPhoneList.add(new NameTypeInterface() {

					@Override
					public String getModelType() {
						// TODO Auto-generated method stub
						return PhoneConstant.TYPE_MOBILE;
					}

					@Override
					public String getModelName() {
						// TODO Auto-generated method stub
						return null;
					}
				});
				notifyDataSetChanged();
			}
		});
		mImListView.addFooterView(mImFooterLayout);
		mPhoneAdapter
				.setOnDelteItemClickListener(new DeletePhoneItemClickListener());
		mPhoneAdapter
				.setOnAccountEditTextChangedListener(new PhoneEditChangeListener());
		mImAdapter.setOnDelteItemClickListener(new DeleteImItemClickListener());
		mImAdapter
				.setOnAccountEditTextChangedListener(new ImEditChangeListener());
	}

	private void notifyDataSetChanged() {
		mPhoneAdapter.notifyDataSetChanged();
		Utils.setListViewHeightBasedOnChildren(mPhoneListView);
		mImAdapter.notifyDataSetChanged();
		Utils.setListViewHeightBasedOnChildren(mImListView);
	}

	public class ImEditChangeListener implements EditTextChangedListener {

		@Override
		public void editTextChanged(Editable e, int position) {
			// TODO Auto-generated method stub

		}

	}

	public class DeleteImItemClickListener implements DeleteItemClickListener {

		@Override
		public void deleteItemClick(View v, int position) {
			// TODO Auto-generated method stub

		}

	}

	public class AddImItemClickListener implements AddItemClickListener {

		@Override
		public void addItemClick(View v) {
			// TODO Auto-generated method stub

		}

	}

	public class PhoneEditChangeListener implements EditTextChangedListener {

		@Override
		public void editTextChanged(Editable e, int position) {
			// TODO Auto-generated method stub

		}

	}

	public class DeletePhoneItemClickListener implements
			DeleteItemClickListener {

		@Override
		public void deleteItemClick(View v, int position) {
			// TODO Auto-generated method stub

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		setRightTitle("新建");
		setTitle("新建联系人");
		return result;
	}
}
