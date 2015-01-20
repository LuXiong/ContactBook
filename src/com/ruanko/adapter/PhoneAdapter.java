package com.ruanko.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ruanko.common.ContactItemInterface;
import com.ruanko.common.NameTypeInterface;
import com.ruanko.contactbook.R;
import com.ruanko.control.ContactItemComparator;
import com.ruanko.control.ContactsSectionIndexer;
import com.ruanko.model.Contact;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class PhoneAdapter extends BaseAdapter {

	private ArrayList<NameTypeInterface> mPhonesList;
	private Context mContext;

	public PhoneAdapter(ArrayList<NameTypeInterface> mPhonesList,
			Context mContext) {
		this.mPhonesList = mPhonesList;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return mPhonesList.size();// 数据总数;
	}

	@Override
	public Object getItem(int position) {
		return mPhonesList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		NameTypeInterface item = mPhonesList.get(position);
		ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.item_detail_phone, null);
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
		public ImageButton callBtn, messageBtn;

		public ViewHolder(View v) {
			typeView = (TextView) v
					.findViewById(R.id.activity_detail_item_type_value);
			stringView = (TextView) v
					.findViewById(R.id.activity_detail_item_string_value);
			callBtn = (ImageButton) v
					.findViewById(R.id.activity_detail_callBtn);
			messageBtn = (ImageButton) v
					.findViewById(R.id.activity_detail_messageBtn);
			callBtn.setOnClickListener(new CallBtnClickListener());
			messageBtn.setOnClickListener(new MessageBtnClickListener());
		}

	}
	
	public class CallBtnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			String phoneNumber = v.findViewById(R.id.activity_detail_item_string_value).toString();
			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
					+ phoneNumber));
			mContext.startActivity(intent);
		}

	}
	
	public class MessageBtnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			String phoneNumber = "";
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"
					+ phoneNumber));//
			intent.setType("vnd.android-dir/mms-sms");
			// intent.setData(Uri.parse("content://mms-sms/conversations/"));//此为号码
			mContext.startActivity(intent);
		}

	}

	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	}
}
