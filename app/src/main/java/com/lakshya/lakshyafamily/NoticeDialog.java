package com.lakshya.lakshyafamily;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NoticeDialog extends AppCompatDialogFragment {

    EditText notice_heading,notice_details;

    DatabaseReference databaseReference;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.notice_dialog,null);

        notice_heading = view.findViewById(R.id.notice_heading);
        notice_details= view.findViewById(R.id.notice_details);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton("send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        databaseReference = FirebaseDatabase.getInstance().getReference("notice");
                        Notice notice = new Notice(notice_heading.getText().toString().trim(),notice_details.getText().toString().trim());
                        String id = databaseReference.push().getKey();
                        databaseReference.child(id).setValue(notice);

                    }
                });



        return builder.create();
    }
}
