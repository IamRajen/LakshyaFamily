package com.lakshya.lakshyafamily;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity implements View.OnClickListener{

    DatabaseReference databaseReference;

    static String useremail;
    ProgressBar login_progress;
    Button login;
    EditText user_email,password;
    private int flag ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference("member");


        setContentView(R.layout.activity_login_page);
        login = findViewById(R.id.login);
        user_email = findViewById(R.id.user_email);
        password= findViewById(R.id.user_password);
        login_progress = findViewById(R.id.login_progress);

        login.setOnClickListener(this);
    }

    private void showData(DataSnapshot dataSnapshot,String email,String password) {

        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            MemberDetails memberDetails = ds.getValue(MemberDetails.class);

            if(memberDetails.getStudent_email().equals(email) && memberDetails.password.equals(password))
            {


                SharedPreferences sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username",email);
                editor.putString("password",password);
                editor.commit();

                login_progress.setVisibility(View.GONE);

                finish();

                useremail = email;

                Intent intent = new Intent(this,ProfileActivity.class);
                startActivity(intent);


            }
             //Toast.makeText(this, ""+memberDetails.getStudent_email(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View v) {
        if(user_email.getText().toString().isEmpty())
        {
            user_email.setError("Email is Required..!!");
            user_email.requestFocus();

        }
        if (password.getText().toString().isEmpty())
        {
            password.setError("Password is Required..!!");
            password.requestFocus();
        }
        final String email = user_email.getText().toString().trim();
        final String user_password = password.getText().toString().trim();
        login_progress.setVisibility(View.VISIBLE);
        flag=0;
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot,email,user_password);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


}
