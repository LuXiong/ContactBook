package com.ruanko.common;

import java.util.ArrayList;

import android.provider.ContactsContract;

import com.ruanko.model.Im;

public class ImConstant {
	final static public String QQ = "QQ";
	final static public String MSN = "MSN";
	final static public String CUSTOM = "custom";

	public static int getType(Im im) {
		int type = 0;
		if (im.getType().equals(QQ)) {
			type = ContactsContract.CommonDataKinds.Im.PROTOCOL_QQ;
		} else if (im.getType().equals(MSN)) {
			type = ContactsContract.CommonDataKinds.Im.PROTOCOL_MSN;
		} else if (im.getType().equals(CUSTOM)) {
			type = ContactsContract.CommonDataKinds.Im.PROTOCOL_CUSTOM;
		}
		return type;
	}
	public static ArrayList<String> getTypeArray(){
		ArrayList<String> typeList = new ArrayList<String>();
		typeList.add(QQ);
		typeList.add(MSN);
		return typeList;
	}
 }
