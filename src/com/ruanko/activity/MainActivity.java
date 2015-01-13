package com.ruanko.activity;

import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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

	// 自定义适配器
	private void show() {
		List<Contact> users = null;
		UserAdapter adapter = new UserAdapter(this, users, R.layout.item_main);

		Log.v("wq", "hi contactbook");
	}



}
