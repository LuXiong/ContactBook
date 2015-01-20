package com.ruanko.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

	private Context mContext;
	private ArrayList<NameTypeInterface> mList;
	private EditText mEditText;
	private EditTextChangedListener mAccountEditTextChangedListener;
	private DeleteItemClickListener mDeleteItemClickListener;

	public NameTypeEditListAdapter(ArrayList<NameTypeInterface> list,
			Context context) {
		this.mContext = context;
		this.mList = list;
		// mListEmptyCheck();
	}

	@Override
	public void notifyDataSetChanged() {
		// mListEmptyCheck();
		super.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mList.size();
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

		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_create_contact, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.accountEditText.requestFocus();
		NameTypeInterface item = mList.get(position);
		if (item != null) {
			holder.deleteImg.setOnClickListener(new DeleteLayoutClickListener(
					position));
			holder.typeTextView.setText(item.getModelType());
			holder.accountEditText.setText(item.getModelName());
			holder.accountEditText
					.addTextChangedListener(new AccountEditTextChanged(position));
		}

		return convertView;
	}
	private void mListEmptyCheck() {
		if (mList.isEmpty()) {
			mList.add(new NameTypeInterface() {

				@Override
				public String getModelType() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public String getModelName() {
					// TODO Auto-generated method stub
					return null;
				}
			});
		}
	}

	public void setOnDelteItemClickListener(DeleteItemClickListener l) {
		this.mDeleteItemClickListener = l;
	}

	public void setOnAccountEditTextChangedListener(EditTextChangedListener l) {
		this.mAccountEditTextChangedListener = l;
	}

	public class AccountEditTextChanged implements TextWatcher {
		private int mPosition;

		public AccountEditTextChanged(int position) {
			this.mPosition = position;
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {

		}

		@Override
		public void afterTextChanged(Editable s) {
			if (mAccountEditTextChangedListener != null) {
				mAccountEditTextChangedListener.editTextChanged(s, mPosition);
			}
		}

	}

	public class DeleteLayoutClickListener implements OnClickListener {
		private int mPosition;

		public DeleteLayoutClickListener(int position) {
			this.mPosition = position;
		}

		@Override
		public void onClick(View v) {
			if (mDeleteItemClickListener != null) {
				mDeleteItemClickListener.deleteItemClick(v, mPosition);
			}
		}

	}

	public interface AddItemClickListener {
		public void addItemClick(View v);
	}

	public interface DeleteItemClickListener {
		public void deleteItemClick(View v, int position);
	}

	public interface EditTextChangedListener {
		public void editTextChanged(Editable e, int position);
	}

	private class ViewHolder {
		public ImageView deleteImg;
		public TextView typeTextView;
		public EditText accountEditText;

		public ViewHolder(View v) {
			deleteImg = (ImageView) v
					.findViewById(R.id.item_create_contact_delete_img);
			typeTextView = (TextView) v
					.findViewById(R.id.item_create_contact_phone_type_text);
			accountEditText = (EditText) v
					.findViewById(R.id.item_create_contact_content_editText);

		}
	}

}
