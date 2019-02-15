package com.techneophytes.googlemapsgoogleplaces;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isServicesOkay()) {
            init();
        }
    }
    public void init() {
        // we will have a button
        Button btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
    public boolean isServicesOkay() {
        Log.d(TAG, "isServicesOkay: checking google services version");
        
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        
        if(available == ConnectionResult.SUCCESS) {
            // EVERYTHING IS FINE
            Log.d(TAG, "isServicesOkay: Google Play Services");
            return true;
        } else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            // error occured but we can resolve it
            Log.d(TAG, "isServicesOkay: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
        } else {
            Toast.makeText(this, "Can't make a map request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
