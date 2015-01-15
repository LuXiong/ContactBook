package com.ruanko.activity;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ScrollView;

import com.ruanko.bussiness.ContactBussiness;
import com.ruanko.contactbook.BaseActivity;
import com.ruanko.contactbook.R;
import com.ruanko.model.Contact;

public class DetailActivity extends BaseActivity {
	
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
				 // intent.setData(Uri.parse("content://mms-sms/conversations/"));//此为号码
				startActivity(intent);
				
			}
		});
    	mBtnDel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(DetailActivity.this) 
				.setTitle("确认")
				.setMessage("确定吗？")
				.setPositiveButton("是", null)//添加删除功能或者不改变
				.setNegativeButton("否", null)
				.show();
			
			}
		});
    	
    	initData();
		bindEvent();
	}


	private void bindEvent() {

		
	}


	private void initData() {
		//setTitle("hello world");
	}

	public class LaunchScrollViewClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			
		}

	}
	
}
