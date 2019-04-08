package com.lakshya.lakshyafamily;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectUser extends AppCompatActivity {

    Button member_btn,student_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);
        member_btn = findViewById(R.id.select_member);
        student_btn = findViewById(R.id.select_student);
        member_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectUser.this,LoginPage.class));
            }
        });

        student_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectUser.this,MainActivity.class));
            }
        });
    }



}
