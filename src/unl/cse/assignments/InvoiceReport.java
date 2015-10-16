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
		
		double finalSubtotal = 0;
		double finalTax = 0;
		double finalTotal = 0;
		double additionalFee = 0;
		double modifier = 1;
		
		double finalSubtotal2 = 0;
		double finalTax2 = 0;
		double finalTotal2 = 0;
		double additionalFee2 = 0;

		sb.append("Executive Summary Report\n");
		sb.append("=========================\n");
		sb.append(String.format("%-10s%-50s%-30s%11s%12s%12s%12s\n", "Invoice", "Customer", "Salesperson", "Subtotal", "Fees", "Taxes", "Total"));

		//TODO: Add code for generating summary of all Invoices
		for(Invoice i : invoices){
			Customer c = i.getCustomerCode();
			String customerType = "";
			if(c.getType().equals("G")){
				customerType = "General";
			} else if(c.getType().equals("C")){
				customerType = "Corporate";
			} else if(c.getType().equals("V")){
				customerType = "Government";
			}
			
			additionalFee = 0;
			
			if(c.getType().equals("C")){
				additionalFee = 40;
				modifier = .12;
				additionalFee2 += 40;
			}
			
			String salespersonName = "ONLINE";
			
			if(i.getSalespersonCode() != null){
				salespersonName = i.getSalespersonCode().getLastName() + ", " + i.getSalespersonCode().getFirstName();
			}
			
			for(Product p : i.getProducts()){

				if(p.getProductType().equals("TS")){

					StandardTicket ticket = (StandardTicket) p;

					finalSubtotal += ticket.calculateSubtotal();
					finalSubtotal2 += ticket.calculateSubtotal();
					finalTax += ticket.calculateTax(c.getType());
					finalTax2 += ticket.calculateTax(c.getType());
					finalTotal += ticket.calculateSubtotal() + ticket.calculateTax(c.getType());
					finalTotal2 += ticket.calculateSubtotal() + ticket.calculateTax(c.getType());
					

				} else if(p.getProductType().equals("TO")){

					OffSeasonTicket ticket = (OffSeasonTicket) p;

					finalSubtotal += ticket.calculateSubtotal();
					finalSubtotal2 += ticket.calculateSubtotal();
					finalTax += ticket.calculateTax(c.getType());
					finalTax2 += ticket.calculateTax(c.getType());
					finalTotal += ticket.calculateSubtotal() + ticket.calculateTax(c.getType());
					finalTotal2 += ticket.calculateSubtotal() + ticket.calculateTax(c.getType());

				} else if(p.getProductType().equals("TA")){

					AwardTickets ticket = (AwardTickets) p;

					finalSubtotal += ticket.subtotal();
					finalSubtotal2 += ticket.subtotal();
					finalTax += ticket.calculateTax(c.getType());
					finalTax2 += ticket.calculateTax(c.getType());
					finalTotal += ticket.subtotal() + ticket.calculateTax(c.getType());
					finalTotal2 += ticket.subtotal() + ticket.calculateTax(c.getType());

				} else if(p.getProductType().equals("SC")){

					CheckedBaggage checkedBaggage = (CheckedBaggage) p;

					finalSubtotal += checkedBaggage.calculateSubtotal();
					finalSubtotal2 += checkedBaggage.calculateSubtotal();
					finalTax += checkedBaggage.calculateTax(c.getType());
					finalTax2 += checkedBaggage.calculateTax(c.getType());
					finalTotal += checkedBaggage.calculateSubtotal() + checkedBaggage.calculateTax(c.getType());
					finalTotal2 += checkedBaggage.calculateSubtotal() + checkedBaggage.calculateTax(c.getType());

				} else if(p.getProductType().equals("SI")){
					double miles = 0;
					
					Insurance insurance = (Insurance) p;
					
					finalSubtotal += insurance.calculateSubtotal(miles);
					finalSubtotal2 += insurance.calculateSubtotal(miles);
					finalTax += insurance.calculateTax(c.getType(), miles);
					finalTax2 += insurance.calculateTax(c.getType(), miles);
					finalTotal += insurance.calculateSubtotal(miles) + insurance.calculateTax(c.getType(), miles);
					finalTotal2 += insurance.calculateSubtotal(miles) + insurance.calculateTax(c.getType(), miles);

				} else if(p.getProductType().equals("SR")){

					Refreshments refreshments = (Refreshments) p;
					
					finalSubtotal += refreshments.calculateSubtotal();
					finalSubtotal2 += refreshments.calculateSubtotal();
					finalTax += refreshments.calculateTax(c.getType());
					finalTax2 += refreshments.calculateTax(c.getType());
					finalTotal += refreshments.calculateSubtotal() + refreshments.calculateTax(c.getType());
					finalTotal2 += refreshments.calculateSubtotal() + refreshments.calculateTax(c.getType());

				} else if(p.getProductType().equals("SS")){

					SpecialAssistance specialAssistance = (SpecialAssistance) p;

				}

			}
			
			if(c.getType().equals("C")){
				double discount = finalSubtotal * modifier;
				finalTotal -= discount;
			}
			
			sb.append(String.format("%-10s%-50s%-30s%11.2f%12.2f%12.2f%12.2f\n", i.getInvoiceCode(), i.getCustomerCode().getCustomerName() + " [" + customerType + "]", salespersonName, finalSubtotal, additionalFee, finalTax, finalTotal + additionalFee));
			finalSubtotal = 0;
			finalTax = 0;
			finalTotal = 0;
			modifier = 1;
		}
		sb.append("=========================================================================================================================================\n");
		sb.append(String.format("%-90s%11.2f%12.2f%12.2f%12.2f\n", "TOTALS", finalSubtotal2, additionalFee2, finalTax2, finalTotal2));
		return sb.toString();
	}


	private String getTravelSummary(Invoice invoice) {
		StringBuilder sb = new StringBuilder();

		sb.append("FLIGHT INFORMATION\n");
		sb.append("==================================================\n");

		for(Product pro : invoice.getProducts()){
			Ticket ticket = null;
			if(pro instanceof Ticket){
				ticket = (Ticket) pro;

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
			}
		}

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

		double finalSubtotal = 0;
		double finalTax = 0;
		double finalTotal = 0;
		double additionalFee = 0;
		
		if(c.getType().equals("C")){
			additionalFee = 40;
		}

		for(Product p : invoice.getProducts()){

			if(p.getProductType().equals("TS")){

				StandardTicket ticket = (StandardTicket) p;
				sb.append(String.format("%-10s %-70s %-15.2f %-15.2f %-15.2f \n", p.getProductCode(), 
						"StandardTicket (" + ticket.getFlightClass() + ") " + ticket.getDepAirportCode().getAirportCode() + " to " + ticket.getArrAirportCode().getAirportCode() + " (" + ticket.getMiles() + ")", 
						ticket.calculateSubtotal(), 
						ticket.calculateTax(c.getType()), 
						ticket.calculateSubtotal() + ticket.calculateTax(c.getType())));

				finalSubtotal += ticket.calculateSubtotal();
				finalTax += ticket.calculateTax(c.getType());
				finalTotal += ticket.calculateSubtotal() + ticket.calculateTax(c.getType());


			} else if(p.getProductType().equals("TO")){

				OffSeasonTicket ticket = (OffSeasonTicket) p;
				sb.append(String.format("%-10s %-70s %-15.2f %-15.2f %-15.2f \n", p.getProductCode(), 
						"OffSeasonTicket (" + ticket.getFlightClass() + ") " + ticket.getDepAirportCode().getAirportCode() + " to " + ticket.getArrAirportCode().getAirportCode() + " (" + ticket.getMiles() + ")", 
						ticket.calculateSubtotal(), 
						ticket.calculateTax(c.getType()), 
						ticket.calculateSubtotal() + ticket.calculateTax(c.getType())));

				finalSubtotal += ticket.calculateSubtotal();
				finalTax += ticket.calculateTax(c.getType());
				finalTotal += ticket.calculateSubtotal() + ticket.calculateTax(c.getType());

			} else if(p.getProductType().equals("TA")){

				AwardTickets ticket = (AwardTickets) p;
				sb.append(String.format("%-10s %-70s %-15.2f %-15.2f %-15.2f \n", p.getProductCode(), 
						"AwardTicket (" + ticket.getFlightClass() + ") " + ticket.getDepAirportCode().getAirportCode() + " to " + ticket.getArrAirportCode().getAirportCode() + " (" + ticket.getMiles() + "), (" + ticket.calculatePoints() + " points)", 
						ticket.subtotal(), 
						ticket.calculateTax(c.getType()), 
						ticket.subtotal() + ticket.calculateTax(c.getType())));

				finalSubtotal += ticket.subtotal();
				finalTax += ticket.calculateTax(c.getType());
				finalTotal += ticket.subtotal() + ticket.calculateTax(c.getType());

			} else if(p.getProductType().equals("SC")){

				CheckedBaggage checkedBaggage = (CheckedBaggage) p;
				sb.append(String.format("%-10s %-70s %-15.2f %-15.2f %-15.2f \n", p.getProductCode(), 
						"CheckedBaggage (" + checkedBaggage.getNoOfBaggage() + " units @ $25.00 for first and $35.00 onwards)", 
						checkedBaggage.calculateSubtotal(), 
						checkedBaggage.calculateTax(c.getType()), 
						checkedBaggage.calculateSubtotal() + checkedBaggage.calculateTax(c.getType())));

				finalSubtotal += checkedBaggage.calculateSubtotal();
				finalTax += checkedBaggage.calculateTax(c.getType());
				finalTotal += checkedBaggage.calculateSubtotal() + checkedBaggage.calculateTax(c.getType());

			} else if(p.getProductType().equals("SI")){
				double miles = 0;
				
				Insurance insurance = (Insurance) p;
				sb.append(String.format("%-10s %-70s %-15.2f %-15.2f %-15.2f \n", p.getProductCode(), 
						"Insurance " + insurance.getName() + " (" + insurance.getAgeClass() + ")", 
						insurance.calculateSubtotal(miles), 
						insurance.calculateTax(c.getType(), miles), 
						insurance.calculateSubtotal(miles) + insurance.calculateTax(c.getType(), miles)));
				
				finalSubtotal += insurance.calculateSubtotal(miles);
				finalTax += insurance.calculateTax(c.getType(), miles);
				finalTotal += insurance.calculateSubtotal(miles) + insurance.calculateTax(c.getType(), miles);

			} else if(p.getProductType().equals("SR")){

				Refreshments refreshments = (Refreshments) p;
				sb.append(String.format("%-10s %-70s %-15.2f %-15.2f %-15.2f \n", p.getProductCode(), 
						refreshments.getName() + " (" + refreshments.getNoOfRefreshments() + " of units @ " + refreshments.getCost() + " with 5% off)" , 
						refreshments.calculateSubtotal(), 
						refreshments.calculateTax(c.getType()), 
						refreshments.calculateSubtotal() + refreshments.calculateTax(c.getType())));
				
				finalSubtotal += refreshments.calculateSubtotal();
				finalTax += refreshments.calculateTax(c.getType());
				finalTotal += refreshments.calculateSubtotal() + refreshments.calculateTax(c.getType());

			} else if(p.getProductType().equals("SS")){

				SpecialAssistance specialAssistance = (SpecialAssistance) p;
				sb.append(String.format("%-10s %-70s %-15.2f %-15.2f %-15.2f \n", p.getProductCode(), 
						"Special Assistance for" + " [" + specialAssistance.getPerson().getLastName() + ", " + specialAssistance.getPerson().getFirstName() + "] (" + specialAssistance.calculateFee() + ")" , 
						0.00, 
						0.00, 
						0.00));

			}

		}
		sb.append(String.format("\n"));
		sb.append(String.format("%-81s %-15.2f %-15.2f %-15.2f\n", "SUB-TOTALS", finalSubtotal, finalTax, finalTotal));
		sb.append(String.format("%-113s %-15.2f\n", "ADDITIONAL FEE", additionalFee));
		sb.append(String.format("%-113s %-15.2f\n", "TOTAL", finalTotal + additionalFee));
		sb.append("\n\n");

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
