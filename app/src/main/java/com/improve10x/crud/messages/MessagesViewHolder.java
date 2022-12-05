package com.improve10x.crud.messages;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;

public class MessagesViewHolder extends RecyclerView.ViewHolder {
    public TextView nameTxt;
    public TextView messagesTxt;
    public TextView phoneNumberTxt;
    public ImageButton deleteBtn;

    public MessagesViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTxt = itemView.findViewById(R.id.name_txt);
        messagesTxt = itemView.findViewById(R.id.message_txt);
        phoneNumberTxt = itemView.findViewById(R.id.phonenumber_txt);
        deleteBtn = itemView.findViewById(R.id.delete_btn);

    }
}
