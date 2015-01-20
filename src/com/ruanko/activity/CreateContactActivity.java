package com.ruanko.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ruanko.adapter.NameTypeEditListAdapter;
import com.ruanko.adapter.NameTypeEditListAdapter.DeleteItemClickListener;
import com.ruanko.adapter.NameTypeEditListAdapter.EditTextChangedListener;
import com.ruanko.adapter.NameTypeEditListAdapter.ItemTypeClickListener;
import com.ruanko.bussiness.ContactBussiness;
import com.ruanko.common.ImConstant;
import com.ruanko.common.NameTypeInterface;
import com.ruanko.common.PhoneConstant;
import com.ruanko.common.Utils;
import com.ruanko.contactbook.BaseActivity;
import com.ruanko.contactbook.R;
import com.ruanko.control.dialog.CustomDialog;
import com.ruanko.control.dialog.DialogBuilder;
import com.ruanko.listener.DataBaseListener;
import com.ruanko.model.Contact;
import com.ruanko.model.Im;
import com.ruanko.model.Phone;

public class CreateContactActivity extends BaseActivity {

	public final static String EXTRA_INPUT = "contact";

	private final static int STATE_CREATE = 0;
	private final static int STATE_EDIT = 1;

	private TextView addPhoneText, addImText;
	private EditText mNameEditText, mEmailEditText, mAddrEditText;
	private ListView mPhoneListView, mImListView;
	private NameTypeEditListAdapter mPhoneAdapter, mImAdapter;
	private RelativeLayout mPhoneFooterLayout, mImFooterLayout;

	private ArrayList<NameTypeInterface> mPhoneList;
	private ArrayList<NameTypeInterface> mImList;

	private Contact mContact;

	private int mState = STATE_CREATE;
	private CustomDialog mDialog;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_create_contact);
		findView();
		initView();
		loadData();
	}

	private void findView() {
		mPhoneFooterLayout = (RelativeLayout) LayoutInflater.from(this)
				.inflate(R.layout.item_create_contact_footer, null);
		mImFooterLayout = (RelativeLayout) LayoutInflater.from(this).inflate(
				R.layout.item_create_contact_footer, null);
		mNameEditText = (EditText) findViewById(R.id.activity_create_contact_name_edit);
		mEmailEditText = (EditText) findViewById(R.id.activity_create_contact_email_edit);
		mAddrEditText = (EditText) findViewById(R.id.activity_create_contact_addr_edit);
		mPhoneListView = (ListView) findViewById(R.id.activity_create_contact_phone_list);
		mImListView = (ListView) findViewById(R.id.activity_create_contact_im_list);

		addPhoneText = (TextView) mPhoneFooterLayout
				.findViewById(R.id.item_create_contact_add_text);
		addImText = (TextView) mImFooterLayout
				.findViewById(R.id.item_create_contact_add_text);
	}

	private void initView() {
		mPhoneList = new ArrayList<NameTypeInterface>();
		mImList = new ArrayList<NameTypeInterface>();
		mPhoneAdapter = new NameTypeEditListAdapter(mPhoneList, this);
		mImAdapter = new NameTypeEditListAdapter(mImList, this);
		mPhoneListView.addFooterView(mPhoneFooterLayout, null, true);
		mImListView.addFooterView(mImFooterLayout, null, true);
		mPhoneListView.setAdapter(mPhoneAdapter);
		mImListView.setAdapter(mImAdapter);
		addPhoneText.setText("添加电话");
		addImText.setText("添加社交账号");
		mPhoneFooterLayout.setOnClickListener(new PhoneAddClickListener());
		mImFooterLayout.setOnClickListener(new ImAddClickListener());
		mPhoneAdapter
				.setOnDelteItemClickListener(new DeletePhoneItemClickListener());
		mPhoneAdapter
				.setOnAccountEditTextChangedListener(new PhoneEditChangeListener());
		mPhoneAdapter.setOnTypeClickListener(new PhoneTypeClickListener());
		mImAdapter.setOnDelteItemClickListener(new DeleteImItemClickListener());
		mImAdapter
				.setOnAccountEditTextChangedListener(new ImEditChangeListener());
		mImAdapter.setOnTypeClickListener(new ImTypeClickListener());
		setOnRightBtnClickListener(new FinishClickListener());

		notifyDataSetChanged();
	}

	private void loadData() {
		mContact = (Contact) getIntent().getSerializableExtra(EXTRA_INPUT);

		if (mContact != null) {
			mPhoneList.clear();
			mPhoneList.addAll(mContact.getPhones());
			mImList.clear();
			mImList.addAll(mContact.getIms());
			mState = STATE_EDIT;
			mNameEditText.setText(mContact.getName());
			mEmailEditText.setText(mContact.getEmail());
			mAddrEditText.setText(mContact.getAddr());

			notifyDataSetChanged();
		}
		mNameEditText.requestFocus();
	}

	private void notifyDataSetChanged() {
		mPhoneAdapter.notifyDataSetChanged();
		Utils.setListViewHeightBasedOnChildrenWithFooterHight(this,
				mPhoneListView, Utils.dip2px(this, 40));
		mImAdapter.notifyDataSetChanged();
		Utils.setListViewHeightBasedOnChildrenWithFooterHight(this,
				mImListView, Utils.dip2px(this, 40));
	}

	public class ImEditChangeListener implements EditTextChangedListener {

		@Override
		public void editTextChanged(Editable e, int position) {
			if (position < mImList.size()) {
				((Im) mImList.get(position)).setAccount(e.toString());
			}
		}

	}

	public class DeleteImItemClickListener implements DeleteItemClickListener {

		@Override
		public void deleteItemClick(View v, int position) {
			if (position < mImList.size()) {
				mImList.remove(position);
				notifyDataSetChanged();
			}
		}

	}

	public class PhoneEditChangeListener implements EditTextChangedListener {

		@Override
		public void editTextChanged(Editable e, int position) {
			if (position < mPhoneList.size()) {
				((Phone) mPhoneList.get(position)).setNumber(e.toString());
			}
		}

	}

	public class DeletePhoneItemClickListener implements
			DeleteItemClickListener {

		@Override
		public void deleteItemClick(View v, int position) {
			if (position < mPhoneList.size()) {
				mPhoneList.remove(position);
				notifyDataSetChanged();
			}
		}

	}

	public class PhoneAddClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			mPhoneList.add(new Phone(Utils.getRandomType(PhoneConstant
					.getTypeArray()), null));
			notifyDataSetChanged();
		}

	}

	public class ImAddClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			mImList.add(new Im(null, Utils.getRandomType(ImConstant
					.getTypeArray())));
			notifyDataSetChanged();
		}

	}

	public class FinishClickListener implements OnRightBtnClickListener {

		@Override
		public void onClick(MenuItem item) {
			ArrayList<Phone> phoneList = new ArrayList<Phone>();
			ArrayList<Im> imList = new ArrayList<Im>();
			for (int i = 0; i < mPhoneList.size(); i++) {
				Phone phone = (Phone) mPhoneList.get(i);
				if (!TextUtils.isEmpty(phone.getNumber())) {
					phoneList.add(phone);
				}
			}
			for (int i = 0; i < mImList.size(); i++) {
				Im im = (Im) mImList.get(i);
				if (!TextUtils.isEmpty(im.getAccount())) {
					imList.add(im);
				}
			}
			final Contact contact = new Contact(null, mNameEditText.getText()
					.toString(), phoneList, imList, null, mAddrEditText
					.getText().toString(), mEmailEditText.getText().toString(),
					null);
			if (mState == STATE_CREATE) {
				ContactBussiness contactBussiness = new ContactBussiness();
				contactBussiness.createNewContact(CreateContactActivity.this,
						contact, new DataBaseListener() {

							@Override
							public void onSuccess() {
								CreateContactActivity.this.finish();
								Intent intent = new Intent(
										CreateContactActivity.this,
										MainActivity.class);
								intent.putExtra(DetailActivity.EXTRA_INPUT,
										contact);
								startActivity(intent);
							}

							@Override
							public void onFailure(String info) {
								Toast.makeText(CreateContactActivity.this,
										info, Toast.LENGTH_LONG).show();
								super.onFailure(info);
							}

						});
			}
			if (mState == STATE_EDIT) {
				ContactBussiness contactBussiness = new ContactBussiness();
				contactBussiness.deleteContact(CreateContactActivity.this,
						mContact, new DataBaseListener() {

							@Override
							public void onFailure(String info) {
								Toast.makeText(CreateContactActivity.this,
										info, Toast.LENGTH_LONG).show();
							}

							@Override
							public void onSuccess() {
								ContactBussiness service = new ContactBussiness();
								service.createNewContact(
										CreateContactActivity.this, contact,
										new DataBaseListener() {
											@Override
											public void onSuccess() {
												CreateContactActivity.this
														.finish();
												Intent intent = new Intent(
														CreateContactActivity.this,
														MainActivity.class);
												startActivity(intent);
											}

											@Override
											public void onFailure(String info) {
												Toast.makeText(
														CreateContactActivity.this,
														info, Toast.LENGTH_LONG)
														.show();
												super.onFailure(info);
											}
										});
							}

						});
				// ContactBussiness contactBussiness = new ContactBussiness();
				// contactBussiness.updateContact(CreateContactActivity.this,
				// mContact, contact, new DataBaseListener() {
				//
				// @Override
				// public void onSuccess() {
				// CreateContactActivity.this.finish();
				// Intent intent = new Intent(
				// CreateContactActivity.this,
				// DetailActivity.class);
				// intent.putExtra(DetailActivity.EXTRA_INPUT,
				// contact);
				// startActivity(intent);
				// }
				//
				// @Override
				// public void onFailure(String info) {
				// Toast.makeText(CreateContactActivity.this,
				// info, Toast.LENGTH_LONG).show();
				// super.onFailure(info);
				// }
				//
				// });
			}
		}
	}

	public class PhoneTypeClickListener implements ItemTypeClickListener {

		@Override
		public void onTypeClick(View v, final int listPosition) {
			final ArrayList<String> phoneTypeList = PhoneConstant
					.getTypeArray();
			DialogBuilder builder = new DialogBuilder();
			builder.setType(CustomDialog.LIST_DIALOG);
			builder.setListDialogContent(phoneTypeList);
			builder.setListItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					((Phone) (mPhoneList.get(listPosition)))
							.setType(phoneTypeList.get(position));
					notifyDataSetChanged();
					mDialog.dismiss();
				}
			});
			mDialog = new CustomDialog(CreateContactActivity.this, builder);
			mDialog.show();
		}

	}

	public class ImTypeClickListener implements ItemTypeClickListener {

		@Override
		public void onTypeClick(View v, final int listPosition) {
			final ArrayList<String> imTypeList = ImConstant.getTypeArray();
			DialogBuilder builder = new DialogBuilder();

			builder.setType(CustomDialog.LIST_DIALOG);
			builder.setListDialogContent(imTypeList);
			builder.setListItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					((Im) (mImList.get(listPosition))).setType(imTypeList
							.get(position));
					notifyDataSetChanged();
					mDialog.dismiss();
				}
			});
			mDialog = new CustomDialog(CreateContactActivity.this, builder);
			mDialog.show();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		setRightResource(R.drawable.item_create_contact_finish_img);
		hideSearchBtn();
		if (mState == STATE_CREATE) {
			setTitle("新建联系人");
		}
		if (mState == STATE_EDIT) {
			setTitle("编辑联系人");
		}
		return result;
	}

}
