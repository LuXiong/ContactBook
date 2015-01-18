package com.ruanko.contactbook;

import android.app.ActionBar;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

public class BaseActivity extends FragmentActivity {
	private ActionBar mActionBar;
	private MenuItem mSearchItem, mRightItem;
	private onSearchViewTextChangeListener mSearchViewChangeListener;
	private OnRightBtnClickListener mRightBtnClickListener;

	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		mActionBar = getActionBar();
		// left top button to get back
		mActionBar.setDisplayHomeAsUpEnabled(true);
		// using title
		mActionBar.setDisplayShowTitleEnabled(true);
		// do not use icon
		mActionBar.setDisplayShowHomeEnabled(false);

	}

	public void hideDisplayHomeAsUpBtn() {
		mActionBar.setDisplayHomeAsUpEnabled(false);
	}
	
	public void hideSearchBtn(){
		mSearchItem.setVisible(false);
	}

	public void setRightTitle(String title) {
		mRightItem.setTitle(title);
		mRightItem.setIcon(null);
	}

	public void setRightResource(int res) {
		mRightItem.setTitle("");
		mRightItem.setIcon(res);
	}

	public void setRightResource(Drawable drawable) {
		mRightItem.setTitle("");
		mRightItem.setIcon(drawable);
	}

	public void setTitle(String title) {
		mActionBar.setTitle(title);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_bar_right_item:
			if (mRightBtnClickListener != null) {
				mRightBtnClickListener.onClick(item);
			}
		
			break;
		case android.R.id.home:
			finish();
			break;
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.actionbar_menu, menu);
		findMenu(menu);
		initMenu();
		return true;
	}

	private void findMenu(Menu menu) {
		mSearchItem = menu.findItem(R.id.action_bar_right_item_search);
		mRightItem = menu.findItem(R.id.action_bar_right_item);
	}

	private void initMenu() {
		SearchView searchView = (SearchView) mSearchItem.getActionView();
		searchView.setOnQueryTextListener(searchViewChangeListener);
	}

	public interface onSearchViewTextChangeListener {
		public void onSearchViewChanged(String content);

		public void onSearchViewSubmit(String content);
	}

	public interface OnRightBtnClickListener {
		public void onClick(MenuItem item);
	}

	public void setOnSearchVieTextChangeListener(
			onSearchViewTextChangeListener l) {
		this.mSearchViewChangeListener = l;
	}

	public void setOnRightBtnClickListener(OnRightBtnClickListener l) {
		this.mRightBtnClickListener = l;
	}

	private OnQueryTextListener searchViewChangeListener = new OnQueryTextListener() {

		@Override
		public boolean onQueryTextSubmit(String query) {
			if (mSearchViewChangeListener != null) {
				mSearchViewChangeListener.onSearchViewSubmit(query);
			}

			return true;// return true to handle this action instead of the
						// default
		}

		@Override
		public boolean onQueryTextChange(String newText) {
			if (mSearchViewChangeListener != null) {
				mSearchViewChangeListener.onSearchViewChanged(newText);
			}

			return true;// return true to handle this action instead of the
						// default
		}
	};

}
