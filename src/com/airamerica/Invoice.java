package com.airamerica;

import java.util.ArrayList;

public class Invoice {
	
	private String InvoiceCode;
	private Customer CustomerCode;
	private Person SalespersonCode;
	private String date;
	private ArrayList<Product> products;
	// TODO products
	
	public Invoice(String invoiceCode, Customer customerCode,
			Person salespersonCode, String date, ArrayList<Product> products) {
		super();
		InvoiceCode = invoiceCode;
		CustomerCode = customerCode;
		SalespersonCode = salespersonCode;
		this.date = date;
		this.products = products;
	}	
}
