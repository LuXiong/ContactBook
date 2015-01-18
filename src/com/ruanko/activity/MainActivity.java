package com.ruanko.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.ruanko.adapter.ContactListAdapter;
import com.ruanko.bussiness.ContactBussiness;
import com.ruanko.contactbook.BaseActivity;
import com.ruanko.contactbook.R;
import com.ruanko.control.ContactItemInterface;
import com.ruanko.control.ContactListView;
import com.ruanko.model.Contact;

public class MainActivity extends BaseActivity {
	private ContactListView mListView;
	private BaseAdapter mAdapter;
	private ArrayList<ContactItemInterface> mContactList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findView();
		initView();
		loadData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		setRightTitle("新建");
		setTitle("联系人");
		return result;
	}

	private void findView() {
		mListView = (ContactListView) findViewById(R.id.activity_main_listView);
	}

	private void initView() {
		mContactList = new ArrayList<ContactItemInterface>();
		mAdapter = new ContactListAdapter(this, mContactList);
		mListView.setAdapter(mAdapter);
		mListView.setFastScrollEnabled(true);
		mListView.setOnItemClickListener(new ItemClickListener());
		setOnRightBtnClickListener(new OnRightBtnClickListener() {

			@Override
			public void onClick(MenuItem item) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, EditActivity.class);
				startActivity(intent);
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
			mContactList.get(position);
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, DetailActivity.class);
			intent.putExtra(DetailActivity.EXTRA_INPUT,
					(Contact) mContactList.get(position));
			startActivity(intent);
		}

	}

	private void notifyDataSetChanged() {
		mAdapter.notifyDataSetChanged();
		mListView.notifyAdapterChanged(mAdapter);
	}

}
