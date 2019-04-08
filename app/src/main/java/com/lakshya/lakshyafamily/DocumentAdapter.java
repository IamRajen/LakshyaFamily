package com.lakshya.lakshyafamily;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DocumentAdapter extends ArrayAdapter<Document> {

    private Activity context;
    private List<Document> documentList;

    public DocumentAdapter(Activity context, List<Document> documentList)
    {
        super(context, R.layout.notice_receiver,documentList);
        this.context=context;
        this.documentList=documentList;
    }
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.document_receiver,null,true);

        TextView document_name = (TextView) listViewItem.findViewById(R.id.document_name);
        TextView document_details = (TextView) listViewItem.findViewById(R.id.document_details);

        Document document = documentList.get(position);

        document_name.setText(document.getDoc_name());
        document_details.setText(document.getDetails());


        return listViewItem;
    }
}
