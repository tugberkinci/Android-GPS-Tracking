package com.example.myapplication;

import android.nfc.Tag;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

public class DbWriter {

    private double latittude;
    private double longitude;

    public void setLatittude(double latittude) {
        this.latittude = latittude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }




   FirebaseFirestore db=FirebaseFirestore.getInstance();

    public void addDatabase()
    {
        double lat=latittude;
        double lon=longitude;


        Map<String, Double> data = new HashMap<>();
        data.put("Latittude",lat);
        data.put("Longitude", lon);
        db.collection("Users")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("isSuccusful", "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("isFailed", "Error adding document", e);
                    }
                });



    }




}
