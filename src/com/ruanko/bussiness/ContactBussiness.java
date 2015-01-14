package com.ruanko.bussiness;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Data;
import android.util.Log;

import com.ruanko.model.Contact;
import com.ruanko.model.Phone;

public class ContactBussiness {
	
	public void fetchContacts(Context context){
		A
	}
	public void fetchContactInformationV2(Context context) {

		ArrayList<Contact> contacts = new ArrayList<Contact>();
		for (int i = 0; i < 30; i++) {
			contacts.add(new Contact(String.valueOf(i), "zhouyezi" + i,
					(new ArrayList<Phone>()), "", "addr" + i, (new ArrayList<String>()),
					"group" + i));
		}
		String id;
		String mimetype;
		ContentResolver contentResolver = context.getContentResolver();
		// ֻ��Ҫ��Contacts�л�ȡID�������Ķ����Բ�Ҫ��ͨ���鿴���������SQL��䣬���Կ������ڶ�������
		// ���ó�null��Ĭ�Ϸ��ص��зǳ��࣬��һ����Դ�˷ѡ�
		Cursor cursor = contentResolver
				.query(android.provider.ContactsContract.Contacts.CONTENT_URI,
						new String[] { android.provider.ContactsContract.Contacts._ID },
						null, null, null);
		while (cursor.moveToNext()) {
			id = cursor
					.getString(cursor
							.getColumnIndex(android.provider.ContactsContract.Contacts._ID));

			// ��һ��Cursor��ȡ���е���Ϣ
			Cursor contactInfoCursor = contentResolver.query(
					android.provider.ContactsContract.Data.CONTENT_URI,
					new String[] { Data.CONTACT_ID, Data.MIMETYPE, Data.DATA1,
							Data.DATA2, Data.DATA3, Data.DATA4, Data.DATA5,
							Data.DATA6, Data.DATA7, Data.DATA8, Data.DATA9,
							Data.DATA10, Data.DATA11, Data.DATA12, Data.DATA13,
							Data.DATA14, Data.DATA15 }, Data.CONTACT_ID + "="
							+ id, null, null);
			while (contactInfoCursor.moveToNext()) {
				mimetype = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data.MIMETYPE));
				StringBuffer value = new StringBuffer();

				String s1 = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data.DATA1));
				value.append(android.provider.ContactsContract.Data.DATA1 + ":"
						+ s1);
				String s2 = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data.DATA2));
				value.append(android.provider.ContactsContract.Data.DATA2 + ":"
						+ s2);
				String s3 = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data.DATA3));
				value.append(android.provider.ContactsContract.Data.DATA3 + ":"
						+ s3);
				String s4 = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data.DATA4));
				value.append(android.provider.ContactsContract.Data.DATA4 + ":"
						+ s4);
				String s5 = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data.DATA5));
				value.append(android.provider.ContactsContract.Data.DATA5 + ":"
						+ s5);
				String s6 = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data.DATA6));
				value.append(android.provider.ContactsContract.Data.DATA6 + ":"
						+ s6);
				String s7 = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data.DATA7));
				value.append(android.provider.ContactsContract.Data.DATA7 + ":"
						+ s7);
				String s8 = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data.DATA8));
				value.append(android.provider.ContactsContract.Data.DATA8 + ":"
						+ s8);
				String s9 = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data.DATA9));
				value.append(android.provider.ContactsContract.Data.DATA9 + ":"
						+ s9);
				String s10 = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data.DATA10));
				value.append(android.provider.ContactsContract.Data.DATA10
						+ ":" + s10);
				String s11 = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data.DATA11));
				value.append(android.provider.ContactsContract.Data.DATA11
						+ ":" + s11);
				String s12 = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data.DATA12));
				value.append(android.provider.ContactsContract.Data.DATA12
						+ ":" + s12);
				String s13 = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data.DATA13));
				value.append(android.provider.ContactsContract.Data.DATA13
						+ ":" + s13);
				String s14 = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data.DATA14));
				value.append(android.provider.ContactsContract.Data.DATA14
						+ ":" + s14);
				String s15 = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data.DATA15));
				value.append(android.provider.ContactsContract.Data.DATA15
						+ ":" + s15);

				if (mimetype
						.equals(ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)) {
					Log.v("xionglu", "����=" + value);
				} else if (mimetype.contains("/im")) {
					Log.v("xionglu", "����(QQ)�˺�=" + value);
				} else if (mimetype.contains("/email")) {
					Log.v("xionglu", "����=" + value);
				} else if (mimetype.contains("/phone")) {
					Log.v("xionglu", "�绰=" + value);
				} else if (mimetype.contains("/postal")) {
					Log.v("xionglu", "�ʱ�=" + value);
				} else if (mimetype.contains("/photo")) {
					Log.v("xionglu", "��Ƭ=" + value);
				} else if (mimetype.contains("/group")) {
					Log.v("xionglu", "��=" + value);
				}
			}
			System.out.println("*********");
			contactInfoCursor.close();
		}
		cursor.close();
	}

}
