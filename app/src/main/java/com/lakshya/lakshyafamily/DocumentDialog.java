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

public class DocumentDialog extends AppCompatDialogFragment {

    EditText documnet_name,document_details;

    DatabaseReference databaseReference;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_document,null);

        documnet_name = view.findViewById(R.id.document_name);
        document_details= view.findViewById(R.id.document_details);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton("send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        databaseReference = FirebaseDatabase.getInstance().getReference("document");
                        Document document = new Document(documnet_name.getText().toString().trim(),document_details.getText().toString().trim());
                        String id = databaseReference.push().getKey();
                        databaseReference.child(id).setValue(document);

                    }
                });



        return builder.create();
    }
}
