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

public class JobsDialog extends AppCompatDialogFragment {

    EditText job_title,job_link,job_details;

    DatabaseReference databaseReference;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.jobs_dialog,null);

        job_title = view.findViewById(R.id.job_title);
        job_details= view.findViewById(R.id.job_details);
        job_link= view.findViewById(R.id.job_link);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
            .setPositiveButton("send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                databaseReference = FirebaseDatabase.getInstance().getReference("jobs");
                Jobs jobs = new Jobs(job_title.getText().toString().trim(),job_link.getText().toString().trim(),job_details.getText().toString().trim());
                String id = databaseReference.push().getKey();
                databaseReference.child(id).setValue(jobs);

            }
        });



        return builder.create();
    }
}
