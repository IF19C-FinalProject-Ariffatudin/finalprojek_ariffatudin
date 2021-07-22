package com.firdaus.pengingattugas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInAct extends AppCompatActivity {

    Button btn_sign_up;
    EditText xusername,xpassword;

    FloatingActionButton btn_login;

    DatabaseReference reference;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_sign_up = findViewById(R.id.btn_register);
        btn_login = (FloatingActionButton) findViewById(R.id.fab_login);

        xusername = findViewById(R.id.et_username);
        xpassword = findViewById(R.id.et_password);

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gosignup = new Intent(SignInAct.this, SignUpTwo.class);
                startActivity(gosignup);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //mengubah state menjadi loading
                btn_login.setEnabled(false);


                final String username = xusername.getText().toString();
                final String password = xpassword.getText().toString();

                if(username.isEmpty()){
                    Toast.makeText(getApplicationContext(), "username kosong !", Toast.LENGTH_SHORT).show();
                    //mengubah state menjadi loading
                    btn_login.setEnabled(true);

                }
                else{
                    if(password.isEmpty()){
                        Toast.makeText(getApplicationContext(), "password kosong !", Toast.LENGTH_SHORT).show();
                        //mengubah state menjadi loading
                        btn_login.setEnabled(true);

                    }
                    else{
                        reference = FirebaseDatabase.getInstance().getReference()
                                .child("Users").child(username);

                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    // mengambil data password dari firebase
                                    String passwordFromFirebase = dataSnapshot.child("password").getValue().toString();

                                    // memvalidasi password
                                    if(password.equals(passwordFromFirebase)){

                                        // simpan username (key)  ke local
                                        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString(username_key, xusername.getText().toString());
                                        editor.apply();

                                        // berpindah activity
                                        Intent gotohome = new Intent(SignInAct.this, MainActivity.class);
                                        startActivity(gotohome);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "password salah", Toast.LENGTH_SHORT).show();
                                        //mengubah state menjadi loading
                                        btn_login.setEnabled(true);

                                    }
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "username salah", Toast.LENGTH_SHORT).show();
                                    //mengubah state menjadi loading
                                    btn_login.setEnabled(true);

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(getApplicationContext(), "Database Error !", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });
    }
}
