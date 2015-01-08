package com.ruanko.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ruanko.contactbook.R;
import com.ruanko.model.User;

public class UserAdapter extends BaseAdapter {

	private List<User> users;
	private int resource;//����Ŀ
	private LayoutInflater inflater; //����xml��Ӧ��view����
	

	public UserAdapter(Context context,List<User> users,int resource) {
		this.users = users;
		this.resource = resource;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return users.size();//��������
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
			convertView = inflater.inflate(resource, null);//������Ŀ
			nameView = (TextView)convertView.findViewById(R.id.name);
			
			ViewCache cache = new ViewCache();
			cache.nameView = nameView;
			convertView.setTag(cache);
		}else{
			ViewCache cache = (ViewCache)convertView.getTag();
			nameView = cache.nameView;
		}
		User user = users.get(position);
		nameView.setText(user.getName());//������
		
		return convertView;
	}

	private final class ViewCache{
		public TextView nameView;
	}
}
