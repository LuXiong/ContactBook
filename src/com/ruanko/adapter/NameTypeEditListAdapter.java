package com.ruanko.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ruanko.common.NameTypeInterface;
import com.ruanko.contactbook.R;

public class NameTypeEditListAdapter extends BaseAdapter {
	public class AddLayoutClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

		}

	}


	public class DeleteLayoutClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

		}

	}


	private Context mContext;
	private ArrayList<NameTypeInterface> mList;

	public NameTypeEditListAdapter(ArrayList<NameTypeInterface> list,
			Context context) {
		this.mContext = context;
		this.mList = list;
	}

	@Override
	public int getCount() {
		return mList.size() + 1;
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		NameTypeInterface item = mList.get(position);
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_create_contact, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (position == mList.size()) {
			holder.editLayout.setVisibility(View.GONE);
			holder.addLayout.setVisibility(View.VISIBLE);
			holder.addLayout.setOnClickListener(new AddLayoutClickListener());
			
		} else {
			holder.editLayout.setVisibility(View.VISIBLE);
			holder.addLayout.setVisibility(View.GONE);
			holder.deleteImg.setOnClickListener(new DeleteLayoutClickListener());
		}
		return null;
	}


	private class ViewHolder {
		public ImageView deleteImg;
		public TextView typeTextView;
		public EditText accountEditText;
		public LinearLayout addLayout;
		public LinearLayout editLayout;

		public ViewHolder(View v) {
			deleteImg = (ImageView) v
					.findViewById(R.id.item_create_contact_delete_img);
			typeTextView = (TextView) v
					.findViewById(R.id.item_create_contact_phone_type_text);
			accountEditText = (EditText) v
					.findViewById(R.id.item_create_contact_content_editText);
			addLayout = (LinearLayout) v
					.findViewById(R.id.item_create_contact_add_layout);
			addLayout = (LinearLayout) v
					.findViewById(R.id.item_create_contact_edit_layout);
		}
	}

}
