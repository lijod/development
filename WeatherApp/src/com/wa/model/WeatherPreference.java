package com.wa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="weather_preferences")
public class WeatherPreference implements Serializable{
	
	@EmbeddedId
	WeatherPrefPK weatherPrefPK;
	
	@Column(name="is_local")
	boolean isLocal;
	
	@Column(name="name")
	String name;
	
	public WeatherPreference(WeatherPrefPK weatherPrefPK, boolean isLocal, String name) {
		this.weatherPrefPK = weatherPrefPK;
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

	public WeatherPrefPK getWeatherPrefPK() {
		return weatherPrefPK;
	}

	public void setWeatherPrefPK(WeatherPrefPK weatherPrefPK) {
		this.weatherPrefPK = weatherPrefPK;
	}

	@Override
	public String toString() {
		return "WeatherPreference [weatherPrefPK=" + weatherPrefPK + ", isLocal=" + isLocal + ", name=" + name + "]";
	}
	
	
	
}
