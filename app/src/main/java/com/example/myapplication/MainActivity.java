package com.example.myapplication;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


public class MainActivity extends AppCompatActivity {

    Button btnRead;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    DbHelper id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id=new DbHelper();

        mAuth = FirebaseAuth.getInstance();
        btnRead=findViewById(R.id.buttonRead);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("signed_in", "onAuthStateChanged:signed_in:" + user.getUid());
                    id.setId(user.getUid());

                } else {
                    // User is signed out
                    Log.d("signed_out", "onAuthStateChanged:signed_out");
                }

            }
        };

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                newActivity();

            }
        });
    }



    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("isSuccessful", "OnComplete : " +task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Log.w("Failed", "Failed : ", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }
    // release listener in onStop
    @Override
    public void onStop() {
        super.onStop();
        //mAuth.signOut();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void newActivity(){
        Intent i = new Intent(MainActivity.this, KonumBul.class);
        i.putExtra("id",id.getId());
        startActivity(i);
    }


}
