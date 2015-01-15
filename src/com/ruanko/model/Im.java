package com.ruanko.model;

public class Im {
	private String account;
	private String type;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Im [account=" + account + ", type=" + type + "]";
	}

	public Im() {
		this(null, null);
	}

	public Im(String account, String type) {
		super();
		this.account = account;
		this.type = type;
	}
}
