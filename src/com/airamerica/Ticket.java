package com.airamerica;

import java.util.ArrayList;
import com.airamerica.utils.*;

public class Ticket extends Product {
	
	private String travelDate;
	private String noOfPass;
	private Person person;
	private String ticketNote;
	private Airport depAirportCode;
	private Airport arrAirportCode;
	private String depTime;
	private String arrTime;
	private String flightNo;
	private String flightClass;
	private String airCraftType;
	private double baseFare;
	private double miles;
	
	private ArrayList<Passenger> passengers;
	
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
		this.passengers = new ArrayList<Passenger>();
		if(flightClass.equals("EC")){
			this.baseFare = .15;
		} else if(flightClass.equals("BC")){
			this.baseFare = .5;
		} else if(flightClass.equals("EP")){
			this.baseFare = .2;
		}
		
		Haversine haversine = new Haversine();
		
		this.miles = haversine.getMiles(this.getDepAirportCode().getLatitude(), this.getDepAirportCode().getLongtitude(), this.getArrAirportCode().getLatitude(), this.getArrAirportCode().getLongtitude());
	}

	public String getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}

	public String getNoOfPass() {
		return noOfPass;
	}

	public void setNoOfPass(String noOfPass) {
		this.noOfPass = noOfPass;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getTicketNote() {
		return ticketNote;
	}

	public void setTicketNote(String ticketNote) {
		this.ticketNote = ticketNote;
	}

	public Airport getDepAirportCode() {
		return depAirportCode;
	}

	public void setDepAirportCode(Airport depAirportCode) {
		this.depAirportCode = depAirportCode;
	}

	public Airport getArrAirportCode() {
		return arrAirportCode;
	}

	public void setArrAirportCode(Airport arrAirportCode) {
		this.arrAirportCode = arrAirportCode;
	}

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public String getArrTime() {
		return arrTime;
	}

	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public String getAirCraftType() {
		return airCraftType;
	}

	public void setAirCraftType(String airCraftType) {
		this.airCraftType = airCraftType;
	}

	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(ArrayList<Passenger> passengers) {
		this.passengers = passengers;
	}

	public void addPassenger(Passenger passenger){
		this.passengers.add(passenger);
	}
	
	public double getMiles(){
		return this.miles;
	}
	
	public double calculateFee(String type){
		double federalExciseTax = 1.075;
		double flightSegmentTax = 4 * this.passengers.size();
		double sept11SecurityFee = 5.60 * this.passengers.size();
		double passengerFacilityCharge = this.getArrAirportCode().getPassengerFacilityFee();
		
		Haversine haversine = new Haversine();
		 
		double totalFee = this.baseFare * this.miles;;
		
		if(type.equals("G")){
			totalFee *= federalExciseTax;
			totalFee += passengerFacilityCharge;
			totalFee += sept11SecurityFee;
			totalFee += flightSegmentTax;
			
		} else if(type.equals("C")){
			totalFee *= .88;
			totalFee *= federalExciseTax;
			totalFee += passengerFacilityCharge;
			totalFee += sept11SecurityFee;
			totalFee += flightSegmentTax;
		}
		
		return totalFee;
	}
	
	
}
