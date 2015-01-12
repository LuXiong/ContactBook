package com.ruanko.contactbook;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

public class BaseActivity extends FragmentActivity {
	ActionBar mActionBar;
	MenuItem rightItem;

	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		mActionBar = getActionBar();
		// 左上角的返回键
		mActionBar.setDisplayHomeAsUpEnabled(true);
		// 使用title
		mActionBar.setDisplayShowTitleEnabled(true);
		// 不使用图标
		mActionBar.setDisplayShowHomeEnabled(false);


	}

	public void setRightTitle(String title) {

	}

	public void setRightResource() {

	}

	public void setTitle(String title) {

		mActionBar.setTitle(title);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.actionbar_menu, menu);
		 MenuItem menuItem = menu.findItem(R.id.acition_bar_right_item_search);
		 menuItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				mActionBar = getActionBar();
				// 左上角的返回键
				mActionBar.setDisplayHomeAsUpEnabled(false);
				// 使用title
				mActionBar.setDisplayShowTitleEnabled(false);
				// 不使用图标
				mActionBar.setDisplayShowHomeEnabled(false);
				return false;
			}
		});
//		    menuItem.setOnActionExpandListener(new OnActionExpandListener() {
//		        @Override
//		        public boolean onMenuItemActionCollapse(MenuItem item) {
//		        	// 左上角的返回键
//		    		getActionBar().setDisplayHomeAsUpEnabled(false);
//		    		// 使用title
//		    		getActionBar().setDisplayShowTitleEnabled(false);
//		    		// 不使用图标
//		    		getActionBar().setDisplayShowHomeEnabled(false);
//		            return true;  // Return true to collapse action view
//		        }
//
//		        @Override
//		        public boolean onMenuItemActionExpand(MenuItem item) {
//		        	// 左上角的返回键
//		    		getActionBar().setDisplayHomeAsUpEnabled(false);
//		    		// 使用title
//		    		getActionBar().setDisplayShowTitleEnabled(false);
//		    		// 不使用图标
//		    		getActionBar().setDisplayShowHomeEnabled(false);
//		            return true;  // Return true to expand action view
//		        }
//		    });
//		rightItem = menu.getItem(R.id.acition_bar_right_item);
//		rightItem.setTitle("搜索");
		return true;
	}

}
