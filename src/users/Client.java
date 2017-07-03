package group787.utor.flylocity.users;

import java.io.Serializable;
import java.util.ArrayList;
import group787.utor.flylocity.flights.Itinerary;

/**
 * A Client that can store personal information and interact 
 * with the application.
 */
public class Client implements Serializable {
	
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String ccNumber;
	private String ccExpiry;
	private ArrayList<Itinerary> bookedItins = new ArrayList<Itinerary>();
	
	/**
	 * Create an instance of Client with all fields.
	 * @param firstName string of the first name of the client
	 * @param lastName string of the last name of the client
	 * @param email the string email of this client
	 * @param address string address of the client
	 * @param ccNumber credit card number of this client
	 * @param ccExpiry security code of the credit card info of this client
	 */
	public Client(String lastName,String firstName, String email, 
			String address, String ccNumber, String ccExpiry) {
		
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.address = address;
		this.ccNumber = ccNumber;
		this.ccExpiry = ccExpiry;
	}

	/**
	 * Get the first name of this Client.
	 * @return the first name of this Client
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set the first name of this Client.
	 * @param firstName the first name of this Client
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get the last name of this Client.
	 * @return the last name of this Client
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set the last name of this Client.
	 * @param lastName the last name of this Client
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Return the email of this Client.
	 * @return the email of this Client.
	 */
	public String getEmail(){
		return this.email;
	}
	
	/**
	 * Changes the email of this Client.
	 * @param email of this Client.
	 */
	public void setEmail(String email){
		this.email = email;
	}

	/**
	 * Returns the address of this client.
	 * @return the address of this client
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Changes the address of this Client.
	 * @param address the address of this Client
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Get the credit card number for this Client.
	 * @return the credit card number of this Client
	 */
	public String getCcNumber() {
		return ccNumber;
	}
	
	/**
	 * Set the credit card number for this Client.
	 * @param ccNumber
	 */
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	
	/**
	 * Get the credit card expiry date for this Client.
	 * @return the credit card expiry date for this Client
	 */
	public String getCcExpiry() {
		return ccExpiry;
	}
	
	/**
	 * Set the credit card expiry date for this Client.
	 * @param ccExpiry credit card expiry date for this Client
	 */
	public void setCcExpiry(String ccExpiry) {
		this.ccExpiry = ccExpiry;
	}
	
	/**
	 * Takes an itinerary and stores it in the clients booked itineraries
	 * @param itin the Itinerary to be booked by this user.
	 */
	public void bookItin(Itinerary itin){
		this.bookedItins.add(itin);
	}

	/**
	 * Return the list of booked Itineraries this Client has booked.
	 * @return the list of booked Itineraries for this Client
	 */
	public ArrayList<Itinerary> getBookedItins(){
		return this.bookedItins;
	}
	
	/**
	 * Return String representation of this Client in the format:
	 * 		LastName,FirstNames,Email,Address,CreditCardNumber,ExpiryDate
	 */
	@Override
	public String toString() {
		String formatString = "";
		formatString += this.lastName + ",";
		formatString += this.firstName + ",";
		formatString += this.email + ",";
		formatString += this.address + ",";
		formatString += this.ccNumber + ",";
		formatString += this.ccExpiry;
		return formatString;
	}

}
