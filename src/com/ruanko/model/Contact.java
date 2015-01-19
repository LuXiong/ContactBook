package com.ruanko.model;

import java.util.ArrayList;
import java.io.Serializable;

import com.j256.ormlite.table.DatabaseTable;
import com.ruanko.common.ContactItemInterface;
import com.ruanko.pinyin.PinYin;

public class Contact implements ContactItemInterface, Serializable {

	private String contactId;

	private String name;

	private ArrayList<Phone> phones;

	private ArrayList<Im> ims;

	private String avatar;

	private String addr;

	private String email;

	private String groupName;

	private DataId ids;

	private String namePinyin;

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
		this.namePinyin = (new PinYin()).getPinyin(name);
		if (this.namePinyin == null) {
			this.namePinyin = name;
		}
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
			String avatar, String addr, String email, String groupName) {
		this.contactId = contactId;
		this.name = name;
		this.phones = phones;
		this.avatar = avatar;
		this.addr = addr;
		this.email = email;
		this.groupName = groupName;
		this.ids = new DataId();
	}

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", name=" + name
				+ ", phones=" + phones + ", ims=" + ims + ", avatar=" + avatar
				+ ", addr=" + addr + ", emails=" + email + ", groupName="
				+ groupName + "]";
	}

	public class DataId implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -3620391410428335349L;

		private String name;
		private String avatar;
		private String group;
		private String addr;
		private ArrayList<String> phones;
		private String email;
		private ArrayList<String> ims;

		public DataId() {
			phones = new ArrayList<String>();
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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public ArrayList<String> getIms() {
			return ims;
		}

		public void setIms(ArrayList<String> ims) {
			this.ims = ims;
		}

	}

	@Override
	public String getOrderingItem() {
		return namePinyin;
	}

	@Override
	public String getDisplayItem() {
		return name;
	}
}
