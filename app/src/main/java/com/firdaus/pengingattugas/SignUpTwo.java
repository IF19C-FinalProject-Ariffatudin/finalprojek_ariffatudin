package com.firdaus.pengingattugas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpTwo extends AppCompatActivity {

    ImageView btn_back;
    FloatingActionButton btn_next;
    EditText username, password,name, email_address;
    Button masuk;

    DatabaseReference reference, reference_username;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activy_register);

        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);


        masuk = findViewById(R.id.btn_login);
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(SignUpTwo.this, SignInAct.class);
                startActivity(main);
                finish();
            }
        });



        btn_next = (FloatingActionButton) findViewById(R.id.fab_register);





        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                //mengambil username pada firebase Database
                reference_username = FirebaseDatabase.getInstance().getReference().child("Users").child(username.getText().toString());
                reference_username.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //jika username tersedia
                        if(dataSnapshot.exists()){
                            Toast.makeText(getApplicationContext(),"Username sudah tersedia !", Toast.LENGTH_SHORT).show();
                            btn_next.setEnabled(true);

                        }
                        else {
                            // menyimpan data kepada local storage atau hp
                            SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(username_key, username.getText().toString());
                            editor.apply();

                            // menyimpan ke database
                            reference = FirebaseDatabase.getInstance().getReference()
                                    .child("Users").child(username.getText().toString());

                            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    dataSnapshot.getRef().child("username").setValue(username.getText().toString());
                                    dataSnapshot.getRef().child("password").setValue(password.getText().toString());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                            Intent gosignuptwo = new Intent(SignUpTwo.this, SignInAct.class);
                            startActivity(gosignuptwo);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }
}
