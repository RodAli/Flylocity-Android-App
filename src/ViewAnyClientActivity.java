package group787.utor.flylocity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

import group787.utor.flylocity.driver.AppEngine;
import group787.utor.flylocity.users.Client;

/**
 * This activity enables administrators to view information of any client.
 */
public class ViewAnyClientActivity extends AppCompatActivity {

    private AppEngine system;
    private String[] userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_any_client);

        // Unpack the intent
        Intent intent = getIntent();
        system = (AppEngine) intent.getSerializableExtra(MainActivity.SYSTEM_KEY);
        userInfo = (String[]) intent.getSerializableExtra("userInformation");

        // Display all the clients information from the system
        HashMap<String, Client> emailToClients = system.getAllClients();
        int counter = 1;
        String printString = "All Clients Information:\n\n";
        for (String email: emailToClients.keySet()){
            printString += "("+ String.valueOf(counter) + ")"
                    + emailToClients.get(email).toString() + "\n\n";
            counter++;
        }

        TextView clientDisplay = (TextView) findViewById(R.id.clientDisplay);
        clientDisplay.setText(printString);
    }

    /**
     * Go back to the previosu activity.
     * @param view View object to react on button click
     */
    public void goBack(View view ){
        Intent intent = new Intent(this, AdminActivity.class);
        intent.putExtra("system", system);
        intent.putExtra("userInformation", userInfo);
        startActivity(intent);
    }
}
