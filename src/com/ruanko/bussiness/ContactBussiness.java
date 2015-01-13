package com.ruanko.bussiness;

import java.sql.SQLException;
import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts.Photo;

import com.j256.ormlite.dao.Dao;
import com.ruanko.database.DataBaseHelper;
import com.ruanko.database.DataBaseHelperManager;
import com.ruanko.listener.DataBaseListener;
import com.ruanko.model.Contact;

public class ContactBussiness {

	public void loadLocalContacts(Context context, DataBaseListener listener) {
		listener.onStart();
		try {
			DataBaseHelper helper = DataBaseHelperManager.getInstance()
					.getHelper();
			ContentResolver resolver = context.getContentResolver();
			Cursor phoneCursor = resolver.query(Phone.CONTENT_URI,
					Contact.PHONES_PROJECTION, null, null, null);

			if (phoneCursor != null) {
				while (phoneCursor.moveToNext()) {
					String name = phoneCursor
							.getString(Contact.PHONES_DISPLAY_NAME_INDEX);
					String phone = phoneCursor
							.getString(Contact.PHONES_NUMBER_INDEX);
					String avatar = phoneCursor
							.getString(Contact.PHONES_PHOTO_URI_INDEX);
					Contact contact = new Contact(name, phone, avatar, null,null);

					Dao<Contact, String> contactDao = helper
							.getDao(Contact.class);
					contactDao.createOrUpdate(contact);
					listener.onSuccess();

				}
			} else {
				listener.onFailure("can not get resolver");
			}
			helper.close();
		} catch (SQLException e) {
			e.printStackTrace();
			listener.onFailure("sql exception");
		}
		listener.onFinish();

	}
}
