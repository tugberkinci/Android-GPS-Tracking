package com.example.myapplication;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SaveLocation extends AppCompatActivity {


    private FusedLocationProviderClient mFusedLocationClient;
    TextView konum;
    double latittude;
    double longitude;
    FirebaseFirestore db;
    Button btn;
    DbWriter wrt;
    String durdur="Durdur";
    String kaydet="Kaydet";
    String buttonText=null;






    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_location);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        konumBul();
        wrt=new DbWriter();
        btn=findViewById(R.id.save);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                konumBul();
                wrt.addDatabase();

               /* if(buttonText.equals(kaydet))
                {
                    //konum.setText("durdur");

                    btn.setText(durdur);


                }
                if(buttonText.equals(durdur))
                {
                    //konum.setText("konum al");
                    btn.setText(kaydet);

                }*/




            }
        });



    }

    private void konumBul()
    {
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if (location != null) {
                            // Logic to handle location object
                            latittude = location.getLatitude();
                            longitude = location.getLongitude();

                            //konum.setText("Latitude = " + location.getLongitude() + "\n Longitude = " + location.getLatitude());
                            Log.d("isSuccesful", location.getLatitude() + " ==> " + location.getLongitude());
                            wrt.setLongitude(location.getLongitude());
                            wrt.setLatittude(location.getLatitude());

                        }
                    }
                });
    }






    }
