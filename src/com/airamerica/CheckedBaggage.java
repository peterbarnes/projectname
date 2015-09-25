package com.airamerica;

public class CheckedBaggage extends Product {
	
	private int ticketCode;

	public CheckedBaggage(String productCode, String productType, int ticketCode) {
		super(productCode, productType);
		this.ticketCode = ticketCode;
	}

}
