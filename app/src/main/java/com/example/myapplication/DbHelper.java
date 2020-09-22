package com.example.myapplication;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;



public class DbHelper {

    private String id;
    Map<String, Object> data = new HashMap<>();
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }



    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public void read(){
        db.collection("Users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("isSuccessful", document.getId() + " ==> " + document.getData());
                                data.put(document.getId(),document.getData());


                            }
                        } else {
                            Log.w("isFail", "Error getting documents.", task.getException());
                        }
                    }
                });

    }
}