package com.airamerica;

import java.util.ArrayList;

public class Invoice {
	
	private String InvoiceCode;
	private String CustomerCode;
	private String SalespersonCode;
	private String date;
	//private ArrayList<Product>products;
	// TODO products
	
	public Invoice(String invoiceCode, String customerCode,
			String salespersonCode, String date) {
		super();
		InvoiceCode = invoiceCode;
		CustomerCode = customerCode;
		SalespersonCode = salespersonCode;
		this.date = date;
		//this.products = products;
	}
	
}
