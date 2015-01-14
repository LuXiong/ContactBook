package com.ruanko.activity;

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
import android.widget.ListView;
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
	private ListView listView;
	private UserAdapter mAdapter;
	private OnMenuItemClickListener listner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) this.findViewById(R.id.listView);
		listView.setOnItemClickListener(new ItemClickListener());
		show();

		ContactBussiness ub = new ContactBussiness();
		ub.loadLocalContacts(this, new DataBaseListener() {

			@Override
			public void onStart() {
				Log.v("xionglu", "loadContacts start");
			}

			@Override
			public void onSuccess() {
				Log.v("xionglu", "loadContacts success");
			}

			@Override
			public void onFinish() {
				Log.v("xionglu", "loadContacts finish");
			}

			@Override
			public void onFailure(String info) {
				Log.v("xionglu", "loadContacts failure:" + info);
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.actionbar_menu, menu);
		findMenu(menu);
		initMenu();
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_bar_right_item:
		{
			setContentView(R.layout.activity_editor);
			item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
				
				@Override
				public boolean onMenuItemClick(MenuItem it) {
					
			                  Intent intent = new Intent();
	                          intent.setClass(MainActivity.this, EditActivity.class);
                              startActivity(intent);
                              MainActivity.this.finish();
					return false;
				}
			});
		}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	

	private final class ItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			ListView lView = (ListView) parent;// 页面
			Contact user = (Contact) lView.getItemAtPosition(position);
			Toast.makeText(getApplicationContext(), String.valueOf(position), 1)
					.show();

		}

	}
	
	
	
	private void notifyDataSetChanged(){
		mAdapter.notifyDataSetChanged();
	}

	// 自定义适配器
	private void show() {
		List<Contact> users = null;
		mAdapter = new UserAdapter(this, users, R.layout.item_main);
		Log.v("wq", "hi contactbook");
	}



}
