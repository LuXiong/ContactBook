package com.ruanko.bussiness;

import java.util.ArrayList;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.Data;
import android.widget.Toast;

import com.ruanko.common.ImConstant;
import com.ruanko.common.PhoneConstant;
import com.ruanko.model.Contact;
import com.ruanko.model.Im;
import com.ruanko.model.Phone;

public class ContactBussiness {

	public void deleteContact(Context context, Contact contact) {
		ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
		ContentProviderOperation.Builder opRawContact = ContentProviderOperation
				.newDelete(ContactsContract.RawContacts.CONTENT_URI)
				.withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
				.withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
				.withSelection(ContactsContract.RawContacts._ID,
						new String[] { contact.getContactId() });
		ops.add(opRawContact.build());
		try {
			context.getContentResolver().applyBatch(ContactsContract.AUTHORITY,
					ops);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createNewContact(Context context, Contact contact) {
		ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
		ContentProviderOperation.Builder opRawContact = ContentProviderOperation
				.newInsert(ContactsContract.RawContacts.CONTENT_URI)
				.withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
				.withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null);
		ops.add(opRawContact.build());

		ArrayList<Phone> phones = contact.getPhones();
		if (phones != null) {
			for (Phone phone : phones) {
				ContentProviderOperation.Builder opPhone = ContentProviderOperation
						.newInsert(ContactsContract.Data.CONTENT_URI)
						.withValueBackReference(
								ContactsContract.Data.RAW_CONTACT_ID, 0)
						.withValue(
								ContactsContract.Data.MIMETYPE,
								ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
						.withValue(
								ContactsContract.CommonDataKinds.Phone.NUMBER,
								phone.getNumber())
						.withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
								phone.getType());
				if (PhoneConstant.getType(phone) == CommonDataKinds.Phone.TYPE_OTHER) {
					opPhone.withValue(
							ContactsContract.CommonDataKinds.Phone.LABEL,
							phone.getType());
				}
				ops.add(opPhone.build());
			}
		}

		ArrayList<Im> ims = contact.getIms();
		if (ims != null) {
			for (Im im : ims) {
				ContentProviderOperation.Builder opIm = ContentProviderOperation
						.newInsert(ContactsContract.Data.CONTENT_URI)
						.withValueBackReference(
								ContactsContract.Data.RAW_CONTACT_ID, 0)
						.withValue(
								ContactsContract.Data.MIMETYPE,
								ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE)
						.withValue(ContactsContract.CommonDataKinds.Im.DATA,
								im.getAccount())
						.withValue(ContactsContract.CommonDataKinds.Im.TYPE,
								ContactsContract.CommonDataKinds.Im.TYPE_HOME);
				if (ImConstant.getType(im) == CommonDataKinds.Im.PROTOCOL_CUSTOM) {
					opIm.withValue(
							ContactsContract.CommonDataKinds.Im.CUSTOM_PROTOCOL,
							im.getType());
				}
				ops.add(opIm.build());
			}
		}

		// ContentProviderOperation.Builder opGroupName =
		// ContentProviderOperation
		// .newInsert(ContactsContract.Data.CONTENT_URI)
		// .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
		// .withValue(
		// ContactsContract.Data.MIMETYPE,
		// ContactsContract.CommonDataKinds.GroupMembership.CONTENT_ITEM_TYPE)
		// .withValue(
		// ContactsContract.CommonDataKinds.GroupMembership.DISPLAY_NAME,
		// contact.getName());
		if (contact.getEmail() != null) {
			ContentProviderOperation.Builder opEmail = ContentProviderOperation
					.newInsert(ContactsContract.Data.CONTENT_URI)
					.withValueBackReference(
							ContactsContract.Data.RAW_CONTACT_ID, 0)
					.withValue(
							ContactsContract.Data.MIMETYPE,
							ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
					.withValue(ContactsContract.CommonDataKinds.Email.ADDRESS,
							contact.getEmail());
			ops.add(opEmail.build());

		}
		if (contact.getAddr() != null) {
			ContentProviderOperation.Builder opAddr = ContentProviderOperation
					.newInsert(ContactsContract.Data.CONTENT_URI)
					.withValueBackReference(
							ContactsContract.Data.RAW_CONTACT_ID, 0)
					.withValue(
							ContactsContract.Data.MIMETYPE,
							ContactsContract.CommonDataKinds.SipAddress.CONTENT_ITEM_TYPE)
					.withValue(
							ContactsContract.CommonDataKinds.SipAddress.SIP_ADDRESS,
							contact.getAddr());

			ops.add(opAddr.build());
		}

		if (contact.getName() == null) {
			Toast.makeText(context, "姓名不能为空", Toast.LENGTH_LONG).show();
		} else {
			ContentProviderOperation.Builder opName = ContentProviderOperation
					.newInsert(ContactsContract.Data.CONTENT_URI)
					.withValueBackReference(
							ContactsContract.Data.RAW_CONTACT_ID, 0)
					.withValue(
							ContactsContract.Data.MIMETYPE,
							ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
					.withValue(
							ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
							contact.getName());
			opName.withYieldAllowed(true);
			ops.add(opName.build());
		}

		try {

			context.getContentResolver().applyBatch(ContactsContract.AUTHORITY,
					ops);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public void updateContact(Context context, Contact preContact, Contact
	// contact) {
	// ArrayList<ContentProviderOperation> ops = new
	// ArrayList<ContentProviderOperation>();
	// ContentProviderOperation.Builder opName = ContentProviderOperation
	// .newUpdate(ContactsContract.Data.CONTENT_URI)
	// .withSelection(ContactsContract.Data._ID,
	// new String[] { contact.getDataIds().getName() })
	// .withValue(
	// ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
	// contact.getName());
	// ops.add(opName.build());
	// ArrayList<Phone> phones = contact.getPhones();
	// for (int i = 0; i < phones.size(); i++) {
	// Phone phone = phones.get(i);
	// ContentProviderOperation.Builder opPhone = ContentProviderOperation
	// .newUpdate(ContactsContract.Data.CONTENT_URI)
	// .withSelection(
	// ContactsContract.Data._ID,
	// new String[] { contact.getDataIds().getPhones()
	// .get(i) })
	// .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER,
	// phone.getNumber())
	// .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
	// phone.getType());
	// if (PhoneConstant.getType(phone) == CommonDataKinds.Phone.TYPE_OTHER) {
	// opPhone.withValue(ContactsContract.CommonDataKinds.Phone.LABEL,
	// phone.getType());
	// }
	// ops.add(opPhone.build());
	// }
	// ArrayList<Im> ims = contact.getIms();
	// for (int i = 0; i < ims.size(); i++) {
	// Im im = ims.get(i);
	// ContentProviderOperation.Builder opIm = ContentProviderOperation
	// .newUpdate(ContactsContract.Data.CONTENT_URI)
	// .withSelection(
	// ContactsContract.Data._ID,
	// new String[] { contact.getDataIds().getIms().get(i) })
	// .withValue(ContactsContract.CommonDataKinds.Im.DATA,
	// im.getAccount())
	// .withValue(ContactsContract.CommonDataKinds.Im.TYPE,
	// ContactsContract.CommonDataKinds.Im.TYPE_HOME);
	// if (ImConstant.getType(im) == CommonDataKinds.Im.PROTOCOL_CUSTOM) {
	// opIm.withValue(
	// ContactsContract.CommonDataKinds.Im.CUSTOM_PROTOCOL,
	// im.getType());
	// }
	// ops.add(opIm.build());
	// }

	// ContentProviderOperation.Builder opGroupName =
	// ContentProviderOperation
	// .newUpdate(ContactsContract.Data.CONTENT_URI)
	// .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
	// .withValue(
	// ContactsContract.Data.MIMETYPE,
	// ContactsContract.CommonDataKinds.GroupMembership.CONTENT_ITEM_TYPE)
	// .withValue(
	// ContactsContract.CommonDataKinds.GroupMembership.DISPLAY_NAME,
	// contact.getName());
	// ArrayList<String> emails = contact.getEmails();
	// for (int i = 0; i < emails.size(); i++) {
	// String email = emails.get(i);
	// ContentProviderOperation.Builder opEmail = ContentProviderOperation
	// .newUpdate(ContactsContract.Data.CONTENT_URI)
	// .withSelection(
	// ContactsContract.Data._ID,
	// new String[] { contact.getDataIds().getEmails()
	// .get(i) })
	// .withValue(ContactsContract.CommonDataKinds.Email.ADDRESS,
	// email);
	// ops.add(opEmail.build());
	// }
	// ContentProviderOperation.Builder opAddr = ContentProviderOperation
	// .newUpdate(ContactsContract.Data.CONTENT_URI)
	// .withSelection(ContactsContract.Data._ID,
	// new String[] { contact.getDataIds().getAddr() })
	// .withValue(
	// ContactsContract.CommonDataKinds.SipAddress.SIP_ADDRESS,
	// contact.getAddr());
	// opAddr.withYieldAllowed(true);
	// ops.add(opAddr.build());
	// try {
	//
	// context.getContentResolver().applyBatch(ContactsContract.AUTHORITY,
	// ops);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

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
			Uri uri = ContentUris.withAppendedId(
					ContactsContract.Contacts.CONTENT_URI, Long.valueOf(id));
			contact.setAvatar(uri.getPath());
			Cursor contactInfoCursor = contentResolver.query(
					android.provider.ContactsContract.Data.CONTENT_URI,
					new String[] { Data.CONTACT_ID, Data._ID, Data.MIMETYPE,
							Data.DATA1, Data.DATA2, Data.DATA3, Data.DATA5,
							Data.DATA6 }, Data.CONTACT_ID + "=" + id, null,
					null);
			while (contactInfoCursor.moveToNext()) {
				mimetype = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data.MIMETYPE));
				String dataId = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data._ID));
				String s1 = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data.DATA1));
				String s2 = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data.DATA2));
				String s3 = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data.DATA3));

				String s5 = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data.DATA5));
				String s6 = contactInfoCursor
						.getString(contactInfoCursor
								.getColumnIndex(android.provider.ContactsContract.Data.DATA6));

				if (mimetype
						.equals(ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)) {
					contact.setName(s1);
					contact.getDataIds().setName(dataId);
				} else if (mimetype
						.equals(ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE)) {
					Im im = new Im();
					im.setType(getImLabel(Integer.parseInt(s5), s6));
					im.setAccount(s1);
					ims.add(im);
					contact.getDataIds().getIms().add(dataId);
				} else if (mimetype
						.equals(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)) {
					contact.setEmail(s1);
					contact.getDataIds().setEmail(dataId);
				} else if (mimetype
						.equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)) {
					Phone phone = new Phone();
					phone.setNumber(s1);
					phone.setType(getPhoneLable(Integer.parseInt(s2), s3));
					phones.add(phone);
					contact.getDataIds().getPhones().add(dataId);
				} else if (mimetype
						.equals(ContactsContract.CommonDataKinds.GroupMembership.CONTENT_ITEM_TYPE)) {
					contact.setGroupName(s1);
					contact.getDataIds().setGroup(dataId);
				} else if (mimetype
						.equals(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)) {
					contact.setAddr(s1);
					contact.getDataIds().setAddr(dataId);
				}
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
