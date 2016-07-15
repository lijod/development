package com.wa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class WeatherPrefPK implements Serializable {
	
	@Column(name="user_id")
    protected long userId;
	
	@Column(name="zipcode")
    protected String zipcode;

    public WeatherPrefPK() {}

    public WeatherPrefPK(long userId, String zipcode) {
        this.userId = userId;
        this.zipcode = zipcode;
    }
    // equals, hashCode
}
