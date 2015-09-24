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

public class DataConverter {

	public static void main(String args[]) {

		/*PERSONS*/
		Scanner persons = null;
		
		try {
			persons = new Scanner (new File ("data/Persons.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int totalPersons = persons.nextInt();
		ArrayList<Person> personList = new ArrayList<Person>(totalPersons);
		while (persons.hasNext()) {
			String line = persons.nextLine();
			String tokens[] = line.split(";");
			String personCode = tokens[0];
			
			String name[] = tokens[1].split(",");
			String lastName = name[0];
			String firstName = name[1];
			
			String address[] = tokens[2].split(",");
			String street = address[0];
			String city = address[1];
			String state = address[2];
			String zip = address[3];
			String country = address[4];
			Address newAddress = new Address(street, city, state, zip, country);
			
			//TODO phone number shit
			//TODO email shit
			Person person = new Person(personCode, newAddress, firstName, lastName);
			personList.add(person);
		}
		
		/*AIRPORTS*/
		Scanner airports = null;
		
		try {
			airports = new Scanner (new File ("data/Airports.dat"));
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
		int totalAirports = airports.nextInt();
		ArrayList<Airport> airportList = new ArrayList<Airport>(totalAirports);
		while (airports.hasNext()) {
			String line = airports.nextLine();
			String tokens[] = line.split(";");
			String airportCode = tokens[0];
			String name = tokens[1];
			
			String address[] = tokens[2].split(",");
			String street = address[0];
			String city = address[1];
			String state = address[2];
			String zip = address[3];
			String country = address[4];
			Address newAddress = new Address(street, city, state, zip, country);
			
			String coordinates[] = tokens[3].split(",");
			int latitudeDegs = Integer.parseInt(coordinates[0]);
			int latitudeMins = Integer.parseInt(coordinates[1]);
			int longitudeDegs = Integer.parseInt(coordinates[2]);
			int longitudeMins = Integer.parseInt(coordinates[3]);
			
			double passengerFacilityFee = Double.parseDouble(tokens[4]);
			
			Airport airport = new Airport(airportCode, name, newAddress, latitudeDegs, latitudeMins, longitudeDegs, longitudeMins, passengerFacilityFee);
			airportList.add(airport);
		}
		
		/*CUSTOMERS*/
		Scanner customers = null;
		
		try {
			customers = new Scanner (new File ("data/Customers.dat"));
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
		int totalCustomers = customers.nextInt();
		ArrayList<Customer> customerList = new ArrayList<Customer>(totalCustomers);
		while (customers.hasNext()){
			String line = customers.nextLine();
			String tokens[] = line.split(";");
			String customerCode = tokens[0];
			String type = tokens[1];
			String personCode = tokens[2]; //TODO search through persons.dat file
			Person primaryContact = null;
			for(Person p : personList){
				
				if(p.getPersonCode().equals(personCode)){
					primaryContact = p;
					break;
				}
				
			}
			
			String customerName = tokens[3];
			int airlineMiles = Integer.parseInt(tokens[4]);
			
			Customer customer = new Customer(customerCode, type, primaryContact, customerName, airlineMiles);
			customerList.add(customer);
			
		}
		
		/*PRODUCTS*/
		//XMLExample();
	}

	/*
	 * An example of using XStream API It exports to "data/Person-example.xml"
	 * NOTE: It may be interesting to note how compositions (and relationships
	 * are exported. NOTE: Pay attention how to alias various properties of an
	 * object.
	 */
//	public static void XMLExample() {
//		XStream xstream = new XStream();
//
//		Address address1 = new Address("Street1", "City1", "State1", "Country1", "Zip1");
//		Person p1 = new Person("PersonCode1", address1, "firstName", "lastName", "phoneNumber");
//		p1.addEmail("Email1");
//		p1.addEmail("Email2");
//		Person p2 = new Person("PersonCode1", address1, "firstName2", "lastName2", "phoneNumber2");
//		p2.addEmail("Email3");
//		p2.addEmail("Email4");
//		xstream.alias("person", Person.class);
//		PrintWriter pw = null;
//		try {
//			pw = new PrintWriter(new File("data/Person-example.xml"));
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//		pw.print("<persons>\n");
//		pw.print(xstream.toXML(p1) + "\n");
//		pw.print(xstream.toXML(p2) + "\n");
//		pw.print("</persons>" + "\n");
//		pw.close();
//
//		System.out.println("XML generated at 'data/Person-example.xml'");
//	}
}
