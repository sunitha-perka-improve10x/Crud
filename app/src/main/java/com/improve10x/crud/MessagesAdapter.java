package com.improve10x.crud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesViewHolder> {
    public List<Messages> messagesList;
    public  void setData (List<Messages> messagesArrayList) {
        messagesList = messagesArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_item,parent,false);
        MessagesViewHolder messagesViewHolder = new MessagesViewHolder(view);
        return messagesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesViewHolder holder, int position) {
        Messages messages = messagesList.get(position);
        holder.nameTxt.setText(messages.name);
        holder.messagesTxt.setText(messages.message);
        holder.phoneNumberTxt.setText(messages.phoneNumber);
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }
}
