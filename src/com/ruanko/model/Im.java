package com.ruanko.model;

import java.io.Serializable;

import com.ruanko.common.NameTypeInterface;

public class Im implements Serializable, NameTypeInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 945594020709374261L;
	private String account;
	private String type;
	private String id;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	@Override
	public String getModelName() {
		return this.account;
	}

	@Override
	public String getModelType() {
		return this.type;
	}
}
