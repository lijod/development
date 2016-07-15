package com.wa.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="weather_preferences")
public class WeatherPreference {
	
	@EmbeddedId
	WeatherPrefPK weatherPrefPK;
	
	@Column(name="is_local")
	boolean isLocal;
	
	@Column(name="name")
	String name;
	
	public WeatherPreference(WeatherPrefPK weatherPrefPK, boolean isLocal, String name) {
		super();
		this.isLocal = isLocal;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isLocal() {
		return isLocal;
	}
	
	public void setLocal(boolean isLocal) {
		this.isLocal = isLocal;
	}
	
}
