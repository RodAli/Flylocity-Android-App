package group787.utor.flylocity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.math.BigDecimal;

import group787.utor.flylocity.driver.AppEngine;
import group787.utor.flylocity.flights.Flight;

/**
 * This activity enables administrators to edit the information of one particular
 * flight.
 */
public class ModifyFlightInfoActivity extends AppCompatActivity {

    private AppEngine system;
    private String[] userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_flight_info);

        // Unpack intent from the previous acitivity
        Intent intent = getIntent();
        system = (AppEngine) intent.getSerializableExtra("system");
        userInfo = (String[]) intent.getSerializableExtra("userInformation");
    }

    /**
     * Switch to the previous activity.
     * @param view View object to react on button click
     */
    public void goBack(View view ){
        Intent intent = new Intent(this, AdminActivity.class);
        intent.putExtra("system", system);
        intent.putExtra("userInformation", userInfo);
        startActivity(intent);
    }

    /**
     * Display the flights information that will be modified.
     * @param view View object to react on button click
     */
    public void displayFlight(View view){
        TextView flightnumberLabel = (TextView) findViewById(R.id.flightnumberLabel);
        flightnumberLabel.setText("To edit new flight, relaunch screen");
        EditText flightnumberEdit = (EditText) findViewById(R.id.flightnumberEdit);
        String flightnumber = flightnumberEdit.getText().toString();
        flightnumberEdit.setEnabled(false);
        Flight flight = this.system.getFlight(flightnumber);
        if (flight == null){
            flightnumberEdit.setText("Flight does not exist!");
        }else{
            // Display all the flights information
            EditText departuredateEdit = (EditText) findViewById(R.id.departuredateEdit);
            EditText departuretimeEdit = (EditText) findViewById(R.id.departuretimeEdit);
            EditText arrivaldateEdit = (EditText) findViewById(R.id.arrivaldateEdit);
            EditText arrivaltimeEdit = (EditText) findViewById(R.id.arrivaltimeEdit);
            EditText editOrigin = (EditText) findViewById(R.id.editOrigin);
            EditText editDestination = (EditText) findViewById(R.id.editDestination);
            EditText priceEdit = (EditText) findViewById(R.id.priceEdit);
            EditText airlineEdit = (EditText) findViewById(R.id.airlineEdit);
            EditText numseatsEdit = (EditText) findViewById(R.id.numseatsEdit);
            departuredateEdit.setText(flight.getDepartureDateandTime()[0]);
            departuretimeEdit.setText(flight.getDepartureDateandTime()[1]);
            arrivaldateEdit.setText(flight.getArrivalDateandTime()[0]);
            arrivaltimeEdit.setText(flight.getArrivalDateandTime()[1]);
            editOrigin.setText(flight.getOrigin());
            editDestination.setText(flight.getDestination());
            priceEdit.setText(flight.getCost().toString());
            airlineEdit.setText(flight.getAirline());
            numseatsEdit.setText(String.valueOf(flight.getNumSeats()));
            Button changeButton = (Button) findViewById(R.id.changeButton);
            changeButton.setEnabled(true);
        }
    }

    /**
     * Read all the changed of this flight and store it in the system.
     * @param view View object to react on button click
     */
    public void applyChanges(View view){
        try {
            // Read changes to the flight
            EditText flightnumberEdit = (EditText) findViewById(R.id.flightnumberEdit);
            EditText departuredateEdit = (EditText) findViewById(R.id.departuredateEdit);
            EditText departuretimeEdit = (EditText) findViewById(R.id.departuretimeEdit);
            EditText arrivaldateEdit = (EditText) findViewById(R.id.arrivaldateEdit);
            EditText arrivaltimeEdit = (EditText) findViewById(R.id.arrivaltimeEdit);
            EditText editOrigin = (EditText) findViewById(R.id.editOrigin);
            EditText editDestination = (EditText) findViewById(R.id.editDestination);
            EditText priceEdit = (EditText) findViewById(R.id.priceEdit);
            EditText airlineEdit = (EditText) findViewById(R.id.airlineEdit);
            EditText numseatsEdit = (EditText) findViewById(R.id.numseatsEdit);
            String flightnumber = flightnumberEdit.getText().toString();
            String departuredate = departuredateEdit.getText().toString();
            String departuretime = departuretimeEdit.getText().toString();
            String arrivaldate = arrivaldateEdit.getText().toString();
            String arrivaltime = arrivaltimeEdit.getText().toString();
            String origin = editOrigin.getText().toString();
            String destination = editDestination.getText().toString();
            BigDecimal cost = new BigDecimal(priceEdit.getText().toString());
            String airline = airlineEdit.getText().toString();
            int numseats = Integer.parseInt(numseatsEdit.getText().toString());
            String[] departure = {departuredate, departuretime};
            String[] arrival = {arrivaldate, arrivaltime};
            Flight flight = new Flight(flightnumber, departure,
                    arrival, airline, origin, destination, cost, numseats);
            // Store flight into the system
            system.addFlight(flight);
            system.writeFlights(system.getFlightsPath());
            TextView resultDisplay = (TextView) findViewById(R.id.resultDisplay);
            resultDisplay.setText("Flight Changed");
        }catch (Exception e){
            // Let user know if they enter improper input
            TextView resultDisplay = (TextView) findViewById(R.id.resultDisplay);
            resultDisplay.setText("Improper input. Changes not saved");
        }
    }

}
