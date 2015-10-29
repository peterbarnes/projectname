package unl.cse.assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.joda.time.DateTime;

import com.airamerica.*;

public class DataLoader {
	
	protected static ArrayList<Person> persons;
	protected static ArrayList<Customer> customers;
	protected static ArrayList<Airport> airports;
	protected static ArrayList<Product> products;
	protected static ArrayList<Invoice> invoices;
	
	// Load data from dat files
	public static void loadDataFromPerson(){
		Scanner sc = null;

		try {
			sc = new Scanner (new File ("data/Persons.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int totalPersons = Integer.parseInt(sc.nextLine());
		persons = new ArrayList<Person>(totalPersons);
		
		while (sc.hasNext()) {
			String line       = sc.nextLine();
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
			persons.add(person);
		}
	}
	
	public static void loadDataFromCustomer(){
		Scanner sc = null;

		try {
			sc = new Scanner (new File ("data/Customers.dat"));
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
		int totalCustomers = Integer.parseInt(sc.nextLine());
		customers = new ArrayList<Customer>(totalCustomers);
		while (sc.hasNext()){
			String line         = sc.nextLine();
			String tokens[]     = line.split(";");
			String customerCode = tokens[0];
			String type         = tokens[1];
			String personCode   = tokens[2]; 
			
			Person primaryContact = null;
			for(Person p : persons){

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
			customers.add(customer);

		}
	}
	
	public static void loadDataFromAirport(){
		Scanner sc = null;

		try {
			sc = new Scanner (new File ("data/Airports.dat"));
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}

		int totalAirports = Integer.parseInt(sc.nextLine());
		airports = new ArrayList<Airport>(totalAirports);
		
		while (sc.hasNext()) {
			String line        = sc.nextLine();
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
			airports.add(airport);
		}
	}
	
	public static void loadDataFromProduct(){
		Scanner sc = null;
		try {
			sc = new Scanner (new File ("data/Products.dat"));
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}

		int totalProducts = Integer.parseInt(sc.nextLine());
		products = new ArrayList<Product>(totalProducts);
		
		while(sc.hasNext()){
			String line        = sc.nextLine();
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
				for(Airport a : airports){

					if(a.getAirportCode().equals(depAirportCode)){
						_depAirportCode = a;
						break;
					}

				}
				
				Airport _arrAirportCode = null;
				for(Airport a : airports){

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
				products.add(standard);

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
				for(Airport a : airports){

					if(a.getAirportCode().equals(depAirportCode)){
						_depAirportCode = a;
						break;
					}

				}
				
				Airport _arrAirportCode = null;
				for(Airport a : airports){

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
				products.add(offSeason);

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
				for(Airport a : airports){

					if(a.getAirportCode().equals(depAirportCode)){
						_depAirportCode = a;
						break;
					}

				}
				
				Airport _arrAirportCode = null;
				for(Airport a : airports){

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
				products.add(award);
				
			} else if(tokens[1].equals("SC")){
				int ticketCode = Integer.parseInt(tokens[2]);
				
				Product checkedBaggage = new CheckedBaggage(productCode, productType, ticketCode);
				
				products.add(checkedBaggage);

			} else if(tokens[1].equals("SI")){
				String name = tokens[2];
				String ageClass = tokens[3];
				double costPerMile = Double.parseDouble(tokens[4]);
				
				Product insurance = new Insurance(productCode, productType, name, ageClass, costPerMile);
				
				products.add(insurance);
				
			} else if(tokens[1].equals("SR")){

				String name = tokens[2];
				double cost = Double.parseDouble(tokens[3]);
				
				Product refreshments = new Refreshments(productCode, productType, name, cost);
				
				products.add(refreshments);

			}else if(tokens[1].equals("SS")){
				String typeOfService = tokens[2];
				
				Product specialAssistance = new SpecialAssistance(productCode, productType, typeOfService);
				
				products.add(specialAssistance);

			}

		}
	}

	public static void loadDataFromInvoice(){
		Scanner sc = null;
		
		try {
			sc = new Scanner( new File("data/Invoices.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int totalInvoices = Integer.parseInt(sc.nextLine());
		invoices = new ArrayList<Invoice>(totalInvoices);
		
		while (sc.hasNext()){
			String line = sc.nextLine();
			String tokens[] = line.split(";");
			String invoiceCode = tokens[0];
			
			String customerCode = tokens[1];
			Customer customer = searchCustomers(customerCode);
			
			String salespersonCode = tokens[2];
			Person salesperson = searchPersons(salespersonCode);
			
			String invoiceDate = tokens[3];
			String productsLine = tokens[4];
			String productDetails[] = productsLine.split(",");
			
			ArrayList<Product>invoiceProducts = new ArrayList<Product>(productDetails.length);
			for(int i = 0; i < productDetails.length; i++){
				String productTokens[] = productDetails[i].split(":");
				String productCode = productTokens[0];
				Product product = searchProducts(productCode);
				
				if(product instanceof Ticket){
					DateTime travelDate = DateTime.parse(productTokens[1]);
					((Ticket) product).setTravelDate(travelDate);
					int noOfPass = Integer.parseInt(productTokens[2]);
					int counter = 0;
					for(int j = 0; j < noOfPass * 5; j+=5){
						
						String seat = productTokens[j + 3];
						
						Person person = searchPersons(productTokens[j + 4]);
						
						String identityNumber = productTokens[j + 5];
						
						String age = productTokens[j + 6];
						
						counter = j + 7;
						String nationality = productTokens[counter];
						Passenger passenger = new Passenger(seat, identityNumber, age, nationality, person);
						
						((Ticket) product).addPassenger(passenger);
					}
					
					String ticketNote = productTokens[counter + 1];
					((Ticket) product).setTicketNote(ticketNote);
				} else if(product instanceof Insurance){
					
					String noOfInsurance = productTokens[1];
					String ticketCode = productTokens[2];
					Ticket ticket = (Ticket) searchProducts(ticketCode);
					
					((Insurance) product).setNoOfInsurance(noOfInsurance);
					((Insurance) product).setTicket(ticket);
					
				} else if(product instanceof CheckedBaggage){
					
					String noOfBaggage = productTokens[1];
					((CheckedBaggage) product).setNoOfBaggage(noOfBaggage);
					
				} else if(product instanceof Refreshments){
					
					String noOfRefreshments = productTokens[1];
					((Refreshments) product).setNoOfRefreshments(noOfRefreshments);
					
				} else if(product instanceof SpecialAssistance){
					Person person = searchPersons(productTokens[1]);
					((SpecialAssistance) product).setPerson(person);
				}
				invoiceProducts.add(product);
			}
			
			Invoice invoice = new Invoice(invoiceCode, customer, salesperson, invoiceDate, invoiceProducts);
			invoices.add(invoice);
		}
		
	}
	
	public static void loadData(){
		loadDataFromPerson();
		loadDataFromCustomer();
		loadDataFromAirport();
		loadDataFromProduct();
		loadDataFromInvoice();
	}
	
	// Return populated ArrayLists
	public static ArrayList<Person> getPersons(){
		loadDataFromPerson();
		return persons;
	}
	
	public static ArrayList<Customer> getCustomers(){
		loadDataFromCustomer();
		return customers;
	}
	
	public static ArrayList<Airport> getAirports(){
		loadDataFromAirport();
		return airports;
	}
	
	public static ArrayList<Product> getProducts(){
		loadDataFromProduct();
		return products;
	}
	
	public static ArrayList<Invoice> getInvoices(){
		loadDataFromInvoice();
		return invoices;
	}

	// Searching methods
	public static Person searchPersons(String code){
		Person person = null;
		for(Person p : persons){
			if(p.getPersonCode().equals(code)){
				person = p;
			}
		}
		return person;
	}

	public static Customer searchCustomers(String code){
		Customer customer = null;
		for(Customer c : customers){
			if(c.getCustomerCode().equals(code)){
				customer = c;
			}
		}
		return customer;
	}

	public static Airport searchAirports(String code){
		Airport airport = null;
		for(Airport a : airports){
			if(a.getAirportCode().equals(code)){
				airport = a;
			}
		}
		return airport;
	}

	public static Product searchProducts(String code){
		Product product = null;
		for(Product p : products){
			if(p.getProductCode().equals(code)){
				if(p instanceof StandardTicket){
					product = new StandardTicket(p.getProductCode(), p.getProductType(), ((Ticket) p).getDepAirportCode(), ((Ticket) p).getArrAirportCode(), ((Ticket) p).getDepTime(), ((Ticket) p).getArrTime(), ((Ticket) p).getFlightNo(), ((Ticket) p).getFlightClass(), ((Ticket) p).getAirCraftType());
					Ticket newProduct = (Ticket) product;
					newProduct.setPassengers(((StandardTicket) p).getPassengers());
					product = (Ticket) newProduct;
				} else if(p instanceof OffSeasonTicket){
					product = new OffSeasonTicket(p.getProductCode(), p.getProductType(), ((OffSeasonTicket) p).getSeasonStartDate(), ((OffSeasonTicket) p).getSeasonEndDate(),((Ticket) p).getDepAirportCode(), ((Ticket) p).getArrAirportCode(), ((Ticket) p).getDepTime(), ((Ticket) p).getArrTime(), ((Ticket) p).getFlightNo(), ((Ticket) p).getFlightClass(), ((Ticket) p).getAirCraftType(), ((OffSeasonTicket) p).getRebate());
					
				} else if(p instanceof AwardTickets){
					product = new AwardTickets(p.getProductCode(), p.getProductType(), ((Ticket) p).getDepAirportCode(), ((Ticket) p).getArrAirportCode(), ((Ticket) p).getDepTime(), ((Ticket) p).getArrTime(), ((Ticket) p).getFlightNo(), ((Ticket) p).getFlightClass(), ((Ticket) p).getAirCraftType(), ((AwardTickets) p).getPointsPerMile());
					Ticket newProduct = (Ticket) product;
					newProduct.setPassengers(((AwardTickets) p).getPassengers());
					product = (Ticket) newProduct;
				} else if(p instanceof CheckedBaggage){
					product = new CheckedBaggage(p.getProductCode(), p.getProductType(), ((CheckedBaggage) p).getTicketCode());
				} else if(p instanceof Insurance){
					product = new Insurance(p.getProductCode(), p.getProductType(), ((Insurance) p).getName(), ((Insurance) p).getAgeClass(), ((Insurance) p).getCostPerMile());
				} else if(p instanceof Refreshments){
					product = new Refreshments(p.getProductCode(), p.getProductType(), ((Refreshments) p).getName(), ((Refreshments) p).getCost());
				} else if(p instanceof SpecialAssistance){
					product = new SpecialAssistance(p.getProductCode(), p.getProductType(), ((SpecialAssistance) p).getTypeOfService());
				}
			}
		}
		return product;
	}

	public static Invoice searchInvoices(String code){
		Invoice invoice = null;
		for(Invoice i : invoices){
			if(i.getInvoiceCode().equals(code)){
				invoice = i;
			}
		}
		return invoice;
	}
	
}
