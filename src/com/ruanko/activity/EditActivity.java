package com.ruanko.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ScrollView;

import com.ruanko.contactbook.BaseActivity;
import com.ruanko.contactbook.R;

public class EditActivity extends BaseActivity {
	
	private ScrollView scrollView;
	private MenuItem item;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setTitle("编辑联系人");
		setRightTitle("保存");
		setContentView(R.layout.activity_editor);
		scrollView = (ScrollView)this.scrollView;
	}
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_bar_right_item:
		{
			setContentView(R.layout.activity_details);		
			item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
     		@Override
				public boolean onMenuItemClick(MenuItem it) {
					 Intent intent = new Intent();
                     intent.setClass(EditActivity.this, DetailActivity.class);
                     startActivity(intent);
                     EditActivity.this.finish();
					 return false;
				}
			});
		}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
}
