package com.example.mattespill;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomListAdapter extends ArrayAdapter<Results> implements View.OnClickListener {
    Context context;
    private ArrayList<Results> resultList;

    private static class ViewHolder {
        TextView txtName;
        TextView txtScore;
    }

    public CustomListAdapter(ArrayList<Results> resultList, Context context) {
        super(context, R.layout.list_view_activity, resultList);
        this.context = context;
        this.resultList = resultList;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null){
            listItem = LayoutInflater.from(context)
                    .inflate(R.layout.list_view_activity, parent, false);
        }

        Results current = resultList.get(position);
/*      LayoutInflater inflater = context.getLayoutInflater();
        View listAct = inflater.inflate(R.layout.list_view_activity, null, true);

        TextView txtName = listAct.findViewById(R.id.header);
        TextView txtInfo = listAct.findViewById(R.id.info);

        txtName.setText(resultList.get(position).name);
        txtInfo.setText(resultList.get(position).score);
*/
        TextView txtName = listItem.findViewById(R.id.header);
        txtName.setText(current.getName());

        TextView txtInfo = listItem.findViewById(R.id.info);
        txtInfo.setText(current.getScore());

        return listItem;
    }


}
