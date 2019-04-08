package com.lakshya.lakshyafamily;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class JobAdapter extends ArrayAdapter<Jobs> {

    private Activity context;
    private List<Jobs> jobsList;

    public JobAdapter(Activity context, List<Jobs> jobList)
    {
        super(context, R.layout.job_receiver,jobList);
        this.context=context;
        this.jobsList=jobList;
    }
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.job_receiver,null,true);

        TextView job_title = (TextView) listViewItem.findViewById(R.id.job_title);
        TextView job_link = (TextView) listViewItem.findViewById(R.id.job_link);
        TextView job_details = (TextView) listViewItem.findViewById(R.id.job_details);

        Jobs jobs = jobsList.get(position);

        job_title.setText(jobs.getTitle());
        job_details.setText(jobs.getDetails());
        job_link.setText(jobs.getLink());


        return listViewItem;
    }
}
