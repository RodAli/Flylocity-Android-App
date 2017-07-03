package group787.utor.flylocity.flights;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * A representation of a single flight with the appropriate characteristics 
 * including origin and destination.
 */
public class Flight implements Serializable {
	
	// Information on a specific flight to be stored for this instance
	private String flightNumber;
	private String[] departureDateandTime;
	private String[] arrivalDateandTime;
	private String airline;
	private String origin;
	private String destination;
	private BigDecimal cost;
	private int numSeats;
	
	/**
	 * Create an instance of a Flight.
	 * @param flightnumber the flight number of this Flight
	 * @param deperatureDateandTime the string array containing departure 
	 * date and time
	 * @param arrivalDateandTime the string array containing arrival date 
	 * and time
	 * @param airline the airline of this Flight
	 * @param origin the origin of this Flight
	 * @param destination the destination of this Flight
	 * @param cost the price of this Flight as a BigDecimal
	 */
	public Flight(String flightnumber, String[] deperatureDateandTime, 
			String[] arrivalDateandTime, String airline, String origin, 
			String destination, BigDecimal cost, int numSeats) {
		
		this.flightNumber = flightnumber;
		this.departureDateandTime = deperatureDateandTime;
		this.arrivalDateandTime = arrivalDateandTime;
		this.airline = airline;
		this.origin = origin;
		this.destination = destination;
		this.cost = cost;
		this.numSeats = numSeats;
	}

	/**
	 * Returns the flight number of this Flight.
	 * @return flight number of this Flight
	 */
	public String getFlightnumber() {
		return this.flightNumber;
	}
	
	/**
	 * Changes the flight number of this Flight.
	 * @param flightnumber of this Flight
	 */
	public void setFlightnumber(String flightnumber) {
		this.flightNumber = flightnumber;
	}
	
	/**
	 * Get the departure date and time of this flight.
	 * @return array of the departure date and time
	 */
	public String[] getDepartureDateandTime() {
		return departureDateandTime;
	}
	
	/**
	 * Set the departure date and time of this flight.
	 * @param departureDateandTime array of the departure date and time
	 */
	public void setDepartureDateandTime(String[] departureDateandTime) {
		this.departureDateandTime = departureDateandTime;
	}
	
	/**
	 * Get the arrival date and time of this flight.
	 * @return array of the arrival date and time of this flight
	 */
	public String[] getArrivalDateandTime() {
		return arrivalDateandTime;
	}

	/**
	 * Set arrival date and time of this flight.
	 * @param arrivalDateandTime array of the arrival date and time 
	 * of this flight
	 */
	public void setArrivalDateandTime(String[] arrivalDateandTime) {
		this.arrivalDateandTime = arrivalDateandTime;
	}
	
	/**
	 * Return the GregorianCalendar object of the departure
	 * date of this flight.
	 * @return Gregorian Calendar object of the departure date and time
	 */
	public GregorianCalendar getDepartureCalendar(){
		String[] departDate = this.departureDateandTime[0].split("-");
		String[] departTime = this.departureDateandTime[1].split(":");
		int year = Integer.parseInt(departDate[0]);
		int month = Integer.parseInt(departDate[1]);
		int day = Integer.parseInt(departDate[2]);
		int hour = Integer.parseInt(departTime[0]);
		int min = Integer.parseInt(departTime[1]);
		GregorianCalendar calendar = 
				new GregorianCalendar(year, month, day, hour, min);
		return calendar;
	}
	
	/**
	 * Return the GregorianCalendar object of the arrival
	 * date of this flight.
	 * @return Gregorian Calendar object of the arrival date and time
	 */
	public GregorianCalendar getArrivalCalendar(){
		String[] arrivalDate = this.arrivalDateandTime[0].split("-");
		String[] arrivalTime = this.arrivalDateandTime[1].split(":");
		int year = Integer.parseInt(arrivalDate[0]);
		int month = Integer.parseInt(arrivalDate[1]);
		int day = Integer.parseInt(arrivalDate[2]);
		int hour = Integer.parseInt(arrivalTime[0]);
		int min = Integer.parseInt(arrivalTime[1]);
		GregorianCalendar calendar = 
				new GregorianCalendar(year, month, day, hour, min);
		return calendar;
	}

	/**
	 * Returns name of the airline of this Flight.
	 * @return the airline name of this Flight
	 */
	public String getAirline() {
		return this.airline;
	}

	/**
	 * Changes the name of the airline of this Flight.
	 * @param airline the name of the airline of this Flight
	 */
	public void setAirline(String airline) {
		this.airline = airline;
	}

	/**
	 * Gets the origin of this Flight.
	 * @return the origin location of this Flight
	 */
	public String getOrigin() {
		return this.origin;
	}

	/**
	 * Changes the origin of this Flight.
	 * @param origin location of this Flight
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * Returns the destination of this Flight
	 * @return destination
	 */
	public String getDestination() {
		return this.destination;
	}

	/**
	 * Changes the destination of this Flight.
	 * @param destination location of this Flight
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * Returns the cost of this Flight.
	 * @return cost of this Flight
	 */
	public BigDecimal getCost() {
		return cost;
	}

	/**
	 * Changes the cost of this Flight.
	 * @param cost of this Flight
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	/**
	 * Return the number of seats this flight has.
	 * @return integer of the number of seats in this flight
	 */
	public int getNumSeats(){
		return this.numSeats;
	}

	/**
	 * Set the number of seats in this flight.
	 * @param numSeats number of seats to be set in this flight
	 */
	public void setNumSeats(int numSeats){
		this.numSeats = numSeats;
	}
	
	/**
	 * Return the string representation of this flight,
	 * in the format:
	 * 	Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,
	 *         Destination,Price
	 */
	@Override
	public String toString() {
		String returnString = "";
		returnString += this.flightNumber + ",";
		returnString += this.departureDateandTime[0] + " ";
		returnString += this.departureDateandTime[1] + ",";
		returnString += this.arrivalDateandTime[0] + " ";
		returnString += this.arrivalDateandTime[1] + ",";
		returnString += this.airline + ",";
		returnString += this.origin + ",";
		returnString += this.destination + ",";
		returnString += this.cost.toString() + ",";
		returnString += this.numSeats;
		return returnString;
	}
}
