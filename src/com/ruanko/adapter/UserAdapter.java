package com.ruanko.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ruanko.contactbook.R;
import com.ruanko.model.Contact;

public class UserAdapter extends BaseAdapter {



	private List<Contact> users;
	private int resource;//绑定条目
	private LayoutInflater inflater; //生成xml对应的view对象
	

	public UserAdapter(Context context,List<Contact> users,int resource) {
		this.users = users;
		this.resource = resource;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return users.size();//数据总数
	}

	@Override
	public Object getItem(int position) {
		return users.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView nameView=null;
		
		if(convertView == null){
			convertView = inflater.inflate(resource, null);//生成条目
			nameView = (TextView)convertView.findViewById(R.id.name);
			
			ViewCache cache = new ViewCache();
			cache.nameView = nameView;
			convertView.setTag(cache);
		}else{
			ViewCache cache = (ViewCache)convertView.getTag();
			nameView = cache.nameView;
		}
		Contact user = users.get(position);
		nameView.setText(user.getName());//绑定数据
		
		return convertView;
	}

	private final class ViewCache{
		public TextView nameView;
	}
}
