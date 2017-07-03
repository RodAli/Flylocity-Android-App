package group787.utor.flylocity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;

import group787.utor.flylocity.driver.AppEngine;
import group787.utor.flylocity.flights.Itinerary;
import group787.utor.flylocity.users.Client;

/**
 * This activity enables a client to edit his or her information.
 */
public class ModifyClientInfoActivity extends AppCompatActivity {

    private AppEngine system;
    private String[] userInfo;
    private ArrayList<Itinerary> bookedItins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_client_info);

        // Unpack intent from the previous activity
        Intent intent = getIntent();
        system = (AppEngine) intent.getSerializableExtra("system");
        userInfo = (String[]) intent.getSerializableExtra("userInformation");
        bookedItins = (ArrayList<Itinerary>) intent.getSerializableExtra("bookedItins");

        // Display this clients information
        Client thisClient = system.getClient(userInfo[0]);
        EditText firstNameEdit = (EditText) findViewById(R.id.firstNameEdit);
        EditText lastNameEdit = (EditText) findViewById(R.id.lastNameEdit);
        EditText addressEdit = (EditText) findViewById(R.id.addressEdit);
        EditText ccnumberEdit = (EditText) findViewById(R.id.ccnumberEdit);
        EditText ccexpiryEdit = (EditText) findViewById(R.id.ccexpiryEdit);
        firstNameEdit.setText(thisClient.getFirstName());
        lastNameEdit.setText(thisClient.getLastName());
        addressEdit.setText(thisClient.getAddress());
        ccnumberEdit.setText(thisClient.getCcNumber());
        ccexpiryEdit.setText(thisClient.getCcExpiry());
    }

    /**
     * Method to properly save the changes that the user makes in this activity.
     * Modifies the system Engine, and writes the changes to savedClients.txt.
     * @param view View object such that method reacts to button click
     */
    public void saveChanges(View view){
        // Read the edit texts
        EditText firstNameEdit = (EditText) findViewById(R.id.firstNameEdit);
        EditText lastNameEdit = (EditText) findViewById(R.id.lastNameEdit);
        EditText addressEdit = (EditText) findViewById(R.id.addressEdit);
        EditText ccnumberEdit = (EditText) findViewById(R.id.ccnumberEdit);
        EditText ccexpiryEdit = (EditText) findViewById(R.id.ccexpiryEdit);
        String firstName = firstNameEdit.getText().toString();
        String lastName = lastNameEdit.getText().toString();
        String address = addressEdit.getText().toString();
        String ccnumber = ccnumberEdit.getText().toString();
        String ccexpiry = ccexpiryEdit.getText().toString();
        // Modify the system
        Client editedClient = new Client(lastName, firstName,
                userInfo[0], address, ccnumber, ccexpiry);
        system.addClient(editedClient);
        // Write to the clients file
        system.writeClients(system.getClientsPath());
        // Give message and change activity
        EditText displayText = (EditText) findViewById(R.id.activityTitle);
        displayText.setText("Successfully Saved!");
        Intent intent = new Intent(this, ClientActivity.class);
        intent.putExtra("system", system);
        intent.putExtra("userInformation", userInfo);
        intent.putExtra("bookedItins", bookedItins);
        // Starts ShowActivity.
        startActivity(intent);
    }
}
