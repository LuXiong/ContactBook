package com.ruanko.model;
import java.io.Serializable;

public class Phone implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5239934130725489288L;

	private String type;

	private String number;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Phone [type=" + type + ", number=" + number + "]";
	}

	public Phone() {
		this(null, null);
	}

	public Phone(String type, String number) {
		this.type = type;
		this.number = number;
	}
}
