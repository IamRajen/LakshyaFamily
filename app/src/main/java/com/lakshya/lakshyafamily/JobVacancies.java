package com.lakshya.lakshyafamily;

import android.app.Dialog;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class JobVacancies extends Fragment {


    DatabaseReference databaseReference ;
    ListView listView;
    List<Jobs> list;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_job_vacancies, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("jobs");
        list = new ArrayList<>();
        listView = rootView.findViewById(R.id.job_list);


       FloatingActionButton floatingActionButton = rootView.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "on Friend", Toast.LENGTH_SHORT).show();
                showPopup();
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    Jobs jobs = dataSnapshot1.getValue(Jobs.class);
                    list.add(jobs);
                }

                JobAdapter jobAdapter = new JobAdapter(getActivity(),list);
                listView.setAdapter(jobAdapter);
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void showPopup()
    {

        JobsDialog jobsDialog = new JobsDialog();
        jobsDialog.show(getFragmentManager(),"Job Dialog");
    }




}