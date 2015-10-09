package unl.cse.assignments;

/* Phase-I */
import com.airamerica.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

// Include imports for XML/JSON libraries if needed
import com.thoughtworks.xstream.XStream;

public class DataConverter extends DataLoader{
	
	// Methods to output various classes to XML Files
	public static void outputXMLFileForCustomers(ArrayList<Customer> customerList){
		XStream xstream = new XStream();
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("data/Customers_output_001.xml"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		pw.print("<customers>\n");
		for(Customer c : customerList){
			if(c.getType().equals("G")) {
				xstream.alias("general", Customer.class);
			} else if(c.getType().equals("C")) {
				xstream.alias("corporate", Customer.class);
			} else if(c.getType().equals("V")) {
				xstream.alias("government", Customer.class);
			}
			pw.print(xstream.toXML(c) + "\n");
		}
		pw.print("</customers>" + "\n");
		pw.close();
	}
	
	public static void outputXMLFileForPersons(ArrayList<Person> personList){
		XStream xstream = new XStream();
		xstream.alias("person", Person.class);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("data/Persons_output_001.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pw.print("<persons>\n");
		for(Person p : personList){
			pw.print(xstream.toXML(p) + "\n");
		}
		pw.print("</persons>" + "\n");
		pw.close();
	}
	
	public static void outputXMLFileForAirports(ArrayList<Airport> airportList){
		XStream xstream = new XStream();
		xstream.alias("airport", Airport.class);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("data/Airports_output_001.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pw.print("<airports>\n");
		for(Airport a : airportList) {
			pw.print(xstream.toXML(a) + "\n");
		}
		pw.print("</persons>" + "\n");
		pw.close();
	}
	
	public static void outputXMLFileForProducts(ArrayList<Product> productList){
		XStream xstream = new XStream();
		xstream.alias("product", Product.class);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("data/Products_output_001.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pw.print("<products>\n");
		for(Product p : productList){
			pw.print(xstream.toXML(p) + "\n");
		}
		pw.print("</products>" + "\n");
		pw.close();
	}
	
	public static void outputXMLFileForInvoices(ArrayList<Invoice> invoiceList){
		XStream xstream = new XStream();
		xstream.alias("invoice", Invoice.class);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("data/Invoices_output_001.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pw.print("<invoices>\n");
		for(Invoice p : invoiceList){
			pw.print(xstream.toXML(p) + "\n");
		}
		pw.print("</invoices>" + "\n");
		pw.close();
	}

	public static void main(String args[]) {
		
		outputXMLFileForPersons(getPersons());
		outputXMLFileForCustomers(getCustomers());
		outputXMLFileForAirports(getAirports());
		outputXMLFileForProducts(getProducts());
		outputXMLFileForInvoices(getInvoices());
		
		System.out.println("Shit worked");
	}
}
