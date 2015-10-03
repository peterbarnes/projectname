package com.airamerica;

public class OffSeasonTicket extends Ticket {
	
	private String seasonStartDate;
	private String seasonEndDate;
	private double rebate;
	
	public OffSeasonTicket(String productCode, 
						   String productType,
						   String seasonStartDate, 
						   String seasonEndDate,
						   Airport depAirportCode,
						   Airport arrAirportCode, 
						   String depTime,
						   String arrTime, 
						   String flightNo, 
						   String flightClass,
						   String airCraftType, 
						   double rebate) {
		super(productCode, productType, depAirportCode, arrAirportCode,
				depTime, arrTime, flightNo, flightClass, airCraftType);
		this.seasonStartDate = seasonStartDate;
		this.seasonEndDate = seasonEndDate;
		this.rebate = rebate;
	}
	
	

}
