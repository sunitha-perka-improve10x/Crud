package com.improve10x.crud.quotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.improve10x.crud.R;
import com.improve10x.crud.api.CrudApi;
import com.improve10x.crud.api.CrudService;
import com.improve10x.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuotesActivity extends BaseActivity {

    private CrudService crudService;
    private ArrayList<Quote> quotes;
    private RecyclerView quotesRv;
    private QuotesAdapter quotesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        getSupportActionBar().setTitle("Quotes");
        setupApiService();
        handleAdd();
        setData();
        setupQuotesRv();
        setupQuotesAdapter();
    }

    private void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this,AddQuoteActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchQuotes();
    }

    private void fetchQuotes(){
        Call<List<Quote>> call = crudService.fetchQuotes();
        call.enqueue(new Callback<List<Quote>>() {
            @Override
            public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
                List<Quote> List = response.body();
                quotesAdapter.setData(List);
            }

            @Override
            public void onFailure(Call<List<Quote>> call, Throwable t) {
                showToast("Fail");
            }
        });
    }

    private void setupQuotesRv() {
        quotesRv = findViewById(R.id.quote_rv);
        quotesRv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void setupQuotesAdapter() {
        quotesAdapter = new QuotesAdapter();
        quotesRv.setAdapter(quotesAdapter);
        quotesAdapter.setData(quotes);
        quotesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Quote quote) {
                showToast("On itemClicked");
            }

            @Override
            public void onItemDelete(Quote quote) {
                showToast("On item Delete");
                deleteQuotes(quote);
            }

            private void deleteQuotes(Quote quote) {
                Call<Void> call = crudService.deleteQuote(quote.id);
                call .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        showToast("success");
                        fetchQuotes();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        showToast("failed");

                    }
                });
            }

            @Override
            public void onItemEdit(Quote quotes) {
                showToast("On item Edit");

            }
        });
    }
    private  void setData() {
        quotes = new ArrayList<>();
        Quote quote =  new Quote();
        quote.authorName = "sunitha";
        quote.quoteText = "Hello";
        quote.category = "djljdd";
        quote.imgUrl = "https://www.kochiesbusinessbuilders.com.au/wp-content/uploads/2022/02/motivational-quote.jpg";
        quotes.add(quote);
    }
}


