package com.airamerica;

public class Insurance extends Product {
	
	private String name;
	private String ageClass;
	private double costPerMile;
	
	public Insurance(String productCode, String productType, String name,
			String ageClass, double costPerMile) {
		super(productCode, productType);
		this.name = name;
		this.ageClass = ageClass;
		this.costPerMile = costPerMile;
	}

}
