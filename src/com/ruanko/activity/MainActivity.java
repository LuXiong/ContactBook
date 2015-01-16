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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
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
	
	private Button mBtnTest;

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
		
		mBtnTest = (Button)this.findViewById(R.id.btn_test);
		mBtnTest.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
			if((v = mBtnTest )!= null){
		         SerializeMethod();  
			}
				
			}
		});
		
		}
	
		private void findView() {
			mListView = (ListView) this.findViewById(R.id.listView);
		}
	
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		setRightTitle("�½�");
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
			mContactList.get(position).getName();//��ȡ����
			mContactList.get(position).getPhones();//��ȡ�绰
			mContactList.get(position).getAddr();//��ȡ��ַ
			mContactList.get(position).getEmails();//��ȡ�ʼ�
			

		}

	}

	public void SerializeMethod(){    
        Contact contact = new Contact(); 
        contact.setName("zhouyezi");    
        contact.setAddr("hust");
       // mContact.setPhones("","");    
        Intent intent = new Intent();  
        intent.setClass(MainActivity.this,DetailActivity.class);  
        intent.putExtra(DetailActivity.EXTRA_INPUT,contact);          
        startActivity(intent);    
    }    

	
	private void notifyDataSetChanged(){
		mAdapter.notifyDataSetChanged();
	}




}
