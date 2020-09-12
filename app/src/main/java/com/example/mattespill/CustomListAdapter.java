package com.example.mattespill;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomListAdapter extends ArrayAdapter {
    private final Activity context;
    private final ArrayList<Results> resultList;


    public CustomListAdapter(Activity context, ArrayList<Results> resultList) {
        super(context, R.layout.list_activity, resultList);
        this.context = context;
        this.resultList = resultList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listAct = inflater.inflate(R.layout.list_activity, null, true);

        TextView txtName = listAct.findViewById(R.id.header);
        TextView txtInfo = listAct.findViewById(R.id.info);

        txtName.setText(resultList.get(position).name);
        txtInfo.setText(resultList.get(position).score);

        return listAct;
    }
}
