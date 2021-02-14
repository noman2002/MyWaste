package com.example.android.mywaste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LocalActivity extends AppCompatActivity  {
    DatabaseReference r1;
    EditText l;
    Button lcbtn;
    String lx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        l = (EditText) findViewById(R.id.LocationTextView);

        lcbtn=(Button)findViewById(R.id.location);



        lcbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                r1= FirebaseDatabase.getInstance().getReference().child("user");
                r1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String lx= Objects.requireNonNull(dataSnapshot.child("location").getValue()).toString();

                        String x="google.navigation:q=";
                        String location=x+lx;
                        //l.setText(location);
                        Uri gmmIntentUri = Uri.parse(location);
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        startActivity(mapIntent);
                        l.setText(lx);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });


            }
        });



    }


}
