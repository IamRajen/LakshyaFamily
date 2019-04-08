package com.lakshya.lakshyafamily;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    private static final long ONE_MEGABYTE = 1024*1024;
    TextView student_DOB,student_phone,matric_sub,matric_board,matric_percent,intermediate_sub,intermediate_board,intermediate_percent,graduation_sub,graduation_board,graduation_percent,
            postgraduation_sub,postgraduation_board,postgraduation_percent,any_other_qualification,hobby,extra_activity,student_name,student_email;

    String email;
    ImageView stud_photo;

    StorageReference storageReference;

    DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("member");

        findViewByIds();

        getDetails();
        downloadPhoto();
    }

    private void getDetails() {

        SharedPreferences sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("username","");
        String password = sharedPreferences.getString("password","");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    MemberDetails memberDetails = dataSnapshot1.getValue(MemberDetails.class);
                    if (memberDetails.getStudent_email().equals(email))
                    {
                        student_name.setText(memberDetails.getStudent_name());
                        student_email.setText(memberDetails.getStudent_email());
                        student_DOB.setText(memberDetails.getStudent_DOB());
                        student_phone.setText(memberDetails.getStudent_phone());
                        matric_sub.setText(memberDetails.getMatric_sub());
                        matric_board.setText(memberDetails.getMatric_board());
                        matric_percent.setText(memberDetails.getMatric_percent());
                        intermediate_board.setText(memberDetails.intermediate_board);
                        intermediate_percent.setText(memberDetails.getIntermediate_percent());
                        intermediate_sub.setText(memberDetails.getIntermediate_sub());
                        postgraduation_sub.setText(memberDetails.getPostgraduation_sub());
                        postgraduation_percent.setText(memberDetails.getPostgraduation_percent());
                        postgraduation_board.setText(memberDetails.getPosgraduation_board());
                        any_other_qualification.setText(memberDetails.getAny_other_qualification());
                        hobby.setText(memberDetails.getHobby());
                        extra_activity.setText(memberDetails.getExtra_activity());

                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void downloadPhoto() {

        String path = "images/"+email.trim()+".jpg";

        final StorageReference riversRef = storageReference.child(path);


        riversRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);

                stud_photo.setMinimumHeight(dm.heightPixels);
                stud_photo.setMinimumWidth(dm.widthPixels);
                Toast.makeText(ProfileActivity.this, ""+riversRef, Toast.LENGTH_SHORT).show();
                stud_photo.setImageBitmap(bm);
            }
        });

    }

    private void findViewByIds() {

        student_name = findViewById(R.id.student_name);
        student_email = findViewById(R.id.student_email);
        student_DOB = findViewById(R.id.stud_dob);
        student_phone = findViewById(R.id.stud_phone);
        matric_sub = findViewById(R.id.matriculation_subject);
        matric_board = findViewById(R.id.matriculation_board);
        matric_percent = findViewById(R.id.matriculation_percent);
        intermediate_board = findViewById(R.id.Inter_board);
        intermediate_percent = findViewById(R.id.Inter_percent);
        intermediate_sub = findViewById(R.id.Inter_subject);
        graduation_board = findViewById(R.id.graduation_board);
        graduation_percent = findViewById(R.id.graduation_percent);
        graduation_sub = findViewById(R.id.graduation_subject);
        postgraduation_percent = findViewById(R.id.PostGraduation_percent);
        postgraduation_sub = findViewById(R.id.PostGraduation_subject);
        postgraduation_board = findViewById(R.id.PostGraduation_board);
        any_other_qualification = findViewById(R.id.Other_details);
        hobby= findViewById(R.id.stud_hobby);
        extra_activity = findViewById(R.id.stud_activities);
        stud_photo = findViewById(R.id.stud_photo);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }
}
