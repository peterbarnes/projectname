package com.airamerica;

public class StandardTicket extends Ticket {

	public StandardTicket(String productCode, 
					      String productType,
					      Airport depAirportCode, 
					      Airport arrAirportCode, 
					      String depTime,
					      String arrTime, 
					      String flightNo, 
					      String flightClass,
					      String airCraftType) {
		super(productCode, productType, depAirportCode, arrAirportCode, depTime,
				arrTime, flightNo, flightClass, airCraftType);
		// TODO Auto-generated constructor stub
	}
}
