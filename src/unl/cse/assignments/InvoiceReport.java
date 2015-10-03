package unl.cse.assignments;

import com.airamerica.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/* Assignment 3,5 and 6 (Project Phase-II,IV and V) */

public class InvoiceReport {
	
	private String generateSummaryReport() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Executive Summary Report\n");
		sb.append("=========================\n");
		
		//TODO: Add code for generating summary of all Invoices
		
		return sb.toString();
	}
	

	private String getTravelSummary() {
		StringBuilder sb = new StringBuilder();
		sb.append("FLIGHT INFORMATION");
		sb.append("==================================================\n");

		//TODO: Add code for generating Travel Information of an Invoice
		
		return sb.toString();
		
	}
	
	private String getCostSummary() {
		StringBuilder sb = new StringBuilder();
		sb.append("FARES AND SERVICES");
		sb.append("==================================================\n");

		//TODO: Add code for generating Cost Summary of all 
		//products and services in an Invoice
		
		return sb.toString();
		
	}

	public String generateDetailReport() {
	StringBuilder sb = new StringBuilder();		
	sb.append("Individual Invoice Detail Reports\n");
	sb.append("==================================================\n");
	
	/* TODO: Loop through all invoices and call the getTravelSummary() and 
	getCostSummary() for each invoice*/
	
	
	
	return sb.toString();
	}

	public static void main(String args[]) {
		
		Scanner invoices = null;

		try {
			invoices = new Scanner (new File ("data/Invoices.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int totalInvoices = invoices.nextInt();
		String blank = invoices.nextLine();
		ArrayList<Invoice>invoiceList = new ArrayList<Invoice>(totalInvoices);
		
		while (invoices.hasNext()){
			String line = invoices.nextLine();
			String tokens[] = line.split(";");
			String invoiceCode = tokens[0];
			String customerCode = tokens[1];
			String salespersonCode = tokens[2];
			String date = tokens[3];
			
			Invoice invoice = new Invoice(invoiceCode, customerCode, salespersonCode, date);
			invoiceList.add(invoice);
		}
		
		InvoiceReport ir = new InvoiceReport();
		String summary = ir.generateSummaryReport();
		String details = ir.generateDetailReport();
				
		System.out.println(summary);
		System.out.println("\n\n");
		System.out.println(details);
		
		System.out.println("======================================================================================================================");
		System.out.println("\n\n");
		
		personList.add("thing");
	}
}
