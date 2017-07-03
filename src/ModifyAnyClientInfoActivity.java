package group787.utor.flylocity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import group787.utor.flylocity.driver.AppEngine;
import group787.utor.flylocity.users.Client;

/**
 * This activity enables administrators to edit the information of one particular
 * client.
 */
public class ModifyAnyClientInfoActivity extends AppCompatActivity {

    private AppEngine system;
    private String[] userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_any_client_info);

        // Unpack the information from the previous activity
        Intent intent = getIntent();
        system = (AppEngine) intent.getSerializableExtra(MainActivity.SYSTEM_KEY);
        userInfo = (String[]) intent.getSerializableExtra("userInformation");
    }

    /**
     * Go back to the previous activity.
     * @param view View object to react on button click
     */
    public void goBack(View view ){
        Intent intent = new Intent(this, AdminActivity.class);
        intent.putExtra("system", system);
        intent.putExtra("userInformation", userInfo);
        startActivity(intent);
    }

    /**
     * View the client that is going to be modified.
     * @param view View object to react on button click
     */
    public void viewClient(View view){
        // Get client from the system to modify
        TextView emailLabel = (TextView) findViewById(R.id.emailLabel);
        emailLabel.setText("To edit new client, relaunch screen");
        EditText emailEditText = (EditText) findViewById(R.id.emailEditText);
        String email = emailEditText.getText().toString();
        emailEditText.setEnabled(false);
        Client client = this.system.getClient(email);
        if (client == null){
            emailEditText.setText("Client does not exist!");
        }else{
            // Display this clients information
            EditText firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
            EditText lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
            EditText addressNameEditText = (EditText) findViewById(R.id.addressEditText);
            EditText ccnumberEditText = (EditText) findViewById(R.id.ccnumberEditText);
            EditText ccexpiryEditText = (EditText) findViewById(R.id.ccexpiryEditText);
            firstNameEditText.setText(client.getFirstName());
            lastNameEditText.setText(client.getLastName());
            addressNameEditText.setText(client.getAddress());
            ccnumberEditText.setText(client.getCcNumber());
            ccexpiryEditText.setText(client.getCcExpiry());
            Button applychangeButton = (Button) findViewById(R.id.applychangeButton);
            applychangeButton.setEnabled(true);
        }
    }

    /**
     * Change the clients information based on the user input.
     * @param view View object to react on button click
     */
    public void readInfo(View view){
        EditText emailEditText = (EditText) findViewById(R.id.emailEditText);
        EditText firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        EditText lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        EditText addressNameEditText = (EditText) findViewById(R.id.addressEditText);
        EditText ccnumberEditText = (EditText) findViewById(R.id.ccnumberEditText);
        EditText ccexpiryEditText = (EditText) findViewById(R.id.ccexpiryEditText);
        String email = emailEditText.getText().toString();
        String firstname = firstNameEditText.getText().toString();
        String lastname = lastNameEditText.getText().toString();
        String address = addressNameEditText.getText().toString();
        String ccnumber = ccnumberEditText.getText().toString();
        String ccexpiry = ccexpiryEditText.getText().toString();
        // Replace old client with new information entered
        Client newClient = new Client(lastname, firstname, email,
                address, ccnumber, ccexpiry);
        // Save the client
        system.addClient(newClient);
        system.writeClients(system.getClientsPath());
        TextView successLabel = (TextView) findViewById(R.id.successLabel);
        successLabel.setText("Client Changed");
    }
}
