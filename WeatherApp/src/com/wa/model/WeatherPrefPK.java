package com.wa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class WeatherPrefPK implements Serializable {
	
	@Column(name="user_id")
    protected long userId;
	
	@Column(name="zip_code")
    protected String zipcode;

    public WeatherPrefPK() {}

    public WeatherPrefPK(long userId, String zipcode) {
        this.userId = userId;
        this.zipcode = zipcode;
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
	
	@Override
	public String toString() {
		return "WeatherPrefPK [userId=" + userId + ", zipcode=" + zipcode + "]";
	}

	

    
}
