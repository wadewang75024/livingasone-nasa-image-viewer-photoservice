package com.livingasone.nasaimageviewer.photoservice.data;

import com.google.gson.annotations.SerializedName;

public class Camera {
	public String id;
	public String name;
	@SerializedName("full_name")
	public String fullName;
	
	public String getId() {
		return id;
	}
	public void setId(final String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(final String fullName) {
		this.fullName = fullName;
	}
}
