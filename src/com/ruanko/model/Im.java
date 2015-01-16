package com.ruanko.model;
import java.io.Serializable;

public class Im implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 945594020709374261L;
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
