package com.ruanko.activity;

import com.ruanko.contactbook.BaseActivity;

import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;

public class CreateContactActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		setRightTitle("新建");
		setTitle("新建联系人");
		return result;
	}
}
