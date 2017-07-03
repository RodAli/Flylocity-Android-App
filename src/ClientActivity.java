package group787.utor.flylocity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import group787.utor.flylocity.driver.AppEngine;
import group787.utor.flylocity.flights.Itinerary;
import group787.utor.flylocity.users.Client;

/**
 * An activity that represents the home page for clients; it displays a list
 * of available options in which the user selects one of them.
 */
public class ClientActivity extends AppCompatActivity {

    private AppEngine system;
    private String[] userInfo;
    private boolean showInfo = true;
    private ArrayList<Itinerary> bookedItins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        Intent intent = getIntent();
        system = (AppEngine) intent.getSerializableExtra(MainActivity.SYSTEM_KEY);
        userInfo = (String[]) intent.getSerializableExtra("userInformation");
        bookedItins = (ArrayList<Itinerary>) intent.getSerializableExtra("bookedItins");
        try {
            system.readClients(system.getClientsPath());
            system.readFlights(system.getFlightsPath());
        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }
    }

    /**
     * Method to display personal information of the client on the screen.
     * @param view View object to react to button click
     */
    public void displayPersonal(View view) {
        if (showInfo) {
            // Get this client
            Client client = system.getClient(userInfo[0]);
            // Format his information
            String formatString = String.format("First Name: %s\nLastName: %s\n" +
                            "Email: %s\nAddress: %s\nCredit Card Number: %s\nCredit Card Expiry: %s",
                    client.getFirstName(), client.getLastName(), client.getEmail(),
                    client.getAddress(), client.getCcNumber(), client.getCcExpiry());
            TextView textView = (TextView) findViewById(R.id.displayText);
            textView.setText(formatString);
            Button button = (Button) findViewById(R.id.personalButton);
            button.setText("Hide Personal Info");
            showInfo = false;
        } else {
            // If button is clicked again, hide the info
            TextView textView = (TextView) findViewById(R.id.displayText);
            textView.setText("");
            Button button = (Button) findViewById(R.id.personalButton);
            button.setText("Display Personal Info");
            showInfo = true;
        }
    }

    /**
     * Go back to previous activity, signing out this Client.
     * @param view View object to react to button click
     */
    public void signOut(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    /**
     * Switch to proper activity based on the radio buttons selected.
     * @param view View object to react to button click
     */
    public void changeTask(View view){
        RadioButton searchFlights = (RadioButton) findViewById(R.id.searchFlightsOption);
        RadioButton searchItin = (RadioButton) findViewById(R.id.searchItinerariesOption);
        RadioButton accInfo = (RadioButton) findViewById(R.id.changeAccountInfoOption);
        Intent intent;
        if (accInfo.isChecked()){
            intent = new Intent(this, ModifyClientInfoActivity.class);
        }else if (searchFlights.isChecked()){
            intent = new Intent(this, ClientFlightSearchActivity.class);
        }else if (searchItin.isChecked()){
            intent = new Intent(this, ClientItinerarySearchActivity.class);
        }else{
            intent = new Intent(this, ClientBookedItineraryActivity.class);
        }
        intent.putExtra("userInformation", userInfo);
        intent.putExtra("system", system);
        intent.putExtra("bookedItins", bookedItins);
        startActivity(intent);
    }

    /**
     * Method to select this radio button and unselect all the others.
     * @param view View object to react to click on this radio button
     */
    public void accountInfo(View view){
        Button goButton = (Button) findViewById(R.id.goButton);
        goButton.setEnabled(true);
        RadioButton searchFlights = (RadioButton) findViewById(R.id.searchFlightsOption);
        RadioButton searchItin = (RadioButton) findViewById(R.id.searchItinerariesOption);
        RadioButton bookedItin = (RadioButton) findViewById(R.id.bookedItinerariesOption);
        searchFlights.setChecked(false);
        searchItin.setChecked(false);
        bookedItin.setChecked(false);
    }

    /**
     * Method to select this radio button and unselect all the others.
     * @param view View object to react to click on this radio button
     */
    public void searchFlights(View view){
        Button goButton = (Button) findViewById(R.id.goButton);
        goButton.setEnabled(true);
        RadioButton accInfo = (RadioButton) findViewById(R.id.changeAccountInfoOption);
        RadioButton searchItin = (RadioButton) findViewById(R.id.searchItinerariesOption);
        RadioButton bookedItin = (RadioButton) findViewById(R.id.bookedItinerariesOption);
        accInfo.setChecked(false);
        searchItin.setChecked(false);
        bookedItin.setChecked(false);
    }

    /**
     * Method to select this radio button and unselect all the others.
     * @param view View object to react to click on this radio button
     */
    public void searchItinerary(View view){
        Button goButton = (Button) findViewById(R.id.goButton);
        goButton.setEnabled(true);
        RadioButton searchFlights = (RadioButton) findViewById(R.id.searchFlightsOption);
        RadioButton accInfo = (RadioButton) findViewById(R.id.changeAccountInfoOption);
        RadioButton bookedItin = (RadioButton) findViewById(R.id.bookedItinerariesOption);
        searchFlights.setChecked(false);
        accInfo.setChecked(false);
        bookedItin.setChecked(false);
    }

    /**
     * Method to select this radio button and unselect all the others.
     * @param view View object to react to click on this radio button
     */
    public void bookedItineraries(View view){
        Button goButton = (Button) findViewById(R.id.goButton);
        goButton.setEnabled(true);
        RadioButton searchFlights = (RadioButton) findViewById(R.id.searchFlightsOption);
        RadioButton accInfo = (RadioButton) findViewById(R.id.changeAccountInfoOption);
        RadioButton searchItin = (RadioButton) findViewById(R.id.searchItinerariesOption);
        accInfo.setChecked(false);
        searchFlights.setChecked(false);
        searchItin.setChecked(false);
    }
}
