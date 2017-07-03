package group787.utor.flylocity.driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import group787.utor.flylocity.flights.Flight;

/**
 * A representation of the algorithm used to generate itineraries for the
 * Flight Application.
 * @author Robertson
 *
 */
public class ItineraryGenerator {

    //
    private ArrayList<ArrayList<String>> tempResult
            = new ArrayList<ArrayList<String>>();

    //
    private ArrayList<ArrayList<Flight>> result
            = new ArrayList<ArrayList<Flight>>();

    //
    private Collection<Flight> flights;

    /**
     * Creates a new algorithm object with various properties designed to handle
     * flights and their information.
     * @param date represented by a a GregorianCalendar object
     * @param origin in the form of a String
     * @param destination in the form of a String
     * @param fl a Hashmap containing the flights stored in this application
     * @return
     */
    public ArrayList<ArrayList<Flight>> search(GregorianCalendar date,
                                               String origin, String destination, HashMap<String, Flight> fl) {

        flights = fl.values();

        //Creates a HashMap with the feature that every String has a
        //set of Strings associated with it
        HashMap<String, HashSet<String>> map = new HashMap<String,
                HashSet<String>>(flights.size());
        for (Flight fly : flights) {
            HashSet<String> temp = new HashSet<String>();
            map.put(fly.getOrigin(), temp);
        }
        for (Flight fly : flights) {
            map.get(fly.getOrigin()).add(fly.getDestination());
        }

        //Creates a new Graph with edges from each location corresponding to
        //flights
        Graph graph = new Graph();
        for (String start : map.keySet()) {
            for (String end : map.get(start)) {
                graph.addEdge(start, end);
            }
        }

        //Creates a bunch of linked lists representing possible itineraries
        LinkedList<String> visited = new LinkedList<String>();
        visited.add(origin);
        recursiveTrace(graph,visited,destination);

        //Changes the list of places into a list of flights between the places
        tempToFlights(date);

        //Checks whether the itineraries have the correct departure dates as
        //specified by user and adds it to a new ArrayList
        ArrayList<ArrayList<Flight>> finalResult
                = new ArrayList<ArrayList<Flight>>();
        for (ArrayList<Flight> poss : result) {
            if (poss.size() > 0){
                if (poss.get(0).getDepartureCalendar().get
                        (GregorianCalendar.DAY_OF_MONTH) == date.get
                        (GregorianCalendar.DAY_OF_MONTH)
                        && poss.get(0).getDepartureCalendar().get
                        (GregorianCalendar.MONTH) == date.get
                        (GregorianCalendar.MONTH)
                        && poss.get(0).getDepartureCalendar().get
                        (GregorianCalendar.YEAR) == date.get
                        (GregorianCalendar.YEAR)) {
                    finalResult.add(poss);
                }
            }
        }

        return finalResult;
    }

    /**
     *
     */
    private void tempToFlights(GregorianCalendar date) {
        for (ArrayList<String> series : tempResult) {

            ArrayList<ArrayList<Flight>> possibleItinerary = new ArrayList<ArrayList<Flight>>();

            String[] places = new String[series.size()];
            places = series.toArray(places);
            for (int i = 0; i < places.length-1; i ++) {
                boolean flag = true;
                possibleItinerary.add(new ArrayList<Flight>());
                for (Flight flight : flights) {
                    if (flight.getOrigin().equals(places[i])
                            && flight.getDestination().equals(places[i+1])
                            && flag ) {
                        if (possibleItinerary.get(i).size() > 1){
                            if (flight.getDepartureCalendar().getTimeInMillis()
                                    > possibleItinerary.get
                                    (possibleItinerary.size() - 1).get(i).
                                    getArrivalCalendar().getTimeInMillis()){
                                possibleItinerary.get(i).add(flight);
                            }
                        }else{
                            possibleItinerary.get(i).add(flight);
                        }
                    }
                }
            }
            if (possibleItinerary.size() != (places.length - 1)){
                possibleItinerary.removeAll(possibleItinerary);
            }
            if (places.length == 2){
                for (int i = 0; i < possibleItinerary.size(); i ++){
                    for (int j = 0; j < possibleItinerary.get(i).size(); j ++){
                        ArrayList<Flight> genItinerary = new ArrayList<Flight>();
                        genItinerary.add(possibleItinerary.get(i).get(j));
                        result.add(genItinerary);
                    }
                }
            }else{
                ArrayList<Flight> genItinerary = new ArrayList<Flight>();
                ArrayList<ArrayList<Flight>> allPossibilities = new ArrayList<ArrayList<Flight>>();
                for (int i = 0; i < possibleItinerary.size(); i ++){
                    ArrayList<Flight> alternativeFlight = new ArrayList<Flight>();
                    if (possibleItinerary.get(i).size() > 1){
                        for (int j = 0; j < possibleItinerary.get(i).size(); j ++){
                            alternativeFlight.add(possibleItinerary.get(i).get(j));
                            genItinerary.add(alternativeFlight.get(0));
                            ArrayList<Flight> singleItinerary = new ArrayList<Flight>();
                            for (Flight flight: genItinerary){
                                singleItinerary.add(flight);
                            }
                            allPossibilities.add(singleItinerary);
                            alternativeFlight.remove(alternativeFlight.size() - 1);
                            genItinerary.remove(genItinerary.size() - 1);
                        }
                    }else{
                        genItinerary.add(possibleItinerary.get(i).get(0));
                    }
                }
                if (allPossibilities.size() > 0){
                    for (int k = 0; k < allPossibilities.size(); k ++){
                        result.add(allPossibilities.get(k));
                    }
                }else{
                    result.add(genItinerary);
                }
            }
        }
    }

    /**
     *
     * @param graph
     * @param visited
     * @param destination
     */
    private void recursiveTrace(Graph graph, LinkedList<String> visited,
                                String destination) {
        LinkedList<String> nodes = graph.adjacentNodes(visited.getLast());

        //Checks if we are at our destination, if we are we call agglomerate
        for (String node: nodes) {
            if (visited.contains(node)) {
                continue;
            }
            if (node.equals(destination)) {
                visited.add(node);
                agglomerate(visited);
                visited.removeLast();
                break;
            }
        }

        //If we are not at our destination, we recursively call this
        //recursiveTrace method
        for (String node : nodes) {
            if (visited.contains(node) || node.equals(destination)) {
                continue;
            }
            visited.addLast(node);
            recursiveTrace(graph, visited, destination);
            visited.removeLast();
        }
    }

    /**
     *
     * @param visited
     */
    private void agglomerate(LinkedList<String> visited) {
        //Adds the possible path to the temporary result
        ArrayList<String> temp = new ArrayList<String>();
        for (String node: visited) {
            temp.add(node);
        }
        tempResult.add(temp);
    }
}
