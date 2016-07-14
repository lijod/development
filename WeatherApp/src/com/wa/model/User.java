package com.wa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="app_user")
public class User {

	@Id
	@Column(name="user_id")
	long userId;
	@Column(name="username")
	String username;
	@Column(name="email")
	String email;
	@Column(name="password	")
	String pasaword;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	WeatherPreference prefList;
	
	public User(long userId, String username, String email, String pasaword) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.pasaword = pasaword;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasaword() {
		return pasaword;
	}

	public void setPasaword(String pasaword) {
		this.pasaword = pasaword;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", email=" + email + ", pasaword=" + pasaword
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((pasaword == null) ? 0 : pasaword.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (pasaword == null) {
			if (other.pasaword != null)
				return false;
		} else if (!pasaword.equals(other.pasaword))
			return false;
		if (userId != other.userId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
}
