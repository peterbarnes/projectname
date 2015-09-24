package com.airamerica;
/*
/* A partial implementation representing a 
 * Person */
import java.util.ArrayList;
import java.util.List;

public class Person {
	
	private String personCode;
	
	/* Note how Address has been used (Composition Relationship) */ 
	private Address address;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	
	private List<String> emails;
	
	// TODO: Add appropriate constructor(s)
	public Person(String personCode, Address address, String firstName, String lastName) {
		this.personCode = personCode;
		this.address = address;
		this.emails = new ArrayList<String>();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
		
	public String getPersonCode() {
		return personCode;
	}



	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public List<String> getEmails() {
		return emails;
	}



	public void setEmails(List<String> emails) {
		this.emails = emails;
	}



	// TODO: Add additional methods here
	public void addEmail(String email) {
		this.emails.add(email);
	}
	
}
