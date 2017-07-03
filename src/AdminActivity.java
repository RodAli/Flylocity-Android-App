package group787.utor.flylocity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import group787.utor.flylocity.driver.AppEngine;

/**
 * An activity that represents the main page for administrators; it displays a
 * list of options in which the administrator selects one of them.
 */
public class AdminActivity extends AppCompatActivity {

    private AppEngine system;
    private String[] userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Intent intent = getIntent();
        system = (AppEngine) intent.getSerializableExtra(MainActivity.SYSTEM_KEY);
        userInfo = (String[]) intent.getSerializableExtra("userInformation");
    }

    /**
     * Method to switch acitvity and sign out of the app.
     * @param view View object to listen to click of button
     */
    public void signOut(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


    /**
     * Method to select this radio button and unselect all the others.
     * @param view View object to react to click on this radio button
     */
    public void selectViewAccountInfo(View view){
        Button goButton = (Button) findViewById(R.id.goButton);
        goButton.setEnabled(true);
        RadioButton editClientInfo = (RadioButton) findViewById(R.id.editClientInfoOption);
        RadioButton uploadClientInfo = (RadioButton) findViewById(R.id.uploadClientInfoOption);
        RadioButton uploadFlightInfo = (RadioButton) findViewById(R.id.uploadFlightInfoOption);
        RadioButton modifyFlight = (RadioButton) findViewById(R.id.editFlightOption);
        RadioButton searchFlights = (RadioButton) findViewById(R.id.searchFlightsOption);
        RadioButton searchItin = (RadioButton) findViewById(R.id.searchItinerariesOption);
        RadioButton bookItin = (RadioButton) findViewById(R.id.bookItinerariesOption);
        bookItin.setChecked(false);
        editClientInfo.setChecked(false);
        uploadClientInfo.setChecked(false);
        uploadFlightInfo.setChecked(false);
        modifyFlight.setChecked(false);
        searchFlights.setChecked(false);
        searchItin.setChecked(false);
    }

    /**
     * Method to select this radio button and unselect all the others.
     * @param view View object to react to click on this radio button
     */
    public void selectEditAccountInfo(View view){
        Button goButton = (Button) findViewById(R.id.goButton);
        goButton.setEnabled(true);
        RadioButton viewClientInfo = (RadioButton) findViewById(R.id.viewClientInfoOption);
        RadioButton uploadClientInfo = (RadioButton) findViewById(R.id.uploadClientInfoOption);
        RadioButton uploadFlightInfo = (RadioButton) findViewById(R.id.uploadFlightInfoOption);
        RadioButton modifyFlight = (RadioButton) findViewById(R.id.editFlightOption);
        RadioButton searchFlights = (RadioButton) findViewById(R.id.searchFlightsOption);
        RadioButton searchItin = (RadioButton) findViewById(R.id.searchItinerariesOption);
        RadioButton bookItin = (RadioButton) findViewById(R.id.bookItinerariesOption);
        bookItin.setChecked(false);
        viewClientInfo.setChecked(false);
        uploadClientInfo.setChecked(false);
        uploadFlightInfo.setChecked(false);
        modifyFlight.setChecked(false);
        searchFlights.setChecked(false);
        searchItin.setChecked(false);
    }

    /**
     * Method to select this radio button and unselect all the others.
     * @param view View object to react to click on this radio button
     */
    public void selectUploadAccountInfo(View view){
        Button goButton = (Button) findViewById(R.id.goButton);
        goButton.setEnabled(true);
        RadioButton viewClientInfo = (RadioButton) findViewById(R.id.viewClientInfoOption);
        RadioButton editClientInfo = (RadioButton) findViewById(R.id.editClientInfoOption);
        RadioButton uploadFlightInfo = (RadioButton) findViewById(R.id.uploadFlightInfoOption);
        RadioButton modifyFlight = (RadioButton) findViewById(R.id.editFlightOption);
        RadioButton searchFlights = (RadioButton) findViewById(R.id.searchFlightsOption);
        RadioButton searchItin = (RadioButton) findViewById(R.id.searchItinerariesOption);
        RadioButton bookItin = (RadioButton) findViewById(R.id.bookItinerariesOption);
        bookItin.setChecked(false);
        viewClientInfo.setChecked(false);
        editClientInfo.setChecked(false);
        uploadFlightInfo.setChecked(false);
        modifyFlight.setChecked(false);
        searchFlights.setChecked(false);
        searchItin.setChecked(false);
    }

    /**
     * Method to select this radio button and unselect all the others.
     * @param view View object to react to click on this radio button
     */
    public void selectUploadFlightInfo(View view){
        Button goButton = (Button) findViewById(R.id.goButton);
        goButton.setEnabled(true);
        RadioButton viewClientInfo = (RadioButton) findViewById(R.id.viewClientInfoOption);
        RadioButton editClientInfo = (RadioButton) findViewById(R.id.editClientInfoOption);
        RadioButton uploadClientInfo = (RadioButton) findViewById(R.id.uploadClientInfoOption);
        RadioButton modifyFlight = (RadioButton) findViewById(R.id.editFlightOption);
        RadioButton searchFlights = (RadioButton) findViewById(R.id.searchFlightsOption);
        RadioButton searchItin = (RadioButton) findViewById(R.id.searchItinerariesOption);
        RadioButton bookItin = (RadioButton) findViewById(R.id.bookItinerariesOption);
        bookItin.setChecked(false);
        editClientInfo.setChecked(false);
        uploadClientInfo.setChecked(false);
        modifyFlight.setChecked(false);
        searchFlights.setChecked(false);
        searchItin.setChecked(false);
    }

    /**
     * Method to select this radio button and unselect all the others.
     * @param view View object to react to click on this radio button
     */
    public void selectEditFlight(View view){
        Button goButton = (Button) findViewById(R.id.goButton);
        goButton.setEnabled(true);
        RadioButton viewClientInfo = (RadioButton) findViewById(R.id.viewClientInfoOption);
        RadioButton editClientInfo = (RadioButton) findViewById(R.id.editClientInfoOption);
        RadioButton uploadClientInfo = (RadioButton) findViewById(R.id.uploadClientInfoOption);
        RadioButton uploadFlightInfo = (RadioButton) findViewById(R.id.uploadFlightInfoOption);
        RadioButton searchFlights = (RadioButton) findViewById(R.id.searchFlightsOption);
        RadioButton searchItin = (RadioButton) findViewById(R.id.searchItinerariesOption);
        RadioButton bookItin = (RadioButton) findViewById(R.id.bookItinerariesOption);
        bookItin.setChecked(false);
        viewClientInfo.setChecked(false);
        editClientInfo.setChecked(false);
        uploadClientInfo.setChecked(false);
        uploadFlightInfo.setChecked(false);
        searchFlights.setChecked(false);
        searchItin.setChecked(false);
    }

    /**
     * Method to select this radio button and unselect all the others.
     * @param view View object to react to click on this radio button
     */
    public void selectSearchFlights(View view){
        Button goButton = (Button) findViewById(R.id.goButton);
        goButton.setEnabled(true);
        RadioButton viewClientInfo = (RadioButton) findViewById(R.id.viewClientInfoOption);
        RadioButton editClientInfo = (RadioButton) findViewById(R.id.editClientInfoOption);
        RadioButton uploadClientInfo = (RadioButton) findViewById(R.id.uploadClientInfoOption);
        RadioButton uploadFlightInfo = (RadioButton) findViewById(R.id.uploadFlightInfoOption);
        RadioButton modifyFlight = (RadioButton) findViewById(R.id.editFlightOption);
        RadioButton searchItin = (RadioButton) findViewById(R.id.searchItinerariesOption);
        RadioButton bookItin = (RadioButton) findViewById(R.id.bookItinerariesOption);
        bookItin.setChecked(false);
        viewClientInfo.setChecked(false);
        editClientInfo.setChecked(false);
        uploadClientInfo.setChecked(false);
        uploadFlightInfo.setChecked(false);
        modifyFlight.setChecked(false);
        searchItin.setChecked(false);
    }

    /**
     * Method to select this radio button and unselect all the others.
     * @param view View object to react to click on this radio button
     */
    public void selectSearchItinerary(View view){
        Button goButton = (Button) findViewById(R.id.goButton);
        goButton.setEnabled(true);
        RadioButton viewClientInfo = (RadioButton) findViewById(R.id.viewClientInfoOption);
        RadioButton editClientInfo = (RadioButton) findViewById(R.id.editClientInfoOption);
        RadioButton uploadClientInfo = (RadioButton) findViewById(R.id.uploadClientInfoOption);
        RadioButton uploadFlightInfo = (RadioButton) findViewById(R.id.uploadFlightInfoOption);
        RadioButton modifyFlight = (RadioButton) findViewById(R.id.editFlightOption);
        RadioButton searchFlights = (RadioButton) findViewById(R.id.searchFlightsOption);
        RadioButton bookItin = (RadioButton) findViewById(R.id.bookItinerariesOption);
        bookItin.setChecked(false);
        viewClientInfo.setChecked(false);
        editClientInfo.setChecked(false);
        uploadClientInfo.setChecked(false);
        uploadFlightInfo.setChecked(false);
        modifyFlight.setChecked(false);
        searchFlights.setChecked(false);
    }

    /**
     * Method to select this radio button and unselect all the others.
     * @param view View object to react to click on this radio button
     */
    public void selectBookItinerary(View view){
        Button goButton = (Button) findViewById(R.id.goButton);
        goButton.setEnabled(true);
        RadioButton viewClientInfo = (RadioButton) findViewById(R.id.viewClientInfoOption);
        RadioButton editClientInfo = (RadioButton) findViewById(R.id.editClientInfoOption);
        RadioButton uploadClientInfo = (RadioButton) findViewById(R.id.uploadClientInfoOption);
        RadioButton uploadFlightInfo = (RadioButton) findViewById(R.id.uploadFlightInfoOption);
        RadioButton modifyFlight = (RadioButton) findViewById(R.id.editFlightOption);
        RadioButton searchFlights = (RadioButton) findViewById(R.id.searchFlightsOption);
        RadioButton searchItin = (RadioButton) findViewById(R.id.searchItinerariesOption);
        searchItin.setChecked(false);
        viewClientInfo.setChecked(false);
        editClientInfo.setChecked(false);
        uploadClientInfo.setChecked(false);
        uploadFlightInfo.setChecked(false);
        modifyFlight.setChecked(false);
        searchFlights.setChecked(false);
    }

    /**
     * Check which radio button is selected and transition to correct Activity
     * @param view View object to react click of button
     */
    public void changeTask(View view){
        RadioButton viewClientInfo = (RadioButton) findViewById(R.id.viewClientInfoOption);
        RadioButton editClientInfo = (RadioButton) findViewById(R.id.editClientInfoOption);
        RadioButton uploadClientInfo = (RadioButton) findViewById(R.id.uploadClientInfoOption);
        RadioButton uploadFlightInfo = (RadioButton) findViewById(R.id.uploadFlightInfoOption);
        RadioButton modifyFlight = (RadioButton) findViewById(R.id.editFlightOption);
        RadioButton searchFlights = (RadioButton) findViewById(R.id.searchFlightsOption);
        RadioButton searchItin = (RadioButton) findViewById(R.id.searchItinerariesOption);
        if (viewClientInfo.isChecked()){
            Intent intent = new Intent(this, ViewAnyClientActivity.class);
            intent.putExtra("userInformation", userInfo);
            intent.putExtra("system", system);
            startActivity(intent);
        }else if (editClientInfo.isChecked()){
            Intent intent = new Intent(this, ModifyAnyClientInfoActivity.class);
            intent.putExtra("userInformation", userInfo);
            intent.putExtra("system", system);
            startActivity(intent);
        }else if (uploadClientInfo.isChecked()){
            Intent intent = new Intent(this, UploadClientInfoActivity.class);
            intent.putExtra("userInformation", userInfo);
            intent.putExtra("system", system);
            startActivity(intent);
        }else if (uploadFlightInfo.isChecked()){
            Intent intent = new Intent(this, UploadFlightInfoActivity.class);
            intent.putExtra("userInformation", userInfo);
            intent.putExtra("system", system);
            startActivity(intent);
        }else if (modifyFlight.isChecked()){
            Intent intent = new Intent(this, ModifyFlightInfoActivity.class);
            intent.putExtra("userInformation", userInfo);
            intent.putExtra("system", system);
            startActivity(intent);
        }else if (searchFlights.isChecked()){
            Intent intent = new Intent(this, ClientFlightSearchActivity.class);
            intent.putExtra("userInformation", userInfo);
            intent.putExtra("system", system);
            startActivity(intent);
        }else if (searchItin.isChecked()){
            Intent intent = new Intent(this, ClientItinerarySearchActivity.class);
            intent.putExtra("userInformation", userInfo);
            intent.putExtra("system", system);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, BookItinerariesActivity.class);
            intent.putExtra("userInformation", userInfo);
            intent.putExtra("system", system);
            startActivity(intent);
        }
    }
}



