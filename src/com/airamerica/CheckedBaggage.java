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
	
	public double calculateSubtotal(){
		
		double fee = 0;
		int baggages = Integer.parseInt(this.noOfBaggage);
		if(baggages == 1){
			fee = 25;
		} else if(baggages > 1){
			fee = 25;
			for(int i = 0; i < baggages - 1; i++){
				fee += 35;
			}
		}
			
		return fee;
	}
	
	public double calculateTax(String type){
		double tax = 0;
		double totalTax = 0;
		
		if(!(type.equals("V"))){
			tax = .04;
			double subtotal = calculateSubtotal();
			
			totalTax = subtotal * tax;
		}
		
		return totalTax;
	}

}
