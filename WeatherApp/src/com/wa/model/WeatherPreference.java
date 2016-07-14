package com.wa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="weather_preferences")
public class WeatherPreference {
	
	@Column(name="user_id")
	long userId;
	@Column(name="zip_code")
	String zipcode;
	@Column(name="is_local")
	boolean isLocal;
	@Column(name="name")
	String name;
	
	public WeatherPreference(long userId, String zipcode, boolean isLocal, String name) {
		super();
		this.userId = userId;
		this.zipcode = zipcode;
		this.isLocal = isLocal;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public boolean isLocal() {
		return isLocal;
	}
	public void setLocal(boolean isLocal) {
		this.isLocal = isLocal;
	}
	
}
