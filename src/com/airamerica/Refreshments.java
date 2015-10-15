package com.airamerica;

public class Refreshments extends Product {
	
	private String name;
	private double cost;
	private String noOfRefreshments;

	public Refreshments(String productCode, String productType, String name, double cost) {
		super(productCode, productType);
		this.name = name;
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getNoOfRefreshments() {
		return noOfRefreshments;
	}

	public void setNoOfRefreshments(String noOfRefreshments) {
		this.noOfRefreshments = noOfRefreshments;
	}
	
	public double calculateFee(String type){
		double fee = this.cost;
		
		if(type.equals("G")){
			fee *= 1.04;
		} else if(type.equals("V")){
			fee *= .88;
			fee *= 1.04;
		}
		
		return cost;
	}

}
