package com.wa.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="app_user")
public class User {

	@Id
	@GeneratedValue
	@Column(name="user_id")
	long userId;
	@Column(name="username")
	String username;
	@Column(name="email")
	String email;
	@Column(name="password	")
	@JsonIgnore
	String pasaword;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	List<WeatherPreference> prefList;
	
	public User() {}

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

	public List<WeatherPreference> getPrefList() {
		return prefList;
	}
	
	public void setPrefList(List<WeatherPreference> prefList) {
		this.prefList = prefList;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", email=" + email + ", pasaword=" + pasaword
				+ ", pref=" + prefList + "]";
	}
}
