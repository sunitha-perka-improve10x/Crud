package com.improve10x.crud;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SeriesItemViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageImg;
    public TextView  titleTxt;

    public SeriesItemViewHolder(@NonNull View itemView) {
        super(itemView);
        imageImg = itemView.findViewById(R.id.image_img);
        titleTxt = itemView.findViewById(R.id.title_txt);
    }
}
