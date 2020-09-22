package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;

public class KonumBul extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
    private FusedLocationProviderClient mFusedLocationClient;

    TextView konum,auth;
    Button konumBul,kayitliKonumlar,konumKaydet;
    DbHelper id;
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konum_bul);
        i=getIntent();

        id=new DbHelper();

        auth=findViewById(R.id.auth);
        auth.setText("id:"+i);
        konumBul = findViewById(R.id.konumBul);
        kayitliKonumlar = findViewById(R.id.kayıtlıKonumlar);
        konumKaydet = findViewById(R.id.konumKaydet);
        konum=findViewById(R.id.konum);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        konumBul.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) { konum_bul();
                //log.d("ididididid", "onAuthStateChanged:signed_in:" + id.getId() );,
                }
        });
        kayitliKonumlar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) { savedLocations(); }
        });
        konumKaydet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) { saveLocation(); }
        });

    }

    private void savedLocations(){
        Intent i = new Intent(KonumBul.this, SavedLocations.class);
        startActivity(i);
    }
    private void saveLocation(){
        Intent i = new Intent(KonumBul.this, SaveLocation.class);
        startActivity(i);
    }
    private void konum_bul() {


        if (ContextCompat.checkSelfPermission(KonumBul.this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(KonumBul.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                new AlertDialog.Builder(this)
                        .setTitle("Required Location Permission")
                        .setMessage("You have to give this permission to acess this feature")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(KonumBul.this,
                                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create()
                        .show();


            } else {
                //izin istegi
                ActivityCompat.requestPermissions(KonumBul.this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {

                            if (location != null) {
                                // Logic to handle location object
                                Double latittude = location.getLatitude();
                                Double longitude = location.getLongitude();

                                konum.setText("Latitude = "+location.getLongitude() + "\nLongitude = " + location.getLatitude());
                                Log.d("isSuccesful", location.getLatitude() + " ==> " + location.getLongitude());

                            }
                        }
                    });

        }

    }


}
