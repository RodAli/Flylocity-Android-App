package group787.utor.flylocity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import group787.utor.flylocity.driver.AppEngine;
import group787.utor.flylocity.flights.Flight;
import group787.utor.flylocity.flights.Itinerary;

/**
 * This acitivity represents the page where flights are searched using the
 * given criteria.
 */
public class ClientFlightSearchActivity extends AppCompatActivity {

    private AppEngine system;
    private String[] userInfo;
    private ArrayList<Itinerary> bookedItins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_flight_search);

        // Unpack information from previous activity
        Intent intent = getIntent();
        system = (AppEngine) intent.getSerializableExtra("system");
        userInfo = (String[]) intent.getSerializableExtra("userInformation");
        bookedItins = (ArrayList<Itinerary>) intent.getSerializableExtra("bookedItins");

        // Display all flights to the user
        TextView displayLabel = (TextView) findViewById(R.id.displayLabel);
        displayLabel.setText(system.allFlightsText());
    }

    /**
     * Method to uncheck the the other radio button when this is checked.
     * @param view View object such that method reacts to button click
     */
    public void radioCostButton(View view){
        RadioButton sortTimeButton = (RadioButton) findViewById(R.id.sortTimeButton);
        sortTimeButton.setChecked(false);
    }

    /**
     * Method to uncheck the other radio button when this is checked.
     * @param view View object such that method reacts to button click
     */
    public void radioTimeButton(View view){
        RadioButton sortCostButton = (RadioButton) findViewById(R.id.sortCostButton);
        sortCostButton.setChecked(false);
    }

    /**
     * Search through the flights that are stored in this system,
     * and also sort them to specification.
     * @param view View object to react on button click
     */
    public void searchFlights(View view){
        String printString = "";
        EditText dateEdit = (EditText) findViewById(R.id.dateEdit);
        EditText originEdit = (EditText) findViewById(R.id.originEdit);
        EditText destinationEdit = (EditText) findViewById(R.id.destinationEdit);
        // Get the search specifications from the user
        String date = dateEdit.getText().toString();
        String origin = originEdit.getText().toString();
        String destination = destinationEdit.getText().toString();
        RadioButton sortTimeButton = (RadioButton) findViewById(R.id.sortTimeButton);
        RadioButton sortCostButton = (RadioButton) findViewById(R.id.sortCostButton);

        // Check whether we are going to sort or not
        if (sortTimeButton.isChecked() || sortCostButton.isChecked()) {
            ArrayList<Flight> flightList;
            if (date.equals("") && origin.equals("")
                    && destination.equals("")) {
                flightList = system.getAllFlightsList();
            } else {
                flightList = system.getFlightList(date, origin, destination);
            }

            // Pack flights into itineraries
            ArrayList<Itinerary> sortList = new ArrayList<Itinerary>();
            for (Flight flight : flightList) {
                Itinerary newItin = new Itinerary();
                newItin.addFlight(flight);
                sortList.add(newItin);
            }

            // Sort the itineraries
            ArrayList<Itinerary> sortedList;
            if (sortCostButton.isChecked()){
                sortedList = system.sortByCost(sortList);
            }else{
                sortedList = system.sortByTime(sortList);
            }

            // Unpack the itineraries
            ArrayList<Flight> sortedFlightList = new ArrayList<Flight>();
            for (Itinerary itin : sortedList){
                Flight addFlight = itin.getItinerary().get(0);
                sortedFlightList.add(addFlight);
            }

            for (Flight flight : sortedFlightList){
                printString += flight.toString() + "\n";
            }
        }else{
            printString = system.searchFlights(date, origin, destination);
        }

        // Display sorted flights
        printString += "\n";
        TextView displayLabel = (TextView) findViewById(R.id.displayLabel);
        displayLabel.setText(printString);

    }

    /**
     * Go back to the previous acitivity.
     * @param view View object to react on button click
     */
    public void goBack(View view){
        // Check if it user or Admin and go to proper Activity
        if (userInfo[0].substring(0,5).equals("ADMIN")){
            Intent intent = new Intent(this, AdminActivity.class);
            intent.putExtra("userInformation", userInfo);
            intent.putExtra("system", system);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, ClientActivity.class);
            intent.putExtra("userInformation", userInfo);
            intent.putExtra("system", system);
            intent.putExtra("bookedItins", bookedItins);
            startActivity(intent);
        }
    }
}
