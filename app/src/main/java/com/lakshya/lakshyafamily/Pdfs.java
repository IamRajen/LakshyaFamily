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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Pdfs extends Fragment  {

    private DatabaseReference databaseReference;
    ListView listView ;
    List<Document> list;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_pdfs, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("document");
        listView = rootView.findViewById(R.id.document_list);
        list = new ArrayList<>();

        FloatingActionButton floatingActionButton = rootView.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    Document document = dataSnapshot1.getValue(Document.class);
                    list.add(document);
                }

                DocumentAdapter documentAdapter = new DocumentAdapter(getActivity(),list);
                listView.setAdapter(documentAdapter);
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showPopup() {

        DocumentDialog documentDialog = new DocumentDialog();
        documentDialog.show(getFragmentManager(),null);

    }
}