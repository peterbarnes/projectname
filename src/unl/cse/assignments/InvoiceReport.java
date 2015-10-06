package unl.cse.assignments;

import com.airamerica.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/* Assignment 3,5 and 6 (Project Phase-II,IV and V) */

public class InvoiceReport {

	public static DataConverter converter = new DataConverter();

	public static ArrayList<Person>persons = converter.personList;
	public static ArrayList<Airport>airports = converter.airportList;
	public static ArrayList<Product>products = converter.productList;
	public static ArrayList<Customer>customers = converter.customerList;

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
			Customer customer = null;
			for(Customer c : customers){
				if(c.getCustomerCode().equals(customerCode)){
					customer = c;
					break;
				}
			}
			String salespersonCode = tokens[2];
			Person person = null;
			for(Person p : persons){
				if(p.getPersonCode().equals(salespersonCode)){
					person = p;
					break;
				}
			}
			String date = tokens[3];
			ArrayList<Product>products = new ArrayList<Product>(tokens[4].split(",").length);
			String product[] = tokens[4].split(",");
			for(int i = 0; i < tokens[4].split(",").length; i++){
				String productTokens[] = product[i].split(":");
				String code = productTokens[0];
				String travelDate = productTokens[1];
				String noOfPass = productTokens[2];
				String seat = productTokens[3];
				String personCode = productTokens[4];
				String identityNumber = productTokens[5];
				String age = productTokens[6];
				String nationality = productTokens[7];
				String ticketNote = productTokens[8];
				Product productDummy = null;
				for(Product p : products){
					if(p.getProductCode().equals(code)){
						productDummy = p;
						p.setTravelDate(travelDate);
						p.setNoOfPass(noOfPass);
						p.setSeat(seat);
						p.setPersonCode(personCode);
						p.setIdentityNumber(identityNumber);
						p.setAge(age);
						p.setNationality(nationality);
						p.setTicketNote(ticketNote);
						products.add(p);
						break;
					}
				}
			}

			Invoice invoice = new Invoice(invoiceCode, customer, person, date, products);
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


	}
}
