package com.improve10x.crud.quotes;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;

public class QuotesViewHolder extends RecyclerView.ViewHolder {
    TextView quoteTxt;
    TextView authorNameTxt;
    ImageView imageImg;
    ImageView deleteBtn;
    public QuotesViewHolder(@NonNull View itemView) {
        super(itemView);
        quoteTxt = itemView.findViewById(R.id.quote_text_txt);
        authorNameTxt = itemView.findViewById(R.id.authorname_txt);
        imageImg = itemView.findViewById(R.id.quote_image_img);
        deleteBtn = itemView.findViewById(R.id.delete_btn);
    }
}

