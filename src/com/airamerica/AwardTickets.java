package com.airamerica;

public class AwardTickets extends Ticket {
	
	private int pointsPerMile;
	
	public AwardTickets(String productCode,
						String productType,
						Airport depAirportCode, 
						Airport arrAirportCode, 
						String depTime,
						String arrTime, 
						String flightNo, 
						String flightClass,
						String airCraftType, 
						int pointsPerMile) {
		super(productCode, productType, depAirportCode, arrAirportCode, depTime,
				arrTime, flightNo, flightClass, airCraftType);
		this.pointsPerMile = pointsPerMile;
		// TODO Auto-generated constructor stub
	}	

}
