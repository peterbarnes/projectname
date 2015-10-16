package com.airamerica;

import com.airamerica.utils.Haversine;

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
	
	public double calculateSubtotal(){
		
		Haversine haversine = new Haversine();
		 
		double totalFee = this.baseFare * this.miles * this.passengers.size();
		
		return totalFee;
	}
	
	public double calculateTax(String type){
		
		double federalExciseTax = 1.075;
		double flightSegmentTax = 4 * this.passengers.size();
		double sept11SecurityFee = 5.60 * this.passengers.size();
		double passengerFacilityCharge = this.getArrAirportCode().getPassengerFacilityFee() * this.getPassengers().size();
		
		double totalTax = calculateSubtotal();
		
		if(type.equals("G")){
			totalTax *= federalExciseTax;
			totalTax += passengerFacilityCharge;
			totalTax += sept11SecurityFee;
			totalTax += flightSegmentTax;
			
		} else if(type.equals("C")){
			totalTax *= federalExciseTax;
			totalTax += passengerFacilityCharge;
			totalTax += sept11SecurityFee;
			totalTax += flightSegmentTax;
		}
		return totalTax - calculateSubtotal();
	}
}
