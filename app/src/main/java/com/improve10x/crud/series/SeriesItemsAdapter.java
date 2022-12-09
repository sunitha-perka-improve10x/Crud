package com.improve10x.crud.series;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeriesItemsAdapter extends RecyclerView.Adapter<SeriesItemViewHolder> {
    public List<SeriesItem> seriesItems;
    public void setData(List<SeriesItem> seriesItemsList) {

        seriesItems = seriesItemsList;
    }
    @NonNull
    @Override
    public SeriesItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seriesitem_item,parent,false);
        SeriesItemViewHolder seriesItemViewHolder = new SeriesItemViewHolder(view);
        return seriesItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesItemViewHolder holder, int position) {
        SeriesItem seriesItem = seriesItems.get(position);
        holder.titleTxt.setText(seriesItem.title);
        Picasso.get().load(seriesItem.imageUrl).into(holder.imageImg);

    }

    @Override
    public int getItemCount() {
        return seriesItems.size();
    }
}
