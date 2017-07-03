package group787.utor.flylocity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import group787.utor.flylocity.driver.AppEngine;
import group787.utor.flylocity.flights.Itinerary;
import group787.utor.flylocity.users.Client;

/**
 * This acitivity represents the page where itineraries are searched using the
 * given criteria.
 */
public class ClientItinerarySearchActivity extends AppCompatActivity {

    private AppEngine system;
    private String[] userInfo;
    private ArrayList<Itinerary> bookedItins;
    private ArrayList<Itinerary> selectionItinerary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_search_itinerary);

        // Unpack information from previous acitivity
        Intent intent = getIntent();
        system = (AppEngine) intent.getSerializableExtra("system");
        userInfo = (String[]) intent.getSerializableExtra("userInformation");
        bookedItins = (ArrayList<Itinerary>) intent.getSerializableExtra("bookedItins");
    }

    /**
     * Go back to the previous activity.
     * @param view View object to react on button click
     */
    public void goBack(View view){
        /*Check whether it is user or admin to decide which
           activity to go to*/
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

    /**
     * Sorts the itineraries stored, by their cost
     * @param view View object to react on button click
     */
    public void sortByCost(View view){
        // Only allow a user to book a itinerary for them self
        if (!userInfo[0].substring(0,5).equals("ADMIN")) {
            Button button = (Button) findViewById(R.id.bookButton);
            button.setEnabled(true);
        }
        EditText dateEditText = (EditText) findViewById(R.id.dateEditText);
        EditText originEditText = (EditText) findViewById(R.id.originEditText);
        EditText destinationEditText = (EditText) findViewById(R.id.destinationEditText);
        // Get search specifications from the user input
        String date = dateEditText.getText().toString();
        String origin = originEditText.getText().toString();
        String destination = destinationEditText.getText().toString();

        try {
            // Get the itineraries and display it to the user
            ArrayList<Itinerary> itineraries;
            itineraries = system.getItineraries(date, origin, destination);
            itineraries = system.sortByCost(itineraries);

            String displayString = "";
            int counter = 0;
            for (Itinerary itin : itineraries) {
                displayString += String.valueOf(counter) + ": " + itin.toString();
                counter++;
            }

            selectionItinerary = itineraries;

            TextView displayText = (TextView) findViewById(R.id.displayText);
            if (displayString.equals("")){
                displayString = "No itineraries found";
            }
            displayText.setText(displayString);
        }catch (Exception e){
            // Let user no if itineraries where not found based on their input
            TextView displayText = (TextView) findViewById(R.id.displayText);
            displayText.setText("No itineraries found");
            Button button = (Button) findViewById(R.id.bookButton);
            button.setEnabled(false);
        }
    }

    /**
     * Sort the itineraries stored on time.
     * @param view View object to react on button click
     */
    public void sortByTime(View view){
        // Only allow client to book a itinerary
        if (!userInfo[0].substring(0,5).equals("ADMIN")) {
            Button button = (Button) findViewById(R.id.bookButton);
            button.setEnabled(true);
        }
        EditText dateEditText = (EditText) findViewById(R.id.dateEditText);
        EditText originEditText = (EditText) findViewById(R.id.originEditText);
        EditText destinationEditText = (EditText) findViewById(R.id.destinationEditText);
        // Take search input from the user
        String date = dateEditText.getText().toString();
        String origin = originEditText.getText().toString();
        String destination = destinationEditText.getText().toString();

        try {
            // Display stored itineraries to the user
            ArrayList<Itinerary> itineraries;
            itineraries = system.getItineraries(date, origin, destination);
            itineraries = system.sortByTime(itineraries);

            String displayString = "";
            int counter = 0;
            for (Itinerary itin : itineraries) {
                displayString += String.valueOf(counter) + ": " + itin.toString();
                counter++;
            }

            selectionItinerary = itineraries;

            TextView displayText = (TextView) findViewById(R.id.displayText);
            if (displayString.equals("")){
                displayString = "No itineraries found";
            }
            displayText.setText(displayString);
        }catch (Exception e){
            // Let user know if no itineraries were found based on input
            TextView displayText = (TextView) findViewById(R.id.displayText);
            displayText.setText("No itineraries found");
            Button button = (Button) findViewById(R.id.bookButton);
            button.setEnabled(false);
        }
    }

    /**
     * Book this Itinerary for the user and store it for them.
     * @param view
     */
    public void bookItin(View view){
        try{
            // Get index of itinerary from tis user
            EditText numberEdit = (EditText) findViewById(R.id.numberEdit);
            int index = Integer.parseInt(numberEdit.getText().toString());
            Client thisClient = system.getClient(userInfo[0]);
            Itinerary itin = selectionItinerary.get(index);
            // Store this users booked itinerary
            thisClient.bookItin(itin);
            system.addClient(thisClient);
            bookedItins.add(itin);
            TextView displayText = (TextView) findViewById(R.id.displayText);
            displayText.setText("This Itinerary booked successfully: " + itin.toString());
        }catch (Exception e){
            // Let user know if itinerary cannot be stored
            TextView displayText = (TextView) findViewById(R.id.displayText);
            displayText.setText("No such itinerary found. Please choose\n" +
                    "number from search list displayed");
            Button button = (Button) findViewById(R.id.bookButton);
            button.setEnabled(false);
            EditText numberEdit = (EditText) findViewById(R.id.numberEdit);
            numberEdit.setText("");
        }
    }
}
