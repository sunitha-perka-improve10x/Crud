package com.improve10x.crud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TemplatesAdapter extends RecyclerView.Adapter<TemplateViewHolder> {
    OnItemActionListener onItemActionListener;
    public  void setOnItemActionListener(OnItemActionListener listener) {
        onItemActionListener = listener;
    }

        public List<Template> templates;
    public  void setData(List<Template> templateArrayList) {
        templates = templateArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TemplateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_item,parent,false);
        TemplateViewHolder templateViewHolder = new TemplateViewHolder(view);
        return templateViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateViewHolder holder, int position) {
        Template template = templates.get(position);
        holder.message.setText(template.messageText);

    }

    @Override
    public int getItemCount() {
        return templates.size();
    }
}