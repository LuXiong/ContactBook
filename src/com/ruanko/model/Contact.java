package com.ruanko.model;

import java.util.ArrayList;

import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts.Photo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "contact")
public class Contact {

	private String contactId;

	private String name;

	private ArrayList<Phone> phones;

	private String avatar;

	private String addr;

	private ArrayList<String> emails;

	private String groupName;

	public Contact() {
		this(null, null, null, null, null, null, null);
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Phone> getPhones() {
		return phones;
	}

	public void setPhones(ArrayList<Phone> phones) {
		this.phones = phones;
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

	public ArrayList<String> getEmails() {
		return emails;
	}

	public void setEmails(ArrayList<String> emails) {
		this.emails = emails;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Contact(String contactId, String name, ArrayList<Phone> phones,
			String avatar, String addr, ArrayList<String> emails,
			String groupName) {
		super();
		this.contactId = contactId;
		this.name = name;
		this.phones = phones;
		this.avatar = avatar;
		this.addr = addr;
		this.emails = emails;
		this.groupName = groupName;
	}

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", name=" + name
				+ ", phones=" + phones + ", avatar=" + avatar + ", addr="
				+ addr + ", emails=" + emails + ", groupName=" + groupName
				+ "]";
	}

}
