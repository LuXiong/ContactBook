package com.ruanko.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;

import com.ruanko.adapter.ContactListAdapter;
import com.ruanko.bussiness.ContactBussiness;
import com.ruanko.common.ContactItemInterface;
import com.ruanko.contactbook.BaseActivity;
import com.ruanko.contactbook.R;
import com.ruanko.control.ContactListView;
import com.ruanko.model.Contact;

public class MainActivity extends BaseActivity {
	private ContactListView mListView;
	private BaseAdapter mAdapter;
	private ArrayList<ContactItemInterface> mContactList;
	private ArrayList<ContactItemInterface> mFilterList;
	private boolean inSearchMode = false;
	private String mSearchText = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findView();
		initView();
		loadData();
	}

	@Override
	protected void onResume() {
		loadData();
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		setRightResource(R.drawable.item_add);
		setTitle("ÁªÏµÈË");
		mSearchItem.setOnActionExpandListener(new OnActionExpandListener() {

			@Override
			public boolean onMenuItemActionExpand(MenuItem item) {
				inSearchMode = true;
				notifyDataSetChanged();
				return true;
			}

			@Override
			public boolean onMenuItemActionCollapse(MenuItem item) {
				inSearchMode = false;
				notifyDataSetChanged();
				return true;
			}
		});
		hideDisplayHomeAsUpBtn();
		return result;
	}

	private void findView() {
		mListView = (ContactListView) findViewById(R.id.activity_main_listView);
	}

	private void initView() {
		mContactList = new ArrayList<ContactItemInterface>();
		mFilterList = new ArrayList<ContactItemInterface>();
		mAdapter = new ContactListAdapter(this, mContactList);
		mListView.setAdapter(mAdapter);
		mListView.setFastScrollEnabled(true);
		mListView.setOnItemClickListener(new ItemClickListener());
		setOnRightBtnClickListener(new OnRightBtnClickListener() {

			@Override
			public void onClick(MenuItem item) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, CreateContactActivity.class);
				startActivity(intent);
			}
		});
		setOnSearchVieTextChangeListener(new onSearchViewTextChangeListener() {

			@Override
			public void onSearchViewSubmit(String content) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSearchViewChanged(String content) {
				mSearchText = content;
				if (!TextUtils.isEmpty(content)) {
					mFilterList.clear();
					for (int i = 0; i < mContactList.size(); i++) {
						Contact contact = (Contact) mContactList.get(i);
						String name = contact.getName();
						if (name.contains(content)) {
							mFilterList.add(contact);
						}

					}
				}
				notifyDataSetChanged();
			}
		});
	}

	private void loadData() {
		ContactBussiness cb = new ContactBussiness();
		mContactList.clear();
		mContactList.addAll(cb.fetchContactInformation(this));
		notifyDataSetChanged();
	}

	private final class ItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Contact contact;
			if (inSearchMode) {
				contact = (Contact) mFilterList.get(position);
			} else {
				contact = (Contact) mContactList.get(position);
			}
			if (contact != null) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, DetailActivity.class);
				intent.putExtra(DetailActivity.EXTRA_INPUT, contact);
				startActivity(intent);
			}

		}

	}

	private void notifyDataSetChanged() {
		if (inSearchMode && !TextUtils.isEmpty(mSearchText)) {
			mListView.setAdapter(new ContactListAdapter(this, mFilterList));
		} else {
			mListView.setAdapter(new ContactListAdapter(this, mContactList));
		}

	}

}
