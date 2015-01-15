package com.ruanko.activity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ruanko.adapter.UserAdapter;
import com.ruanko.bussiness.ContactBussiness;
import com.ruanko.common.PhoneConstant;
import com.ruanko.contactbook.BaseActivity;
import com.ruanko.contactbook.R;
import com.ruanko.model.Contact;
import com.ruanko.model.Phone;
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
		ArrayList<Contact> contacts = (new ContactBussiness())
				.fetchContactInformation(this);
		ImageView img = (ImageView) this.findViewById(R.id.img);
		Contact contact = new Contact();
		contact.setName("haha");
		ContactBussiness c = new ContactBussiness();
		c.createNewContact(this, contact);
		listView = (ListView) this.findViewById(R.id.listView);
		listView.setOnItemClickListener(new ItemClickListener());
		show();
		setOnRightBtnClickListener(new OnRightBtnClickListener() {

			@Override
			public void onClick(MenuItem item) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, EditActivity.class);
				startActivity(intent);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		setRightTitle("新建");
		return result;
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

	private void notifyDataSetChanged() {
		mAdapter.notifyDataSetChanged();
	}

	// 自定义适配器
	private void show() {
		List<Contact> users = null;
		mAdapter = new UserAdapter(this, users, R.layout.item_main);
		Log.v("wq", "hi contactbook");
	}

}
