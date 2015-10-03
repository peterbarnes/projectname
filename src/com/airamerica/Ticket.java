package com.airamerica;

public class Ticket extends Product {
	
	private Airport depAirportCode;
	private Airport arrAirportCode;
	private String depTime;
	private String arrTime;
	private String flightNo;
	private String flightClass;
	private String airCraftType;
	
	public Ticket(String productCode, 
				  String productType,
				  Airport depAirportCode, 
				  Airport arrAirportCode, 
				  String depTime,
				  String arrTime, 
				  String flightNo, 
				  String flightClass,
				  String airCraftType) {
		super(productCode, productType);
		this.depAirportCode = depAirportCode;
		this.arrAirportCode = arrAirportCode;
		this.depTime = depTime;
		this.arrTime = arrTime;
		this.flightNo = flightNo;
		this.flightClass = flightClass;
		this.airCraftType = airCraftType;
	}

}
