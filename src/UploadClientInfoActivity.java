package group787.utor.flylocity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import group787.utor.flylocity.driver.AppEngine;

/**
 * This activity enables administrators to upload information of new clients.
 */
public class UploadClientInfoActivity extends AppCompatActivity {

    private AppEngine system;
    private String[] userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_client_info);

        // Unpack from the previous activity
        Intent intent = getIntent();
        system = (AppEngine) intent.getSerializableExtra("system");
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
     * Read the path from user and upload client information if it exists.
     * @param view View object to react on button click
     */
    public void uploadClientInfo(View view){
        EditText locationEdit = (EditText) findViewById(R.id.locationEdit);
        String path = locationEdit.getText().toString();
        TextView displayMessage = (TextView) findViewById(R.id.displayMessage);
        Button upload = (Button) findViewById(R.id.uploadButton);

        // Check if path exists
        if (new File(path).exists()){
            // Read the file into the system
            system.readClients(path);
            system.writeClients(system.getClientsPath());
            displayMessage.setText("Clients Read Successfully!");
            upload.setEnabled(false);
            locationEdit.setEnabled(true);
        }else if (path.equals("")){
            system.readClients(system.getnNewClientsPath());
            system.writeClients(system.getnNewClientsPath());
            displayMessage.setText("Successfully Read Clients from Default Location.");
            upload.setEnabled(false);
            locationEdit.setEnabled(true);
        }else{
            displayMessage.setText("File Not Found!");
            upload.setEnabled(true);
            locationEdit.setEnabled(false);
        }
    }
}
