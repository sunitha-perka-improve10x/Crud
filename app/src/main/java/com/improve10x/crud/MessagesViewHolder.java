package com.improve10x.crud;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessagesViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView messages;
    public TextView phoneNumber;

    public MessagesViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name_txt);
        messages = itemView.findViewById(R.id.message_txt);
        phoneNumber = itemView.findViewById(R.id.phonenumber_txt);
    }
}
