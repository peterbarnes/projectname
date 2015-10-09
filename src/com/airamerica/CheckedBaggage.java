package com.airamerica;

public class CheckedBaggage extends Product {
	
	private int ticketCode;
	private String noOfBaggage;

	public CheckedBaggage(String productCode, String productType, int ticketCode) {
		super(productCode, productType);
		this.ticketCode = ticketCode;
	}

	public int getTicketCode() {
		return ticketCode;
	}

	public void setTicketCode(int ticketCode) {
		this.ticketCode = ticketCode;
	}

	public String getNoOfBaggage() {
		return noOfBaggage;
	}

	public void setNoOfBaggage(String noOfBaggage) {
		this.noOfBaggage = noOfBaggage;
	}

}
