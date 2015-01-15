package com.ruanko.model;

import java.util.ArrayList;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "contact")
public class Contact {

	private String contactId;

	private String name;

	private ArrayList<Phone> phones;

	private ArrayList<Im> ims;

	private String avatar;

	private String addr;

	private ArrayList<String> emails;

	private String groupName;

	private DataId ids;

	public Contact() {
		this(null, null, null, null, null, null, null);
	}

	public Contact(String contactId, String name, ArrayList<Phone> phones,
			ArrayList<Im> ims, String avatar, String addr,
			ArrayList<String> emails, String groupName) {
		super();
		this.contactId = contactId;
		this.name = name;
		this.phones = phones;
		this.ims = ims;
		this.avatar = avatar;
		this.addr = addr;
		this.emails = emails;
		this.groupName = groupName;
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

	public ArrayList<Im> getIms() {
		return ims;
	}

	public void setIms(ArrayList<Im> ims) {
		this.ims = ims;
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

	public DataId getDataIds() {
		return this.ids;
	}

	public Contact(String contactId, String name, ArrayList<Phone> phones,
			String avatar, String addr, ArrayList<String> emails,
			String groupName) {
		this.contactId = contactId;
		this.name = name;
		this.phones = phones;
		this.avatar = avatar;
		this.addr = addr;
		this.emails = emails;
		this.groupName = groupName;
		this.ids = new DataId();
	}

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", name=" + name
				+ ", phones=" + phones + ", ims=" + ims + ", avatar=" + avatar
				+ ", addr=" + addr + ", emails=" + emails + ", groupName="
				+ groupName + "]";
	}

	public class DataId {
		private String name;
		private String avatar;
		private String group;
		private String addr;
		private ArrayList<String> phones;
		private ArrayList<String> emails;
		private ArrayList<String> ims;

		public DataId() {
			phones = new ArrayList<String>();
			emails = new ArrayList<String>();
			ims = new ArrayList<String>();
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAvatar() {
			return avatar;
		}

		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}

		public String getGroup() {
			return group;
		}

		public void setGroup(String group) {
			this.group = group;
		}

		public String getAddr() {
			return addr;
		}

		public void setAddr(String addr) {
			this.addr = addr;
		}

		public ArrayList<String> getPhones() {
			return phones;
		}

		public void setPhones(ArrayList<String> phones) {
			this.phones = phones;
		}

		public ArrayList<String> getEmails() {
			return emails;
		}

		public void setEmails(ArrayList<String> emails) {
			this.emails = emails;
		}

		public ArrayList<String> getIms() {
			return ims;
		}

		public void setIms(ArrayList<String> ims) {
			this.ims = ims;
		}

	}
}
