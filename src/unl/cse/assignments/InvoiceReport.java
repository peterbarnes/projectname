package unl.cse.assignments;

import com.airamerica.*;

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

		//TODO: Add code for generating summary of all Invoices

		return sb.toString();
	}


	private String getTravelSummary(Invoice invoice) {
		StringBuilder sb = new StringBuilder();
		sb.append("FLIGHT INFORMATION");
		sb.append("==================================================\n");

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
		
		loadData();
		
		System.out.println("running");
		
		for(Invoice i : invoices){
			System.out.println(i.getDate());
		}
		
//		InvoiceReport ir = new InvoiceReport();
//		String summary = ir.generateSummaryReport();
//		String details = ir.generateDetailReport();
//
//		System.out.println(summary);
//		System.out.println("\n\n");
//		System.out.println(details);
//
//		System.out.println("======================================================================================================================");
//		System.out.println("\n\n");


	}
}
