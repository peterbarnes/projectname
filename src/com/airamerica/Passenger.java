package com.airamerica;

public class Passenger {
	
	private String seat;
	private String identityNumber;
	private String age;
	private String nationality;
	private Person person;
	public Passenger(String seat, String identityNumber, String age,
			String nationality, Person person) {
		super();
		this.seat = seat;
		this.identityNumber = identityNumber;
		this.age = age;
		this.nationality = nationality;
		this.person = person;
	}
	
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public String getIdentityNumber() {
		return identityNumber;
	}
	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	
}
