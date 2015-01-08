package com.ruanko.activity;

import java.util.List;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;


import com.ruanko.adapter.UserAdapter;
import com.ruanko.bussiness.UserBussiness;
import com.ruanko.contactbook.BaseActivity;
import com.ruanko.contactbook.R;
import com.ruanko.listener.DataBaseListener;
import com.ruanko.model.User;

public class MainActivity extends BaseActivity {
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView)this.findViewById(R.id.listView);
		listView.setOnItemClickListener(new ItemClickListener());
		show();
		
		UserBussiness ub= new UserBussiness();
		ub.addName(new DataBaseListener(){

			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
			}

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				super.onSuccess();
			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				super.onFinish();
			}

			@Override
			public void onFailure() {
				// TODO Auto-generated method stub
				super.onFailure();
			}
			
		});
	}
	private final class ItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			ListView lView = (ListView)parent;//页面
			User user = (User)lView.getItemAtPosition(position);
	//		Toast.makeText(getApplicationContext(), user.getId().toString(), 1).show();
			
		}
		
	}
//自定义适配器
	private void show() {
		List<User> users = null;
		UserAdapter adapter = new UserAdapter(this,users,R.layout.item);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
