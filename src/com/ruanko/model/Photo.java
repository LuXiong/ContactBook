package com.ruanko.model;

import java.util.Arrays;

public class Photo {

	private String id;
	private byte[] photo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Photo [id=" + id + ", photo=" + Arrays.toString(photo) + "]";
	}

	public Photo(String id, byte[] photo) {
		super();
		this.id = id;
		this.photo = photo;
	}

	public Photo() {

	}
}
