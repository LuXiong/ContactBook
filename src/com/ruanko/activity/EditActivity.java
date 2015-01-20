package com.ruanko.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.ruanko.activity.DetailActivity.CallBtnClickListener;
import com.ruanko.contactbook.BaseActivity;
import com.ruanko.contactbook.R;

public class EditActivity extends BaseActivity {

	private ScrollView scrollView;
	private MenuItem item;
	private Button phoneBtn;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_editor);
		setOnRightBtnClickListener(new OnRightBtnClickListener() {

			@Override
			public void onClick(MenuItem item) {
				Intent intent = new Intent();
				intent.setClass(EditActivity.this, DetailActivity.class);
				startActivity(intent);
				EditActivity.this.finish();
			}
		});
		scrollView = (ScrollView) this.scrollView;
		findView();
		initView();
	}

	private void initView() {
		bindEvent();
		
	}

	private void bindEvent() {
		phoneBtn.setOnClickListener(new OnClickListener(){
				public void onClick(View v) {
					final String[] lang = {"手机","住宅","工作","自定义"};
					Dialog alertDialog = new AlertDialog.Builder(EditActivity.this)
					.setTitle("电话")
					.setSingleChoiceItems(lang,0,new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface dialog, int which){
							Toast.makeText(getApplicationContext(), lang[which], Toast.LENGTH_SHORT).show();
							dialog.dismiss();
							if(which==3){
								final EditText et = new EditText(EditActivity.this);  
								new AlertDialog.Builder(EditActivity.this)
								.setTitle("自定义标签名称")	
								.setView(et)
								.setPositiveButton("确定", new DialogInterface.OnClickListener(){
									public void onClick(DialogInterface dialog,
											int which) {
										String input = et.getText().toString(); 
										if (input.equals("")) {  
									        Toast.makeText(getApplicationContext(), "内容不能为空！", Toast.LENGTH_LONG).show();  
									    }  
										else {  
											Toast.makeText(getApplicationContext(), input, Toast.LENGTH_LONG).show();
											
									        }  
									}
									
								})
								.setNegativeButton("取消", null)
								.show();
								
							}
						}
				})
				.create();
			    alertDialog.show();
			};
			
	});
	}
	

	private void findView() {
	phoneBtn = (Button)findViewById(R.id.activity_edit_phone_items);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		setRightTitle("保存");
		hideSearchBtn();
		return result;
	}

}
