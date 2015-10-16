package com.airamerica;

import com.airamerica.utils.Haversine;

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

	public double calculatePoints(){

		Haversine haversine = new Haversine();

		double cost = this.baseFare * this.miles;
		
		double totalPoints = this.pointsPerMile * cost;
		System.out.println(totalPoints + " asdl;kfas;ldf;alsdjfl;ajfl;kdjf;laksjdf;lakfjs\n");
	
		return totalPoints;
	}
	
	public double subtotal(){
		return 30;
	}

	public double calculateTax(String type){

		double federalExciseTax = 1.075;
		double flightSegmentTax = 4 * this.passengers.size();
		double sept11SecurityFee = 5.60 * this.passengers.size();
		double passengerFacilityCharge = this.getArrAirportCode().getPassengerFacilityFee() * this.getPassengers().size();
		
		double subtotal = 30;

		double totalTax = 30;

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
		return totalTax - subtotal;
	}

	public int getPointsPerMile() {
		return pointsPerMile;
	}

	public void setPointsPerMile(int pointsPerMile) {
		this.pointsPerMile = pointsPerMile;
	}

}
