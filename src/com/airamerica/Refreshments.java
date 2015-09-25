package com.airamerica;

public class Refreshments extends Product {
	
	private String name;
	private double cost;

	public Refreshments(String productCode, String productType, String name, double cost) {
		super(productCode, productType);
		this.name = name;
		this.cost = cost;
	}

}
