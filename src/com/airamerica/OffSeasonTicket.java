package com.airamerica;

public class OffSeasonTicket extends Product {
	
	private String seasonStartDate;
	private String seasonEndDate;
	private Airport depAirportCode;
	private Airport arrAirportCode;
	private String depTime;
	private String arrTime;
	private String flightNo;
	private String flightClass;
	private String airCraftType;
	private double rebate;
	
	public OffSeasonTicket(String productCode, String productType,
			String seasonStartDate, String seasonEndDate,
			Airport depAirportCode, Airport arrAirportCode, String depTime,
			String arrTime, String flightNo, String flightClass,
			String airCraftType, double rebate) {
		super(productCode, productType);
		this.seasonStartDate = seasonStartDate;
		this.seasonEndDate = seasonEndDate;
		this.depAirportCode = depAirportCode;
		this.arrAirportCode = arrAirportCode;
		this.depTime = depTime;
		this.arrTime = arrTime;
		this.flightNo = flightNo;
		this.flightClass = flightClass;
		this.airCraftType = airCraftType;
		this.rebate = rebate;
	}

}
