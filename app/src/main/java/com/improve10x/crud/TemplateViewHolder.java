package com.improve10x.crud;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TemplateViewHolder extends RecyclerView.ViewHolder {

    public TextView messageTextTxt;
    public ImageButton deleteButton;
    public TemplateViewHolder(@NonNull View itemView) {
        super(itemView);
        messageTextTxt = itemView.findViewById(R.id.message_txt);
        deleteButton = itemView.findViewById(R.id.delete_btn);
    }
}

