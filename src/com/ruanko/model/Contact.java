package com.ruanko.model;

import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts.Photo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "contact")
public class Contact {
	private final static String ID = "id";
	private final static String NAME = "name";
	private final static String PHONE = "phone";
	private final static String AVATAR = "avatar";
	private final static String ADDR = "address";
	private final static String EMAIL = "email";
	
	public static final String[] PHONES_PROJECTION = new String[] {
			Phone.DISPLAY_NAME, Phone.NUMBER, Photo.PHOTO_URI };

	public static final int PHONES_DISPLAY_NAME_INDEX = 0;
	public static final int PHONES_NUMBER_INDEX = 1;
	public static final int PHONES_PHOTO_URI_INDEX = 2;

	@DatabaseField(generatedId = true, columnName = ID, canBeNull = false)
	private int id;
	@DatabaseField(columnName = NAME, canBeNull = false)
	private String name;
	@DatabaseField(columnName = PHONE, canBeNull = true)
	private String phone;
	@DatabaseField(columnName = AVATAR, canBeNull = true)
	private String avatar;
	@DatabaseField(columnName = ADDR, canBeNull = true)
	private String addr;
	@DatabaseField(columnName = EMAIL, canBeNull = true)
	private String email;

	public Contact() {

	}

	public Contact(String name, String phone) {
		this(name, phone, null, null,null);
	}

	public Contact(String name, String phone, String avatar, String addr,String email) {
		this.name = name;
		this.phone = phone;
		this.avatar = avatar;
		this.addr = addr;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", phone=" + phone
				+ ", avatar=" + avatar + ", address=" + addr + "]";
	}

}
