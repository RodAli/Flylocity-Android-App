package group787.utor.flylocity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import group787.utor.flylocity.driver.AppEngine;
import group787.utor.flylocity.flights.Itinerary;
import group787.utor.flylocity.users.Client;

/**
 * An activity that represents the list of booked itineraries of the current
 * user; it is displayed from the oldest booked itinerary to the newest one.
 */
public class ClientBookedItineraryActivity extends AppCompatActivity {

    private AppEngine system;
    private String[] userInfo;
    private ArrayList<Itinerary> bookedItins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_booked_itinerary);

        Intent intent = getIntent();
        system = (AppEngine) intent.getSerializableExtra("system");
        userInfo = (String[]) intent.getSerializableExtra("userInformation");
        bookedItins = (ArrayList<Itinerary>) intent.getSerializableExtra("bookedItins");

        // Display the booked itineraries of this user on this activity
        String printString = "";
        int counter = 1;
        for (Itinerary itin : bookedItins){
            printString += "(" + String.valueOf(counter) + "): " + itin.toString() + "\n";
             counter++;
        }

        TextView resultLabel = (TextView) findViewById(R.id.resultLabel);
        resultLabel.setText(printString);
    }

    /**
     * Go back to the previous activity.
     * @param view View object to recognize click of button
     */
    public void goBack(View view){
        Intent intent = new Intent(this, ClientActivity.class);
        intent.putExtra("userInformation", userInfo);
        intent.putExtra("system", system);
        intent.putExtra("bookedItins", bookedItins);
        startActivity(intent);
    }
}
