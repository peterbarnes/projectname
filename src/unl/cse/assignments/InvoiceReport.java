package unl.cse.assignments;

import com.airamerica.*;
import com.airamerica.utils.StandardUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/* Assignment 3,5 and 6 (Project Phase-II,IV and V) */

public class InvoiceReport extends DataLoader{

	private String generateSummaryReport() {
		StringBuilder sb = new StringBuilder();

		sb.append("Executive Summary Report\n");
		sb.append("=========================\n");
		sb.append(String.format("%-10s%-50s%-30s%11s%12s%12s%12s%12s\n", "Invoice", "Customer", "Salesperson", "Subtotal", "Fees", "Taxes", "Discount", "Total"));

		//TODO: Add code for generating summary of all Invoices

		return sb.toString();
	}


	private String getTravelSummary(Invoice invoice) {
		StringBuilder sb = new StringBuilder();
		
		Ticket ticket = null;
		
		if(invoice.getProducts().get(0) instanceof Ticket){
			ticket = (Ticket) invoice.getProducts().get(0);
		}
		
		sb.append("FLIGHT INFORMATION\n");
		sb.append("==================================================\n");

		sb.append(String.format("%-15s%-10s%-10s %30s %30s%15s\n", "Day,Date", "Flight", "Class", "Departure City and Time", "Arrival City and Time", "AirCraft"));

		sb.append(String.format("%-15s%-10s%-10s %30s %30s%15s\n", ticket.getTravelDate(), 
																   ticket.getFlightNo(), 
																   ticket.getFlightClass(), 
																   ticket.getDepAirportCode().getAirportCode() + " " + ticket.getDepTime(), 
																   ticket.getArrAirportCode().getAirportCode() + " " + ticket.getArrTime(), 
																   ticket.getAirCraftType()));
		
		sb.append("\n");
		sb.append(String.format("%-40s%-10s%-15s\n", "Traveller", "Age", "SeatNo"));
		for(int i = 0; i < ticket.getPassengers().size(); i++){
			Passenger p = ticket.getPassengers().get(i);
			sb.append(String.format("%-40s%-10s%-15s\n", p.getPerson().getLastName() + "," + p.getPerson().getFirstName(), p.getAge() , p.getSeat()));
		}
		
		sb.append("\n");
		sb.append("*" + ticket.getTicketNote() + "\n");
	
		
		sb.append("--------------------------------------------------------------------------------------------------------------\n");
		sb.append("CUSTOMER INFORMATION\n");
		
		Customer c = searchCustomers(invoice.getCustomerCode().getCustomerCode());
		
		sb.append(c.getCustomerName() + " (" + c.getCustomerCode() + ")\n");
		
		String customerType = "";
		if(c.getType().equals("G")){
			customerType = "General";
		} else if(c.getType().equals("C")){
			customerType = "Corporate";
		} else if(c.getType().equals("V")){
			customerType = "Government";
		}
		
		sb.append("[" + customerType + "]\n");
		
		Person p = c.getPrimaryContact();
		sb.append(p.getLastName() + ", " + p.getFirstName() + "\n");
		sb.append(p.getAddress().getStreet() + "\n");
		sb.append(p.getAddress().getCity() + " " + p.getAddress().getState() + " " + p.getAddress().getZip() + " " + p.getAddress().getCountry() + "\n");
		
		Person salesperson = invoice.getSalespersonCode();
		
		if(salesperson != null){
			sb.append("Salesperson: " + salesperson.getLastName() + ", " + salesperson.getFirstName() + "\n");
		} else {
			sb.append("Salesperson: ONLINE, null");
		}
		
		sb.append("\n");
		sb.append("--------------------------------------------------------------------------------------------------------------\n\n\n");
		
		

		return sb.toString();

	}

	private String getCostSummary(Invoice invoice) {
		StringBuilder sb = new StringBuilder();
		sb.append("FARES AND SERVICES\n");
		sb.append("==================================================\n");

		//TODO: Add code for generating Cost Summary of all 
		//products and services in an Invoice
		sb.append(String.format("%-10s %-70s %-15s %-15s %-15s \n", "Code", "Item", "SubTotal", "Tax", "Total"));
		Customer c = invoice.getCustomerCode();
		for(Product p : invoice.getProducts()){
			
			if(p.getProductType().equals("TS")){
				
				StandardTicket ticket = (StandardTicket) p;
				sb.append(String.format("%-10s %-70s %-15.2f %-15.2f %-15.2f \n", p.getProductCode(), 
																			"StandardTicket (" + ticket.getFlightClass() + ") " + ticket.getDepAirportCode().getAirportCode() + " to " + ticket.getArrAirportCode().getAirportCode() + " (" + ticket.getMiles() + ")", 
																			ticket.calculateSubtotal(), 
																			ticket.calculateTax(c.getType()), 
																			ticket.calculateSubtotal() + ticket.calculateTax(c.getType())));
				
			} else if(p.getProductType().equals("TO")){
				
				OffSeasonTicket ticket = (OffSeasonTicket) p;
				sb.append(String.format("%-10s %-70s %-15.2f %-15.2f %-15.2f \n", p.getProductCode(), 
						"OffSeasonTicket (" + ticket.getFlightClass() + ") " + ticket.getDepAirportCode().getAirportCode() + " to " + ticket.getArrAirportCode().getAirportCode() + " (" + ticket.getMiles() + ")", 
						ticket.calculateSubtotal(), 
						ticket.calculateTax(c.getType()), 
						ticket.calculateSubtotal() + ticket.calculateTax(c.getType())));
				
			} else if(p.getProductType().equals("TA")){
				
				Ticket ticket = (AwardTickets) p;
				
			} else if(p.getProductType().equals("SC")){
				
				CheckedBaggage checkedBaggage = (CheckedBaggage) p;
				
			} else if(p.getProductType().equals("SI")){
				
				Insurance insurance = (Insurance) p;
				
			} else if(p.getProductType().equals("SR")){
				
				Refreshments refreshments = (Refreshments) p;
				
			} else if(p.getProductType().equals("SS")){
				
				SpecialAssistance specialAssistance = (SpecialAssistance) p;
				
			}
			
			
		}

		return sb.toString();

	}

	public String generateDetailReport() {
		StringBuilder sb = new StringBuilder();		
		sb.append("Individual Invoice Detail Reports\n");
		sb.append("==================================================\n");

		/* TODO: Loop through all invoices and call the getTravelSummary() and 
	getCostSummary() for each invoice*/
		for (Invoice inv : invoices){
			sb.append("Invoice: " + inv.getInvoiceCode() + "\n");
			sb.append("------------------------------------------------------------------------------------------------------\n");
			sb.append(String.format("%-85s %12s\n", "Air America", "PNR"));
			sb.append(String.format("%-15s %-70s %11s\n", "IssueDate:     ", inv.getDate(), StandardUtils.generatePNR() ));
			sb.append("-----------------------------------------------------------------------------------------------------\n");
			
			sb.append(getTravelSummary(inv));
			sb.append(getCostSummary(inv));
			
		}


		return sb.toString();
	}

	public static void main(String args[]) {
		
		loadData();
		
		System.out.println("running");
		
		
		
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
