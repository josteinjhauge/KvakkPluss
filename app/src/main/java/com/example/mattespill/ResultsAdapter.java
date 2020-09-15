package com.example.mattespill;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder> {
    private LayoutInflater inflater;
    private List<Results> resultList;

    public ResultsAdapter(LayoutInflater inflater, List<Results> resultList) {
        this.inflater = inflater;
        this.resultList = resultList;
    }

    @Override
    public ResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = inflater.inflate(R.layout.list_activity, parent, false);
        ResultsViewHolder resultsViewHolder = new ResultsViewHolder(listItem);
        resultsViewHolder.view = listItem;
        resultsViewHolder.header = (TextView) listItem.findViewById(R.id.header);
        resultsViewHolder.info = (TextView) listItem.findViewById(R.id.info);

        return resultsViewHolder;
    }

    @Override
    public void onBindViewHolder(ResultsViewHolder holder, int position) {
        Results result = resultList.get(position);
        holder.header.setText(result.getName());
        holder.info.setText(result.getScore());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public static class ResultsViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView header, info;
        int position;

        public ResultsViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }
}
