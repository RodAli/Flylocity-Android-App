package group787.utor.flylocity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import group787.utor.flylocity.driver.AppEngine;
import group787.utor.flylocity.flights.Itinerary;
import group787.utor.flylocity.users.Client;

/**
 * This activity represents the main screen upon launching this application
 * from the device's app drawer.
 */
public class MainActivity extends AppCompatActivity {

    //The main backend driver of the application
    private AppEngine system;

    // Constants to stored the paths to the persistant files
    public static final String CLIENTS = "savedClients.txt";
    public static final String FLIGHTS = "savedFlights.txt";
    public static final String NEWCLIENTS = "newClients.csv";
    public static final String NEWFLIGHTS = "newFlights.csv";
    public static final String PASSWORDS = "passwords.txt";
    public static final String USERDATADIR = "userdata";
    public static final String SYSTEM_KEY = "system";

    private InputStreamReader reader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find out where this application stores files, and get
        // the directory called USERDATADIR, or if it doesn't exist,
        // create it.
        File userdata = this.getApplicationContext().getDir(USERDATADIR, MODE_PRIVATE);

        File clientsFile = new File(userdata, CLIENTS);

        File flightsFile = new File(userdata, FLIGHTS);

        File passwordsFile = new File(userdata, PASSWORDS);

        File newClientsFile = new File(userdata, NEWCLIENTS);

        File newFlightsFile = new File(userdata, NEWFLIGHTS);

        // Initialize the system.  It will load data from clientsFile and flightsFile
        // if it exists, and if not, will create a new empty file for it to be
        // saved in later.
        try {
            system = new AppEngine(clientsFile, flightsFile, passwordsFile,
                    newClientsFile, newFlightsFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Determines whether the username and password are found in the system.
     * @param username the username to be looked for
     * @param password the password to be looked for
     * @return true if the username and password are in the system, false otherwise
     */
    public boolean isPassword(String username, String password, String passwordsPath){
        BufferedReader br;
        boolean accepted = false;
        try {
            br = new BufferedReader(new FileReader(passwordsPath));
            String mLine;
            while ((mLine = br.readLine()) != null) {
                String[] userAndPass = mLine.split(",");
                String userAdmin = "";
                // Check the username and password for an admin if not check for normal client
                if ((userAndPass[0].length() > 5) && (userAndPass[0].substring(0, 5).equals("ADMIN"))){
                    userAdmin = userAndPass[0].substring(5, userAndPass[0].length());
                    if (username.equals(userAdmin) && password.equals(userAndPass[1])) {
                        accepted = true;
                    }
                }else{
                    if (username.equals(userAndPass[0]) && password.equals(userAndPass[1])) {
                        accepted = true;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            e.getMessage();
        }finally{
            return accepted;
        }
    }

    /**
     * Determine of this user is an admin based on the password file stored.
     * @param username username of this user
     * @param passwordsPath the path to password file
     * @return true if user is an admin, false otherwise
     */
    public boolean isAdmin(String username, String passwordsPath){
        BufferedReader br;
        boolean isAdmin = false;
        try {
            br = new BufferedReader(new FileReader(passwordsPath));
            String mLine;
            while ((mLine = br.readLine()) != null) {
                String[] userAndPass = mLine.split(",");
                if ((userAndPass[0].length() > 5) && (userAndPass[0].substring(0, 5).equals("ADMIN"))){
                    if (userAndPass[0].substring(5).equals(username)){
                        isAdmin = true;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }finally{
            return isAdmin;
        }
    }

    /**
     * ZProperly login this user determining if their username and password are
     * stored in this system.
     * @param view View object to react on button click
     */
    public void loginUser(View view) {

        Intent intent = new Intent(this, ClientActivity.class);
        Intent adminIntent = new Intent(this, AdminActivity.class);

        // Gets the user name from the 1st EditText field.
        EditText usernameField = (EditText) findViewById(R.id.username_field);
        String userName = usernameField.getText().toString();

        // Gets the password from the 2nd EditText field.
        EditText passwordField = (EditText) findViewById(R.id.password_field);
        String password = passwordField.getText().toString();

        if (isPassword(userName, password, system.getPasswordsPath())){
            // If admin go to the admin activity, if not go to client activity
            if (isAdmin(userName, system.getPasswordsPath())){
                //userName = userName.substring(5);
                EditText messageText = (EditText) findViewById(R.id.MessageText);
                messageText.setText("Welcome Administrator!");
                messageText.setTextColor(Color.GREEN);
                userName = "ADMIN" + userName;
                String[] userInfo = {userName, password};
                adminIntent.putExtra(SYSTEM_KEY, system);
                adminIntent.putExtra("userInformation", userInfo);
                // Starts ShowActivity.
                startActivity(adminIntent);
            }else{
                EditText messageText = (EditText) findViewById(R.id.MessageText);
                Client client = system.getClient(userName);
                messageText.setText("Welcome, " + client.getFirstName() + " " + client.getLastName());
                String[] userInfo = {userName, password};
                intent.putExtra(SYSTEM_KEY, system);
                intent.putExtra("userInformation", userInfo);
                intent.putExtra("bookedItins", new ArrayList<Itinerary>());
                // Starts ShowActivity.
                startActivity(intent);
            }
        }else{
            // Let user know if they enter incorrect username or password
            EditText messageText = (EditText) findViewById(R.id.MessageText);
            messageText.setText("Incorrect Username and Password");
            messageText.setTextColor(Color.RED);
        }
    }
}
