package com.airamerica;

/* A partial implementation of address of a particular
 * Location */
public class Address {
	
	private String street;
	private String city;
	private String state;
	private String country;
	private String zip;
//TODO: Add more fields as needed

	/* Constructor - Generated using Eclipse Menu 
	 * (Source-> Generate Constructor using fields) */

	public Address(String street, String city, String state, String country, String zip) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}