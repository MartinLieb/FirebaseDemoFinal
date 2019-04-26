package com.example.firebasedemofinal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button btn;
    private Button btn2;
    private TextView textView;
    private ChildEventListener textlisten;

    DatabaseReference firebaseDemo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        btn = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);

        firebaseDemo = FirebaseDatabase.getInstance().getReference().child("text");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDemo.push().setValue(editText.getText().toString());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDemo.setValue(null);
            }
            });

        textlisten = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String test = dataSnapshot.getValue().toString();
                textView.append(test + "\n");
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                textView.setText("");

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        firebaseDemo.addChildEventListener(textlisten);
    }
}
