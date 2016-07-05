package com.ld;

public class PhoneNumber {
	String phoneNo;

	public PhoneNumber(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public String toString() {
		return " [phoneNo=" + phoneNo + "] ";
	}

}
