package com.airamerica;

public class Product {

	private String productCode;
	private String productType;
	private String travelDate;
	private String noOfPass;
	private String seat;
	private String personCode;
	private String identityNumber;
	private String age;
	private String nationality;
	private String ticketNote;
	private String numberOfCheckedBaggage;
	
	
	public Product(String productCode, String productType) {
		super();
		this.productCode = productCode;
		this.productType = productType;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}

	public String getNoOfPass() {
		return noOfPass;
	}

	public void setNoOfPass(String noOfPass) {
		this.noOfPass = noOfPass;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getTicketNote() {
		return ticketNote;
	}

	public void setTicketNote(String ticketNote) {
		this.ticketNote = ticketNote;
	}
		
}
