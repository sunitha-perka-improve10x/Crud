package com.improve10x.crud.templates;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;

public class TemplateViewHolder extends RecyclerView.ViewHolder {

     TextView messageTextTxt;
     ImageButton deleteButton;
     TemplateViewHolder(@NonNull View itemView) {
        super(itemView);
        messageTextTxt = itemView.findViewById(R.id.message_txt);
        deleteButton = itemView.findViewById(R.id.delete_btn);
    }
}

