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
	
	public static ArrayList<Person> personList = new ArrayList<Person>();
	public static ArrayList<Airport> airportList = new ArrayList<Airport>();
	public static ArrayList<Customer> customerList = new ArrayList<Customer>();
	public static ArrayList<Product> productList = new ArrayList<Product>();
	
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

	public static void main(String args[]) {
		
		/*THE METHOD USED TO READ THE FILE AND SPLIT THE FILE AT EVERY ";" IS THE SAME FOR EVERY CLASS, THEREFORE IT WILL ONLY BE COMMENTED ONCE*/

		/*PERSONS*/
		Scanner persons = null;

		try {
			persons = new Scanner (new File ("data/Persons.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Grabs total number of lines in file, and uses it to create an arraylist
		int totalPersons = persons.nextInt();
		String blank = persons.nextLine();
		
		// Splitting the dat file at every ";" and populating a token array
		while (persons.hasNext()) {
			String line       = persons.nextLine();
			String tokens[]   = line.split(";");
			String personCode = tokens[0];

			String name[]    = tokens[1].split(",");
			String lastName  = name[0];
			String firstName = name[1];

			String address[]   = tokens[2].split(",");
			String street      = address[0];
			String city        = address[1];
			String state       = address[2];
			String zip         = address[4];
			String country     = address[3];
			Address newAddress = new Address(street, 
										     city, 
										     state, 
										     zip, 
										     country);

			String phoneNum = tokens[3];
			Person person = new Person(personCode, 
									   newAddress, 
									   firstName, 
									   lastName, 
									   phoneNum);
			// If the particular line has an email, split it and assign it to the class attributes
			if(tokens.length == 5){
				String emailArray[] = tokens[4].split(",");
				for(int i = 0; i < emailArray.length; i++){
					person.addEmail(emailArray[i]); 

				}

			}
			// Adds object to arraylist
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
		String blank1 = airports.nextLine();
		while (airports.hasNext()) {
			String line        = airports.nextLine();
			String tokens[]    = line.split(";");
			String airportCode = tokens[0];
			String name        = tokens[1];

			String address[]   = tokens[2].split(",");
			String street      = address[0];
			String city        = address[1];
			String state       = address[2];
			String zip         = address[3];
			String country     = address[4];
			Address newAddress = new Address(street, 
											 city, 
											 state, 
											 zip, 
											 country);

			String coordinates[] = tokens[3].split(",");
			int latitudeDegs  = Integer.parseInt(coordinates[0]);
			int latitudeMins  = Integer.parseInt(coordinates[1]);
			int longitudeDegs = Integer.parseInt(coordinates[2]);
			int longitudeMins = Integer.parseInt(coordinates[3]);

			double passengerFacilityFee = Double.parseDouble(tokens[4]);

			Airport airport = new Airport(airportCode, 
										         name, 
										         newAddress, 
										         latitudeDegs, 
										         latitudeMins, 
										         longitudeDegs, 
										         longitudeMins, 
										         passengerFacilityFee);
			// Adds object to arraylist
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
		String blank2 = customers.nextLine();
		while (customers.hasNext()){
			String line         = customers.nextLine();
			String tokens[]     = line.split(";");
			String customerCode = tokens[0];
			String type         = tokens[1];
			String personCode   = tokens[2]; 
			
			Person primaryContact = null;
			for(Person p : personList){

				if(p.getPersonCode().equals(personCode)){
					primaryContact = p;
					break;
				}

			}

			String customerName = tokens[3];
			String airlineMiles = "";
			if(tokens.length == 5) {
				airlineMiles = tokens[4];
			} 
			Customer customer = new Customer(customerCode, type, primaryContact, customerName, airlineMiles);
			// Adds object to arraylist
			customerList.add(customer);

		}

		/*PRODUCTS*/
		Scanner products = null;
		try {
			products = new Scanner (new File ("data/Products.dat"));
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}

		int totalProducts = products.nextInt();
		System.out.println(totalProducts);
		String blank3 = products.nextLine();
		
		while(products.hasNext()){
			String line        = products.nextLine();
			String tokens[]    = line.split(";");
			String productCode = tokens[0];
			String productType = tokens[1];

			if(tokens[1].equals("TS")){
				String depAirportCode = tokens[2];
				String arrAirportCode = tokens[3];
				String depTime        = tokens[4];
				String arrTime        = tokens[5];
				String flightNo       = tokens[6];
				String flightClass    = tokens[7];
				String airCraftType   = tokens[8];
				
				// References airportList to assign to class attribute
				Airport _depAirportCode = null;
				for(Airport a : airportList){

					if(a.getAirportCode().equals(depAirportCode)){
						_depAirportCode = a;
						break;
					}

				}
				
				Airport _arrAirportCode = null;
				for(Airport a : airportList){

					if(a.getAirportCode().equals(arrAirportCode)){
						_arrAirportCode = a;
						break;
					}

				}
				
				Product standard = new StandardTicket(productCode, 
													  productType, 
													  _depAirportCode, 
													  _arrAirportCode, 
													  depTime, 
													  arrTime, 
													  flightNo, 
													  flightClass,
													  airCraftType);
				productList.add(standard);

			} else if(tokens[1].equals("TO")){
				String seasonStartDate = tokens[2];
				String seasonEndDate   = tokens[3];
				String depAirportCode  = tokens[4];
				String arrAirportCode  = tokens[5];
				String depTime         = tokens[6];
				String arrTime         = tokens[7];
				String flightNo        = tokens[8];
				String flightClass     = tokens[9];
				String airCraftType    = tokens[10];
				double rebate          = Double.parseDouble(tokens[11]);
				
				Airport _depAirportCode = null;
				for(Airport a : airportList){

					if(a.getAirportCode().equals(depAirportCode)){
						_depAirportCode = a;
						break;
					}

				}
				
				Airport _arrAirportCode = null;
				for(Airport a : airportList){

					if(a.getAirportCode().equals(arrAirportCode)){
						_arrAirportCode = a;
						break;
					}

				}

				Product offSeason = new OffSeasonTicket(productCode, 
						  									  productType, 
						  									  seasonStartDate, 
						  									  seasonEndDate, 
						  									  _depAirportCode, 
						  									  _arrAirportCode, 
						  									  depTime, 
						  									  arrTime, 
						  									  flightNo, 
						  									  flightClass, 
						  									  airCraftType, 
						  									  rebate); 
				productList.add(offSeason);

			} else if(tokens[1].equals("TA")){

				String depAirportCode = tokens[2];
				String arrAirportCode = tokens[3];
				String depTime        = tokens[4];
				String arrtime        = tokens[5];
				String flightNo       = tokens[6];
				String flightClass    = tokens[7];
				String airCraftType   = tokens[8];
				int pointsPerMile     = Integer.parseInt(tokens[9]);
				
				Airport _depAirportCode = null;
				for(Airport a : airportList){

					if(a.getAirportCode().equals(depAirportCode)){
						_depAirportCode = a;
						break;
					}

				}
				
				Airport _arrAirportCode = null;
				for(Airport a : airportList){

					if(a.getAirportCode().equals(arrAirportCode)){
						_arrAirportCode = a;
						break;
					}

				}
				
				Product award = new AwardTickets(productCode, 
												 productType, 
												 _depAirportCode, 
												 _arrAirportCode, 
												 depTime, 
												 arrtime, 
												 flightNo, 
												 flightClass, 
												 airCraftType, 
												 pointsPerMile);
				productList.add(award);
				
			} else if(tokens[1].equals("SC")){
				int ticketCode = Integer.parseInt(tokens[2]);
				
				Product checkedBaggage = new CheckedBaggage(productCode, productType, ticketCode);
				
				productList.add(checkedBaggage);

			} else if(tokens[1].equals("SI")){
				String name = tokens[2];
				String ageClass = tokens[3];
				double costPerMile = Double.parseDouble(tokens[4]);
				
				Product insurance = new Insurance(productCode, productType, name, ageClass, costPerMile);
				
				productList.add(insurance);
				
			} else if(tokens[1].equals("SR")){

				String name = tokens[2];
				double cost = Double.parseDouble(tokens[3]);
				
				Product refreshments = new Refreshments(productCode, productType, name, cost);
				
				productList.add(refreshments);

			}else if(tokens[1].equals("SS")){
				String typeOfService = tokens[2];
				
				Product specialAssistance = new SpecialAssistance(productCode, productType, typeOfService);
				
				productList.add(specialAssistance);

			}

		}
		
		// Running the output methods
		outputXMLFileForPersons(personList);
		outputXMLFileForCustomers(customerList);
		outputXMLFileForAirports(airportList);
		outputXMLFileForProducts(productList);
		
		System.out.println("Shit worked");
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
