package com.lakshya.lakshyafamily;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class NoticeAdapter extends ArrayAdapter<Notice> {

    private Activity context;
    private List<Notice> noticeList;

    public NoticeAdapter(Activity context,List<Notice> noticeList)
    {
        super(context, R.layout.notice_receiver,noticeList);
        this.context=context;
        this.noticeList=noticeList;
    }
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.notice_receiver,null,true);

        TextView notice_heading = (TextView) listViewItem.findViewById(R.id.notice_heading);
        TextView notice_details = (TextView) listViewItem.findViewById(R.id.notice_details);

        Notice notice = noticeList.get(position);

        notice_heading.setText(notice.getHeading());
        notice_details.setText(notice.getDetails());


        return listViewItem;
    }
}
