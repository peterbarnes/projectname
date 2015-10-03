package com.airamerica;

public class Airport {

	private String airportCode;
	private String name;
	private Address address;
//	private int latitudeDegs;
//	private int latitudeMins;
//	private int longitudeDegs;
//	private int longitudeMins;
	private double passengerFacilityFee;
	private double latitude;
	private double longtitude;
	
	
	public Airport(String airportCode, String name, Address address,
			int latitudeDegs, int latitudeMins, int longitudeDegs,
			int longitudeMins, double passengerFacilityFee) {
		super();
		this.airportCode = airportCode;
		this.name = name;
		this.address = address;
//		this.latitudeDegs = latitudeDegs;
//		this.latitudeMins = latitudeMins;
//		this.longitudeDegs = longitudeDegs;
//		this.longitudeMins = longitudeMins;
		latitude = latitudeDegs + latitudeMins/60.0;
		longtitude = longitudeDegs + longitudeMins/60.0;
		this.passengerFacilityFee = passengerFacilityFee;
	}
	
	public String getAirportCode() {
		return airportCode;
	}
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
//	public int getLatitudeDegs() {
//		return latitudeDegs;
//	}
//	public void setLatitudeDegs(int latitudeDegs) {
//		this.latitudeDegs = latitudeDegs;
//	}
//	public int getLatitudeMins() {
//		return latitudeMins;
//	}
//	public void setLatitudeMins(int latitudeMins) {
//		this.latitudeMins = latitudeMins;
//	}
//	public int getLongitudeDegs() {
//		return longitudeDegs;
//	}
//	public void setLongitudeDegs(int longitudeDegs) {
//		this.longitudeDegs = longitudeDegs;
//	}
//	public int getLongitudeMins() {
//		return longitudeMins;
//	}
//	public void setLongitudeMins(int longitudeMins) {
//		this.longitudeMins = longitudeMins;
//	}
	public double getPassengerFacilityFee() {
		return passengerFacilityFee;
	}
	public void setPassengerFacilityFee(double passengerFacilityFee) {
		this.passengerFacilityFee = passengerFacilityFee;
	}
	
}