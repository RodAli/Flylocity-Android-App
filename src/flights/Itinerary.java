package group787.utor.flylocity.flights;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * An Itinerary to store multiple instances of Flight, 
 * with methods to get total cost and total flight time.
 */
public class Itinerary implements Serializable {
	
	private ArrayList<Flight> itinerary;
	
	/**
	 * Creates an instance of Itinerary.
	 */
	public Itinerary() {
		this.itinerary = new ArrayList<Flight>();
	}

	/**
	 * Returns the flights in the itinerary in an ArrayList<Flight>.
	 * @return all the flights in the itinerary
	 */
	public ArrayList<Flight> getItinerary() {
		return this.itinerary;
	}

	/**
	 * Changes the entire set of flights to another set.
	 * @param itinerary an ArrayList<Flights> to be new Itinerary
	 */
	public void setItinerary(ArrayList<Flight> itinerary) {
		this.itinerary = itinerary;
	}
	
	/**
	 * Adds a flight to this Itinerary. 
	 * @param flight to be added to this Itinerary. 
	 */
	public void addFlight(Flight flight){
		this.itinerary.add(flight);
	}
	
	/**
	 * Returns the total cost of the flights in the itinerary.
	 * @return the total cost of the Itinerary. 
	 */
	public BigDecimal getTotalCost(){
		BigDecimal totalCost = new BigDecimal(0.0);
		for (Flight flight : this.itinerary){
			totalCost = totalCost.add(flight.getCost());
		}
		// Round the decimal to 2 decimal places
		totalCost = totalCost.setScale(2, RoundingMode.CEILING);
		return totalCost;
	}
	
	/**
	 * Returns the total flight time from the first flight to the last 
	 * flight as double in milliseconds.
	 * @return the total time of the itinerary as a double
	 */
	@SuppressWarnings("deprecation")
	public double getDifferenceFlightTime(){
		// If there are no flights return 00:00 total flight time 
		if (this.itinerary.size() == 0){
			double zero = (double) 0;
			return zero;
		}
		
		GregorianCalendar depart;
		GregorianCalendar arrive;
		
		// Find time difference in one flight if there is one flight
		// Find time difference in first and last flight if there
		// are more than one flight
		double difference = 0.0;
		if (this.itinerary.size() == 1){
			Flight flight = this.itinerary.get(0);
			depart = flight.getDepartureCalendar();
			arrive = flight.getArrivalCalendar();
			if ((flight.getArrivalCalendar().getTime().getYear() != flight.getDepartureCalendar().getTime().getYear()) ||
					(flight.getArrivalCalendar().getTime().getMonth() != flight.getDepartureCalendar().getTime().getMonth())||
					(flight.getArrivalCalendar().getTime().getMonth() != flight.getDepartureCalendar().getTime().getMonth())){
				difference -= 86400000;

			}
		}else{
			Flight firstFlight = this.itinerary.get(0);
			Flight lastFlight = this.itinerary.get(
					this.itinerary.size() - 1);
			depart = firstFlight.getDepartureCalendar();
			arrive = lastFlight.getArrivalCalendar();
			if ((firstFlight.getArrivalCalendar().getTime().getYear() != lastFlight.getDepartureCalendar().getTime().getYear()) ||
					(firstFlight.getArrivalCalendar().getTime().getMonth() != lastFlight.getDepartureCalendar().getTime().getMonth())||
					(firstFlight.getArrivalCalendar().getTime().getMonth() != lastFlight.getDepartureCalendar().getTime().getMonth())){
				difference -= 86400000;

			}
		}
		
		// Find the difference in milliseconds
		difference += (arrive.getTimeInMillis() - 
				depart.getTimeInMillis());
		return difference;
	}
	
	/**
	 * Return the string representation of the total flight time of this 
	 * itinerary from the first flight to the last flight.
	 * @return the string representation of the flight time of this itinerary
	 */
	public String getTotalFlightTime(){
		// Get the difference as double in milliseconds
		double difference = getDifferenceFlightTime();

		// Convert milliseconds to hours and minutes
		int hours = (int)(difference/3600000);
		int minutes = (int)((difference%3600000) / 60000);
		String hoursString = Integer.toString(hours);
		String minString = Integer.toString(minutes);
		
		// Format the hours and minutes properly to a string
		if (hoursString.length() == 1){
			hoursString = "0" + hoursString;
		}
		if (minString.length() == 1){
			minString = "0" + minString;
		}
		
		return hoursString + ":" + minString;
	}

	/**
	 * Returns whether or not this itinerary is not full (has 1 or more
	 * available seats in each flight).
	 * @return true if this Itinerary has a full flight. False otherwise
	 */
	public boolean containsEmptyFlight(){
		boolean hasEmpty = false;
		for (Flight flight : this.itinerary){
			if (flight.getNumSeats() < 1){
				hasEmpty = true;
			}
		}
		return  hasEmpty;
	}
	
	/**
	 * Returns the String representation of this Itinerary in the format:
	 * Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,
	 *         Destination for each flight 
	 *         followed by total price (on its own line, exactly two
	 *         decimal places), followed by total duration (on its own line, 
	 *         in format HH:MM).
	 */
	@Override
	public String toString() {
		String returnString = "";
		for (Flight flight: this.itinerary){
			returnString += flight.toString().substring(0, flight.toString().lastIndexOf(",")) + "\n";
		}
		returnString += this.getTotalCost().toString() + "\n";
		returnString += this.getTotalFlightTime() + "\n";
		return returnString;
	}
	
	
	
}
