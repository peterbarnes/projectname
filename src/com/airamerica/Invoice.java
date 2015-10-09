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

	public String getInvoiceCode() {
		return InvoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		InvoiceCode = invoiceCode;
	}

	public Customer getCustomerCode() {
		return CustomerCode;
	}

	public void setCustomerCode(Customer customerCode) {
		CustomerCode = customerCode;
	}

	public Person getSalespersonCode() {
		return SalespersonCode;
	}

	public void setSalespersonCode(Person salespersonCode) {
		SalespersonCode = salespersonCode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}	
	
	
}
