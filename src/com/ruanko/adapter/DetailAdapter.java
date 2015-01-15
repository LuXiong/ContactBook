package com.ruanko.adapter;

import java.util.ArrayList;

import com.ruanko.model.Contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class DetailAdapter extends BaseAdapter {

	private ArrayList<Contact> details;
	private int resource;
	private LayoutInflater inflater;
	
	
	
	public DetailAdapter(ArrayList<Contact> details, int resource, Context context) {
		this.details = details;
		this.resource = resource;
		inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return details.size();
	}

	@Override
	public Object getItem(int position) {
		return details.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		return null;
	}

}
