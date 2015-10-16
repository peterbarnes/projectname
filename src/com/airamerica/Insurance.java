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
	
	public Ticket createTicket(Product product){
		
		Ticket newTicket = null;
		newTicket = (Ticket) product;
		
		return newTicket;
	}
	
	public double calculateSubtotal(double miles){
	
		
		double fee = this.costPerMile * miles;
		
		return fee;
	}
	
	public double calculateTax(String type, double miles){
		
		double tax = .04;
		double subtotal = calculateSubtotal(miles);
		
		double totalTax = subtotal * tax;
		
		return totalTax;
	}

}
