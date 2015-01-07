package com.ruanko.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "user")
public class User {
	private final static String ID = "id";
	private final static String NAME = "name";
	private final static String PHONE = "phone";
	private final static String ADDR = "address";

	@DatabaseField(generatedId = true, columnName = ID)
	private int id;
	@DatabaseField(columnName = NAME)
	private String name;
	@DatabaseField(columnName = PHONE)
	private String phone;
	@DatabaseField(columnName = ADDR)
	private String addr;

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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phone=" + phone
				+ ", address=" + addr + "]";
	}

}
