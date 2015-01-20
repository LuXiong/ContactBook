package com.ruanko.adapter;

import java.util.ArrayList;

import com.ruanko.common.NameTypeInterface;
import com.ruanko.contactbook.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ImAdapter extends BaseAdapter {

	private ArrayList<NameTypeInterface> mImsList;
	private Context mContext;
	
	
	public ImAdapter(ArrayList<NameTypeInterface> mImList, Context mContext) {
		this.mImsList = mImList;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return mImsList.size();
	}

	@Override
	public Object getItem(int position) {
		return mImsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		NameTypeInterface item = mImsList.get(position);
		ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.item_detail_im, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.typeView.setText(item.getModelType());
		holder.stringView.setText(item.getModelName());
		return convertView;

	}

	private class ViewHolder {
		public TextView typeView, stringView;

		public ViewHolder(View v) {
			typeView = (TextView) v
					.findViewById(R.id.activity_detail_item_im_type_value);
			stringView = (TextView) v
					.findViewById(R.id.activity_detail_item_im_string_value);
		}

	}
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	}

}
