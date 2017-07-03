package group787.utor.flylocity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

import group787.utor.flylocity.driver.AppEngine;

/**
 * This activity enables administrators to upload information of new flights;
 * there is an option to either enter a path or use the default.
 */
public class UploadFlightInfoActivity extends AppCompatActivity {

    private AppEngine system;
    private String[] userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_flight_info);

        // Unpack intent from the previous activity
        Intent intent = getIntent();
        system = (AppEngine) intent.getSerializableExtra("system");
        userInfo = (String[]) intent.getSerializableExtra("userInformation");
    }

    /**
     * Go back to the previous activity.
     * @param view View object to react on button click
     */
    public void goBack(View view){
        Intent intent = new Intent(this, AdminActivity.class);
        intent.putExtra("system", system);
        intent.putExtra("userInformation", userInfo);
        startActivity(intent);
    }

    /**
     * Upload flight information from a file if it exists.
     * @param view View object to react on button click
     */
    public void uploadFlightInfo(View view){
        EditText locationEditText = (EditText) findViewById(R.id.locationEditText);
        String path = locationEditText.getText().toString();
        TextView displayResult = (TextView) findViewById(R.id.displayResult);
        Button upload = (Button) findViewById(R.id.uploadOption);

        // Check if path exists
        if (new File(path).exists()){
            // Read the flights
            system.readFlights(path);
            system.writeFlights(system.getFlightsPath());
            displayResult.setText("Flights Read Successfully!");
            upload.setEnabled(false);
            locationEditText.setEnabled(false);
        }else if (path.equals("")){
            system.readFlights(system.getnNewFlightsPath());
            system.writeFlights(system.getnNewFlightsPath());
            displayResult.setText("Successfully Read Flights from Default Location.");
            upload.setEnabled(false);
            locationEditText.setEnabled(false);
        }else{
            displayResult.setText("File Not Found!");
            upload.setEnabled(true);
            locationEditText.setEnabled(true);
        }
    }
}
