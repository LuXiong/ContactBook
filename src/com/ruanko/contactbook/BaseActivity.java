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
		// ���Ͻǵķ��ؼ�
		mActionBar.setDisplayHomeAsUpEnabled(true);
		// ʹ��title
		mActionBar.setDisplayShowTitleEnabled(true);
		// ��ʹ��ͼ��
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
				// ���Ͻǵķ��ؼ�
				mActionBar.setDisplayHomeAsUpEnabled(false);
				// ʹ��title
				mActionBar.setDisplayShowTitleEnabled(false);
				// ��ʹ��ͼ��
				mActionBar.setDisplayShowHomeEnabled(false);
				return false;
			}
		});
//		    menuItem.setOnActionExpandListener(new OnActionExpandListener() {
//		        @Override
//		        public boolean onMenuItemActionCollapse(MenuItem item) {
//		        	// ���Ͻǵķ��ؼ�
//		    		getActionBar().setDisplayHomeAsUpEnabled(false);
//		    		// ʹ��title
//		    		getActionBar().setDisplayShowTitleEnabled(false);
//		    		// ��ʹ��ͼ��
//		    		getActionBar().setDisplayShowHomeEnabled(false);
//		            return true;  // Return true to collapse action view
//		        }
//
//		        @Override
//		        public boolean onMenuItemActionExpand(MenuItem item) {
//		        	// ���Ͻǵķ��ؼ�
//		    		getActionBar().setDisplayHomeAsUpEnabled(false);
//		    		// ʹ��title
//		    		getActionBar().setDisplayShowTitleEnabled(false);
//		    		// ��ʹ��ͼ��
//		    		getActionBar().setDisplayShowHomeEnabled(false);
//		            return true;  // Return true to expand action view
//		        }
//		    });
//		rightItem = menu.getItem(R.id.acition_bar_right_item);
//		rightItem.setTitle("����");
		return true;
	}

}
