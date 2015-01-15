package com.ruanko.bussiness;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.Data;

import com.ruanko.common.ImConstant;
import com.ruanko.common.PhoneConstant;
import com.ruanko.model.Contact;
import com.ruanko.model.Im;
import com.ruanko.model.Phone;

public class ContactBussiness {

	public ArrayList<Contact> fetchContactInformation(Context context) {
		String id;
		String mimetype;
		ContentResolver contentResolver = context.getContentResolver();
		Cursor cursor = contentResolver
				.query(android.provider.ContactsContract.Contacts.CONTENT_URI,
						new String[] { android.provider.ContactsContract.Contacts._ID },
						null, null, null);
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		while (cursor.moveToNext()) {
			id = cursor
					.getString(cursor
							.getColumnIndex(android.provider.ContactsContract.Contacts._ID));
			Contact contact = new Contact();
			contact.setContactId(id);
			ArrayList<Phone> phones = new ArrayList<Phone>();
			ArrayList<Im> ims = new ArrayList<Im>();
			ArrayList<String> emails = new ArrayList<String>();
			Uri uri = ContentUris.withAppendedId(
					ContactsContract.Contacts.CONTENT_URI, Long.valueOf(id));
			contact.setAvatar(uri.getPath());
			Cursor contactInfoCursor = contentResolver.query(
					android.provider.ContactsContract.Data.CONTENT_URI,
					new String[] { Data.CONTACT_ID, Data.MIMETYPE, Data.DATA1,
							Data.DATA2, Data.DATA3, Data.DATA5, Data.DATA6 },
					Data.CONTACT_ID + "=" + id, null, null);
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

				if (mimetype
						.equals(ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)) {
					contact.setName(s1);
				} else if (mimetype
						.equals(ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE)) {
					Im im = new Im();
					im.setType(getImLabel(Integer.parseInt(s5), s6));
					im.setAccount(s1);
					ims.add(im);
				} else if (mimetype
						.equals(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)) {
					String email = s1;
					emails.add(email);
				} else if (mimetype
						.equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)) {
					Phone phone = new Phone();
					phone.setNumber(s1);
					phone.setType(getPhoneLable(Integer.parseInt(s2), s3));
					phones.add(phone);
				} else if (mimetype
						.equals(ContactsContract.CommonDataKinds.GroupMembership.CONTENT_ITEM_TYPE)) {
					contact.setGroupName(s1);
				} else if (mimetype
						.equals(ContactsContract.CommonDataKinds.SipAddress.CONTENT_ITEM_TYPE)) {
					contact.setAddr(s1);
				}
				contact.setEmails(emails);
				contact.setPhones(phones);
				contact.setIms(ims);
			}

			contactInfoCursor.close();
			contacts.add(contact);
		}
		cursor.close();
		return contacts;
	}

	private String getImLabel(int data, String custom) {
		String result = null;
		switch (data) {
		case CommonDataKinds.Im.PROTOCOL_QQ:
			result = ImConstant.QQ;
			break;
		case CommonDataKinds.Im.PROTOCOL_MSN:
			result = ImConstant.MSN;
			break;
		case CommonDataKinds.Im.PROTOCOL_CUSTOM:
			if (custom != null) {
				result = custom;
			}
			break;
		}
		return result;
	}

	private String getPhoneLable(int data, String custom) {
		String result = null;
		switch (data) {
		case CommonDataKinds.Phone.TYPE_MOBILE:
			result = PhoneConstant.TYPE_MOBILE;
			break;
		case CommonDataKinds.Phone.TYPE_HOME:
			result = PhoneConstant.TYPE_HOME;
			break;
		case CommonDataKinds.Phone.TYPE_WORK:
			result = PhoneConstant.TYPE_WORK;
			break;
		case CommonDataKinds.Phone.TYPE_CUSTOM:
			if (custom != null) {
				result = custom;
			}
			break;
		}
		return result;
	}
}
