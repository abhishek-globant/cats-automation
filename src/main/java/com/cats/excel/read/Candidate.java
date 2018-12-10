package com.cats.excel.read;


public class Candidate {
	

	private String firstName;
	private String lastName;
	private String emailID;
	private Long phoneNumber;
	private String location;
	private String position;


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public void setPhoneNumber(Double phoneNumber) {
		this.phoneNumber = (new Double(phoneNumber)).longValue();
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailID() {
		return emailID;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public String getLocation() {
		return location;
	}

	public String getPosition() {
		return position;
	}
}
