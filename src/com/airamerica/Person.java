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
	public Person(String personCode, Address address, String firstName, String lastName, String phoneNumber) {
		this.personCode = personCode;
		this.address = address;
		this.emails = new ArrayList<String>();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	// TODO: Add Getters and setters as appropriate
	public Address getAddress() {
		return this.address;
	}
	
	public void setEmails(List<String> emails)
	{
		this.emails = emails;
	}
		
	// TODO: Add additional methods here
	public void addEmail(String email) {
		this.emails.add(email);
	}
	
}
