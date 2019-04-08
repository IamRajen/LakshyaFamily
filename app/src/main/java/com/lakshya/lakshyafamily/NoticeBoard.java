package com.lakshya.lakshyafamily;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NoticeBoard extends Fragment {

    TextView notice_heading,notice_details;
    CardView notice_card_view;
    ListView listView ;
    List<Notice> notices;
     View rootView;

     RecyclerView myNoticeList;

    DatabaseReference databaseReference;


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_notice_board, container, false);

        listView = rootView.findViewById(R.id.notice_list);
        notices = new ArrayList<>();




        databaseReference = FirebaseDatabase.getInstance().getReference("notice");

        FloatingActionButton floatingActionButton = rootView.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "on Friend", Toast.LENGTH_SHORT).show();
                showPopUp();
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
                notices.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    Notice notice = dataSnapshot1.getValue(Notice.class);
                    notices.add(notice);
                }

                NoticeAdapter noticeAdapter = new NoticeAdapter(getActivity(),notices);
                listView.setAdapter(noticeAdapter);
            }





            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void showPopUp() {


        NoticeDialog noticeDialog = new NoticeDialog();
        noticeDialog.show(getFragmentManager(),"Notice Dialog");
    }
}
