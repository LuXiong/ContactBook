package com.ruanko.common;

import android.provider.ContactsContract;

import com.ruanko.model.Phone;

public class PhoneConstant {
	final static public String TYPE_MOBILE = "�ƶ�";
	final static public String TYPE_HOME = "סլ";
	final static public String TYPE_WORK = "����";
	final static public String TYPE_OTHER= "other";
	
	public static int getType(Phone phone){
		int type = 0;
		if(phone.getType().equals(TYPE_HOME)){
			type = ContactsContract.CommonDataKinds.Phone.TYPE_HOME;
		}else if(phone.getType().equals(TYPE_MOBILE)){
			type = ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE;
		}else if(phone.getType().equals(TYPE_WORK)){
			type = ContactsContract.CommonDataKinds.Phone.TYPE_WORK;
		}else if(phone.getType().equals(TYPE_OTHER)){
			type = ContactsContract.CommonDataKinds.Phone.TYPE_OTHER;
		}
		return type;
	}

}
