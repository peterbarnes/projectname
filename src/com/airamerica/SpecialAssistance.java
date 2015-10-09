package com.airamerica;

public class SpecialAssistance extends Product {
	
	private String typeOfService;
	private Person person;

	public SpecialAssistance(String productCode, String productType, String typeOfService) {
		super(productCode, productType);
		this.typeOfService = typeOfService;
	}

	public String getTypeOfService() {
		return typeOfService;
	}

	public void setTypeOfService(String typeOfService) {
		this.typeOfService = typeOfService;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	

}
