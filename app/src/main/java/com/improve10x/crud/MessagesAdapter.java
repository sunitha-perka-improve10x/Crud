package com.improve10x.crud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesViewHolder> {
    public ArrayList<Messages> messagesList;
    public  void setData (ArrayList<Messages> messagesArrayList) {
        messagesList = messagesArrayList;
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
        holder.name.setText(messages.name);
        holder.messages.setText(messages.message);
        holder.phoneNumber.setText(messages.phoneNumber);
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }
}
