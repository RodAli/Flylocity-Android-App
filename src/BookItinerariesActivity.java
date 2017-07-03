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
 * An activity that represents the page to search and book available itineraries;
 * administrators and clients access this page through their respective main screens.
 */
public class BookItinerariesActivity extends AppCompatActivity {

    private AppEngine system;
    private String[] userInfo;
    private ArrayList<Itinerary> itineraries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_itineraries);

        Intent intent = getIntent();
        system = (AppEngine) intent.getSerializableExtra("system");
        userInfo = (String[]) intent.getSerializableExtra("userInformation");

    }

    /**
     * Method to go back to the previous acitivity
     * @param view View object to react to OnClick
     */
    public void goBack(View view ){
        Intent intent = new Intent(this, AdminActivity.class);
        intent.putExtra("system", system);
        intent.putExtra("userInformation", userInfo);
        startActivity(intent);
    }

    /**
     * Method to show all the itineraries to the user.
     * @param view View object to react to OnClick
     */
    public void showItins(View view){
        // Get text from the edit texts
        EditText editDate = (EditText) findViewById(R.id.editDate);
        EditText editOrigin = (EditText) findViewById(R.id.editOrg);
        EditText editDestination = (EditText) findViewById(R.id.editDest);
        String date = editDate.getText().toString();
        String origin = editOrigin.getText().toString();
        String destination = editDestination.getText().toString();

        try{
            // Get the itinerary that was searched for
            itineraries = system.getItineraries(date, origin, destination);

            String displayString = "";
            int counter = 0;
            for (Itinerary itin : itineraries) {
                displayString += String.valueOf(counter) + ": " + itin.toString();
                counter++;
            }


            // Display itineraries to the user
            Button buttonBook = (Button) findViewById(R.id.buttonBook);
            buttonBook.setEnabled(true);
            TextView displayItinerariesLabel =
                    (TextView) findViewById(R.id.displayItinerariesLabel);
            if (displayString.equals("")){
                displayString = "No itineraries found";
                buttonBook.setEnabled(false);
            }
            displayItinerariesLabel.setText(displayString);

        }catch (Exception e){
            // If itineraries cannot be resolved then let user know
            TextView displayItinerariesLabel =
                    (TextView) findViewById(R.id.displayItinerariesLabel);
            displayItinerariesLabel.setText("Itineraries not found");
            Button buttonBook = (Button) findViewById(R.id.buttonBook);
            buttonBook.setEnabled(false);
        }
    }

    /**
     * Book an itinerary selected from the itineraries generated
     * @param view View object to react to click on button
     */
    public void bookItinerary(View view){
        try {
            // Get the client to book the itinerary for
            EditText clientemailEdit = (EditText) findViewById(R.id.clientemailEdit);
            String email = clientemailEdit.getText().toString();
            Client client = system.getClient(email);
            EditText itineraryNumberEdit = (EditText) findViewById(R.id.itineraryNumberEdit);
            int index = Integer.parseInt(itineraryNumberEdit.getText().toString());
            // Get the itinerary the user selected and book it
            client.bookItin(this.itineraries.get(index));
            system.addClient(client);
            TextView displayItinerariesLabel = (TextView) findViewById(R.id.displayItinerariesLabel);
            displayItinerariesLabel.setText("Client: " + client.getFirstName() +
                " booked Itinerary:\n" + this.itineraries.get(index));
        }catch (Exception e){
            // Let user know if they did not choose an itinerary in the list
            TextView displayItinerariesLabel = (TextView) findViewById(R.id.displayItinerariesLabel);
            displayItinerariesLabel.setText("Client or Itinerary no found. Make sure you select\n" +
                    " itinerary from list displayed.");
        }
    }
}
