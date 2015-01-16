package com.ruanko.activity;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ruanko.bussiness.ContactBussiness;
import com.ruanko.contactbook.BaseActivity;
import com.ruanko.contactbook.R;
import com.ruanko.contactbook.BaseActivity.OnRightBtnClickListener;
import com.ruanko.model.Contact;

public class DetailActivity extends BaseActivity {
	
	final static public String EXTRA_INPUT = "contact"; 
	
	private ScrollView scrollView;
	private ArrayList<Contact> mContact;
	
	private ImageButton mBtnCall;
	private ImageButton mBtnMessage;
    private ImageButton mBtnDel;
    
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_details);   
		findView();
		initView();
		
	}

	
	private void findView() {
		scrollView = (ScrollView)this.findViewById(R.id.ScrollView1);
		
	}

    private void initView() {
    	mContact = new ArrayList<Contact>();
    	ContactBussiness cb = new ContactBussiness();
    	mContact = cb.fetchContactInformation(this);
    	mBtnCall = (ImageButton)this.findViewById(R.id.btn_call);
    	mBtnMessage = (ImageButton)this.findViewById(R.id.btn_message);
    	mBtnDel = (ImageButton)this.findViewById(R.id.btn_delete);
    	mBtnCall.setOnClickListener(new OnClickListener() {
    		@Override
			public void onClick(View v) {
				String phoneNumber = "15072334868";
				Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:" + phoneNumber));
				//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});
    	mBtnMessage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String phoneNumber = "15072334868";
				Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:" + phoneNumber));//get(position); 
				intent.setType("vnd.android-dir/mms-sms");  
				 // intent.setData(Uri.parse("content://mms-sms/conversations/"));//��Ϊ����
				startActivity(intent);
				
			}
		});
    	mBtnDel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(DetailActivity.this) 
				.setTitle("ȷ��")
				.setMessage("ȷ����")
				.setPositiveButton("��", null)//���ɾ�����ܻ��߲��ı�
				.setNegativeButton("��", null)
				.show();
			
			}
		});
		setOnRightBtnClickListener(new OnRightBtnClickListener() {
			
			@Override
			public void onClick(MenuItem item) {
				Intent intent = new Intent();
                intent.setClass(DetailActivity.this, EditActivity.class);
                startActivity(intent);
				
			}
			});
    	
    	initData();
		bindEvent();
	}


	private void bindEvent() {

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	boolean result = super.onCreateOptionsMenu(menu);
	setRightTitle("�༭");
	hideSearchBtn();
	return result;
}

	private void initData() {
		//setTitle("hello world");  

		TextView m_name = (TextView)this.findViewById(R.id.name);   
		TextView m_addr = (TextView)this.findViewById(R.id.address); 
		TextView m_email= (TextView)this.findViewById(R.id.email); 
        Contact mContact = (Contact)getIntent().getSerializableExtra(DetailActivity.EXTRA_INPUT);    
        m_name.setText(mContact.getName()); 
        m_addr.setText(mContact.getAddr()); 
     
		
	}

	public class LaunchScrollViewClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			
		}

	}
	
}
