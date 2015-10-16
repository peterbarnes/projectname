package com.airamerica;

import org.joda.time.DateTime;

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
		DateTime startDate = DateTime.parse(this.seasonStartDate);
		DateTime endDate = DateTime.parse(this.seasonEndDate);
		
		Haversine haversine = new Haversine();
		
		double baseFee = this.baseFare * this.miles;
		
		double discount = baseFee * this.rebate;
		//this.baseFare *= discount;

		double totalFee = 0;
		
		if(this.travelDate.isBefore(endDate) && this.travelDate.isAfter(startDate)){
			totalFee = baseFee - discount;
		} else {
			totalFee = baseFee;
		}

		return totalFee * this.getPassengers().size() + 20;
	}

	public double calculateTax(String type){

		double federalExciseTax = 1.075;
		double flightSegmentTax = 4 * this.getPassengers().size();
		double sept11SecurityFee = 5.60 * this.getPassengers().size();
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

	public String getSeasonStartDate() {
		return seasonStartDate;
	}

	public void setSeasonStartDate(String seasonStartDate) {
		this.seasonStartDate = seasonStartDate;
	}

	public String getSeasonEndDate() {
		return seasonEndDate;
	}

	public void setSeasonEndDate(String seasonEndDate) {
		this.seasonEndDate = seasonEndDate;
	}

	public double getRebate() {
		return rebate;
	}

	public void setRebate(double rebate) {
		this.rebate = rebate;
	}
	
	

}
