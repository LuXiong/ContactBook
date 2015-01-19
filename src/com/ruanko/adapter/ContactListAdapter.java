package com.ruanko.adapter;

import java.util.ArrayList;
import java.util.Collections;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ruanko.common.ContactItemInterface;
import com.ruanko.contactbook.R;
import com.ruanko.control.ContactItemComparator;
import com.ruanko.control.ContactsSectionIndexer;

public class ContactListAdapter extends BaseAdapter{

	private ArrayList<ContactItemInterface> mContactList; 
	private boolean inSearchMode = false;
	private Context mContext;
	
	private ContactsSectionIndexer indexer = null;
	
	public ContactListAdapter(Context _context,  ArrayList<ContactItemInterface> _items) {
		this.mContactList = _items;
		this.mContext = _context;
		Collections.sort(_items, new ContactItemComparator());
		setIndexer(new ContactsSectionIndexer(_items));
	
	}
	@Override
	public void notifyDataSetChanged() {
		Collections.sort(mContactList, new ContactItemComparator());
		setIndexer(new ContactsSectionIndexer(mContactList));
		super.notifyDataSetChanged();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {		
		ContactItemInterface item = mContactList.get(position);
		ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.contact_list_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
	    } else {
	    	holder = (ViewHolder)convertView.getTag();
	    }
		holder.nameTextView.setText(item.getDisplayItem());		
		if(inSearchMode){
			holder.sectionTextView.setVisibility(View.GONE);
	    }
	    else
	    {
		    if(indexer.isFirstItemInSection(position)){
		    	
		    	String sectionTitle = indexer.getSectionTitle(item.getOrderingItem());
		    	holder.sectionTextView.setText(sectionTitle);
		    	holder.sectionTextView.setVisibility(View.VISIBLE);
		    	
		    }
		    else
		    	holder.sectionTextView.setVisibility(View.GONE);
	    }
	
		return convertView;
		
	}
	
	private class ViewHolder{
		public TextView sectionTextView;
		public TextView nameTextView;
		public ViewHolder(View v){
			sectionTextView =(TextView)v.findViewById(R.id.item_main_section_text);
			nameTextView = (TextView) v.findViewById(R.id.item_main_name_text);
		}
		
	}

	public boolean isInSearchMode() {
		return inSearchMode;
	}

	public void setInSearchMode(boolean inSearchMode) {
		this.inSearchMode = inSearchMode;
	}

	public ContactsSectionIndexer getIndexer() {
		return indexer;
	}

	public void setIndexer(ContactsSectionIndexer indexer) {
		this.indexer = indexer;
	}

	@Override
	public int getCount() {
		return mContactList.size();
	}

	@Override
	public Object getItem(int position) {
		return mContactList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	
	
}
