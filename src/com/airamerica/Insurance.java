package com.airamerica;

import com.airamerica.utils.*;

public class Insurance extends Product {
	
	private String name;
	private String ageClass;
	private double costPerMile;
	private String noOfInsurance;
	private Product ticket;
	
	public Insurance(String productCode, String productType, String name,
			String ageClass, double costPerMile) {
		super(productCode, productType);
		this.name = name;
		this.ageClass = ageClass;
		this.costPerMile = costPerMile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAgeClass() {
		return ageClass;
	}

	public void setAgeClass(String ageClass) {
		this.ageClass = ageClass;
	}

	public double getCostPerMile() {
		return costPerMile;
	}

	public void setCostPerMile(double costPerMile) {
		this.costPerMile = costPerMile;
	}

	public String getNoOfInsurance() {
		return noOfInsurance;
	}

	public void setNoOfInsurance(String noOfInsurance) {
		this.noOfInsurance = noOfInsurance;
	}

	public Product getProduct() {
		return ticket;
	}

	public void setProduct(Product product) {
		this.ticket = product;
	}
	
	public double calculateFee(String type){
		
		Ticket newTicket = (Ticket) ticket;
		
		double fee = this.costPerMile * newTicket.getMiles();
		
		if(type.equals("G")){
			fee *= 1.04;
		} else if(type.equals("C")){
			fee *= .88;
			fee *= 1.04;
		}
		
		return fee;
	}

}
