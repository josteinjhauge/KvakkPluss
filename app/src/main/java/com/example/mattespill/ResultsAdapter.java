package com.example.mattespill;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.TestViewHolder> {
    private ArrayList<Results> mResultList;


    public static class TestViewHolder extends RecyclerView.ViewHolder {
        public TextView headerTextView;
        public TextView infoTextView;
        public ImageView mDeleteImage;
        public TestViewHolder(View itemView) {
            super(itemView);
            headerTextView = itemView.findViewById(R.id.header);
            infoTextView = itemView.findViewById(R.id.info);
        }
    }
    public ResultsAdapter(LayoutInflater layoutInflater, ArrayList<Results> resultsArrayList) {
        mResultList = resultsArrayList;
    }
    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_activity, parent, false);
        TestViewHolder evh = new TestViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, int position) {
        Results currentItem = mResultList.get(position);

        holder.headerTextView.setText(currentItem.getName());
        holder.infoTextView.setText(currentItem.getScore());
    }
    @Override
    public int getItemCount() {
        return mResultList.size();
    }
}
