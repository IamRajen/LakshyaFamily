package com.lakshya.lakshyafamily;

import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class Student_Details extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference databaseReference;
    private StorageReference mStorageRef;

    private static final int PICK_IMAGE_REQUST = 101;
    TextView student_name,student_email;
    ImageView student_photo,change_photo;
    FirebaseAuth mAuth;

    EditText password1,student_DOB1,student_phone1,matric_sub1,matric_board1,matric_percent1,intermediate_sub1,intermediate_board1,intermediate_percent1,graduation_sub1,graduation_board1,graduation_percent1,
                postgraduation_sub1,posgraduation_board1,postgraduation_percent1,any_other_qualification1,answer1,hobby1,extra_activity1;
    CheckBox have_joined1;

    Button submit1;

    Uri photo ;

    String password,student_DOB,student_phone,matric_sub,matric_board,matric_percent,intermediate_sub,intermediate_board,intermediate_percent,graduation_sub,graduation_board,graduation_percent,
            postgraduation_sub,posgraduation_board,postgraduation_percent,any_other_qualification,answer,hobby,extra_activity,have_joined;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__details);


        //firebase work...
        databaseReference = FirebaseDatabase.getInstance().getReference("member");
        mStorageRef = FirebaseStorage.getInstance().getReference();

        initialize();




        student_name = findViewById(R.id.student_name);
        student_email = findViewById(R.id.student_email);
        student_photo = findViewById(R.id.user_photo);
        change_photo = findViewById(R.id.change_photo);

        change_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,PICK_IMAGE_REQUST);
            }
        });


        submit1.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();


        student_name.setText(getIntent().getStringExtra("username"));
        student_email.setText(getIntent().getStringExtra("useremail"));



    }

    private void initialize() {

        password1 = findViewById(R.id.password);
        student_DOB1 =findViewById(R.id.stud_dob);
        student_phone1=findViewById(R.id.stud_phone);
        matric_sub1=findViewById(R.id.matriculation_subject);
        matric_board1=findViewById(R.id.matriculation_board);
        matric_percent1=findViewById(R.id.matriculation_percent);
        intermediate_sub1=findViewById(R.id.Inter_subject);
        intermediate_board1=findViewById(R.id.matriculation_board);
        intermediate_percent1=findViewById(R.id.Inter_percent);
        graduation_sub1=findViewById(R.id.graduation_subject);
        graduation_board1=findViewById(R.id.graduation_board);
        graduation_percent1=findViewById(R.id.graduation_percent);
        postgraduation_sub1=findViewById(R.id.PostGraduation_subject);
        posgraduation_board1=findViewById(R.id.PostGraduation_board);
        postgraduation_percent1=findViewById(R.id.graduation_percent);
        any_other_qualification1=findViewById(R.id.Other_details);
        answer1=findViewById(R.id.answer);
        hobby1 = findViewById(R.id.stud_hobby);
        extra_activity1 = findViewById(R.id.stud_activities);
        have_joined1 = findViewById(R.id.have_joined);
        submit1 = findViewById(R.id.submit);
    }

    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null)
        {
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUST && resultCode== RESULT_OK && data!=null && data.getData()!=null)
        {
            photo = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),photo);
                student_photo.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Glide.with(this).load(data.getData()).into(student_photo);

        }
    }
    private void uploadPhoto() {

        String addr = "images/"+student_email.getText().toString().trim()+".jpg";
        Toast.makeText(this, ""+photo, Toast.LENGTH_SHORT).show();
        StorageReference riversRef = mStorageRef.child(addr);

        riversRef.putFile(photo)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content

                        Toast.makeText(Student_Details.this, "success", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        Toast.makeText(Student_Details.this, "failed to upload", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    @Override
    public void onClick(View v) {

        password = password1.getText().toString().trim();
        student_DOB=student_DOB1.getText().toString().trim();
        student_phone=student_phone1.getText().toString().trim();
        matric_sub=matric_sub1.getText().toString().trim();
        matric_board=matric_board1.getText().toString().trim();
        matric_percent=matric_percent1.getText().toString().trim();
        intermediate_sub=intermediate_sub1.getText().toString().trim();
        intermediate_percent=intermediate_percent1.getText().toString().trim();
        intermediate_board=intermediate_board1.getText().toString().trim();
        graduation_sub=graduation_sub1.getText().toString().trim();
        graduation_board=graduation_board1.getText().toString().trim();
        graduation_percent=graduation_percent1.getText().toString().trim();
        postgraduation_sub=postgraduation_sub1.getText().toString().trim();
        posgraduation_board=posgraduation_board1.getText().toString().trim();
        postgraduation_percent=postgraduation_percent1.getText().toString().trim();
        any_other_qualification=any_other_qualification1.getText().toString().trim();
        answer=answer1.getText().toString().trim();
        hobby=hobby1.getText().toString().trim();
        extra_activity=extra_activity1.getText().toString().trim();

        have_joined = have_joined1.isChecked()+"";

        Toast.makeText(this, have_joined1.isChecked()+"", Toast.LENGTH_LONG).show();

        String id = databaseReference.push().getKey();

        Toast.makeText(this, id, Toast.LENGTH_LONG).show();

        MemberDetails memberDetails = new MemberDetails(password,student_DOB,student_phone,matric_sub,matric_board,matric_percent,intermediate_sub,intermediate_board,intermediate_percent,graduation_sub,graduation_board,graduation_percent,
                postgraduation_sub,posgraduation_board,postgraduation_percent,any_other_qualification,answer,hobby,extra_activity,student_name.getText().toString().trim(),student_email.getText().toString().trim(),have_joined);


        databaseReference.child(id).setValue(memberDetails);
       // photo = Uri.parse(String.valueOf(student_photo));
        uploadPhoto();
        Toast.makeText(this, "Information Added", Toast.LENGTH_SHORT).show();

    }


}
