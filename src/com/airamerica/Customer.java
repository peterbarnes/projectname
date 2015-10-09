package com.airamerica;

public class Customer {

	private String customerCode;
	private String type;
	
	/* Note how Person has been used as 
	 * primary contact(Composition Relationship) */ 
	private Person primaryContact;
	private String customerName;
	private String airlineMiles;
	
	public Customer(String customerCode, String type, Person primaryContact, String customerName, String airlineMiles) {
		super();
		this.customerCode = customerCode;
		this.type = type;
		this.primaryContact = primaryContact;
		this.customerName = customerName;
		this.airlineMiles = airlineMiles;
	}
	
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Person getPrimaryContact() {
		return primaryContact;
	}
	public void setPrimaryContact(Person primaryContact) {
		this.primaryContact = primaryContact;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAirlineMiles() {
		return airlineMiles;
	}
	public void setAirlineMiles(String airlineMiles) {
		this.airlineMiles = airlineMiles;
	}

	//TODO: Add additional methods if needed */
}
