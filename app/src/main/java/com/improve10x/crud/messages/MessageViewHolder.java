package com.improve10x.crud.messages;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;

public class MessageViewHolder extends RecyclerView.ViewHolder {
     TextView nameTxt;
     TextView messagesTextTxt;
     TextView phoneNumberTxt;
     ImageButton deleteBtn;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTxt = itemView.findViewById(R.id.name_txt);
        messagesTextTxt = itemView.findViewById(R.id.message_txt);
        phoneNumberTxt = itemView.findViewById(R.id.phonenumber_txt);
        deleteBtn = itemView.findViewById(R.id.delete_btn);

    }
}
