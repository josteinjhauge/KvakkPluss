package com.example.mattespill;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class Dialog extends DialogFragment {
    ListView listView;
    Button btnStats;
    Button btnClose;
    ArrayAdapter<QandA> adapter;
    ArrayList<QandA> qanda;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.results_dialog, null);

        getDialog().setTitle("@string/stats");
        listView = view.findViewById(R.id.list_results);
        btnClose = view.findViewById(R.id.btnClose);
        btnStats = view.findViewById(R.id.btnStats_dialog);

        adapter = new ArrayAdapter<QandA>(getActivity(), android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
                dismiss();
            }
        });

        btnStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), StatisticsActivity.class);
                startActivity(i);
                dismiss();
            }
        });


        return view;
    }
}
