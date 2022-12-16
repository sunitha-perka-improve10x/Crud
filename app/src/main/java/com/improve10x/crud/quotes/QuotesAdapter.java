package com.improve10x.crud.quotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesViewHolder> {
    private OnItemActionListener onItemActionListener;

    private List<Quote> quotes;
      void setOnItemActionListener(OnItemActionListener listener) {
     onItemActionListener = listener;
    }

    void setData(List<Quote> quoteList) {
        quotes = quoteList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quotes_item, parent, false);
        QuotesViewHolder quotesViewHolder = new QuotesViewHolder(view);
        return quotesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuotesViewHolder holder, int position) {
        Quote quote = quotes.get(position);
        holder.authorNameTxt.setText(quote.authorName);
        holder.quoteTxt.setText(quote.quoteText);
        if (quote.imgUrl != null && quote.imgUrl.isEmpty()== false){
            Picasso.get().load(quote.imgUrl).into(holder.imageImg);
        }
        holder.deleteBtn.setOnClickListener(view -> {
          onItemActionListener.onItemDelete(quote);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onItemClicked(quote);
        });
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }
}
