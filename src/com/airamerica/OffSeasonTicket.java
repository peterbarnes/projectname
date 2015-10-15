package com.airamerica;

import com.airamerica.utils.Haversine;

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

	public double calculateSubtotal(){

		Haversine haversine = new Haversine();
		
		double discount = 1 - this.rebate;
		//this.baseFare *= discount;
		System.out.println(this.miles);

		double totalFee = this.baseFare * this.miles + 20;
		System.out.println(totalFee);

		return totalFee;
	}

	public double calculateTax(String type){

		double federalExciseTax = 1.075;
		double flightSegmentTax = 4;
		double sept11SecurityFee = 5.60;
		double passengerFacilityCharge = this.getArrAirportCode().getPassengerFacilityFee();

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
