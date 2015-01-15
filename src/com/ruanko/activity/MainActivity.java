package com.ruanko.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ruanko.adapter.UserAdapter;
import com.ruanko.bussiness.ContactBussiness;
import com.ruanko.contactbook.BaseActivity;
import com.ruanko.contactbook.R;
import com.ruanko.listener.DataBaseListener;
import com.ruanko.model.Contact;
//import com.ruanko.adapter.UserAdapter;

//import com.ruanko.model.User;

public class MainActivity extends BaseActivity {
	private ListView mListView;
	private UserAdapter mAdapter;
	private OnMenuItemClickListener listner;
	private ArrayList<Contact> mContactList;
	
	EditText editViewName;
	EditText editViewPhone;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findView();
		initView();
		
		
	}

	private void initView() {
		
		mContactList = new ArrayList<Contact>();
		ContactBussiness cb = new ContactBussiness();
		mContactList = cb.fetchContactInformation(this);
		mAdapter = new UserAdapter(this, mContactList, R.layout.item_main);
		mListView.setAdapter(mAdapter);
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
	
		private void findView() {
			mListView = (ListView) this.findViewById(R.id.listView);
		}
	
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		setRightTitle("新建");
		return result;
	}
	

	
	

	private final class ItemClickListener implements OnItemClickListener {
		//TextView m;
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			mContactList.get(position);
			/****
			Toast.makeText(getApplicationContext(), String.valueOf(position), 1)
					.show();
					****/
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, DetailActivity.class);
			startActivity(intent);
			mContactList.get(position).getName();//获取姓名
			mContactList.get(position).getPhones();//获取电话
			mContactList.get(position).getAddr();//获取地址
			mContactList.get(position).getEmails();//获取邮件
			

		}

	}
	
	
	
	private void notifyDataSetChanged(){
		mAdapter.notifyDataSetChanged();
	}




}
