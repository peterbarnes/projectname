package com.airamerica;

public class AwardTickets extends Product {
	
	private Airport depAirportCode;
	private Airport arrAirportCode;
	private String depTime;
	private String arrTime;
	private String flightNo;
	private String flightClass;
	private String airCraftType;
	private int pointsPerMile;
	
	public AwardTickets(String productCode, String productType,
			Airport depAirportCode, Airport arrAirportCode, String depTime,
			String arrTime, String flightNo, String flightClass,
			String airCraftType, int pointsPerMile) {
		super(productCode, productType);
		this.depAirportCode = depAirportCode;
		this.arrAirportCode = arrAirportCode;
		this.depTime = depTime;
		this.arrTime = arrTime;
		this.flightNo = flightNo;
		this.flightClass = flightClass;
		this.airCraftType = airCraftType;
		this.pointsPerMile = pointsPerMile;
	}

	

}
