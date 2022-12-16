package com.improve10x.crud.quotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.improve10x.crud.Constants;
import com.improve10x.crud.R;
import com.improve10x.crud.api.CrudApi;
import com.improve10x.crud.api.CrudService;
import com.improve10x.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditQuoteActivity extends BaseActivity {
    private CrudService crudService;
    private EditText quoteTxt;
    private EditText authorNameTxt;
    private EditText categoryTxt;
    private EditText imageUrlTxt;
    private Button editBTn;
    private Button addBtn;
    private Quote quote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote);
        setupViews();
        setupApiService();
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_QUOTE)){
            getSupportActionBar().setTitle("Edit Quote");
            quote = (Quote) intent.getSerializableExtra(Constants.KEY_QUOTE);
            showData();
            handleEdit();
            showEditBtn();
        } else {
            getSupportActionBar().setTitle("Add Quote");
            handleAdd();
            showAddBtn();
        }
    }

    private void showAddBtn() {
        editBTn.setVisibility(View.GONE);
        addBtn.setVisibility(View.VISIBLE);
    }

    private void showEditBtn() {
        addBtn.setVisibility(View.GONE);
        editBTn.setVisibility(View.VISIBLE);
    }

    private void showData() {
        quoteTxt.setText(quote.quoteText);
        authorNameTxt.setText(quote.authorName);
        categoryTxt.setText(quote.category);
        imageUrlTxt.setText(quote.imgUrl);
    }

    private void handleEdit() {
        editBTn.setOnClickListener(view -> {
            String quoteText = quoteTxt.getText().toString();
            String authorName = authorNameTxt.getText().toString();
            String category = categoryTxt.getText().toString();
            String imageUrl = imageUrlTxt.getText().toString();
            Quote updatedQuote = createQuote(quoteText,authorName,category,imageUrl);
            updateQuote(quote.id,updatedQuote);

        });
    }

    private void updateQuote(String id, Quote updatedQuote) {
        Call<Void> call = crudService.updatedQuote(id,updatedQuote);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Success");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed");
            }
        });
    }

    private void handleAdd() {
        addBtn.setOnClickListener(view -> {
            String quotes = quoteTxt.getText().toString();
            String authorName = authorNameTxt.getText().toString();
            String category = categoryTxt.getText().toString();
            String imageUrl = imageUrlTxt.getText().toString();
            Quote quote = createQuote(quotes,authorName,category,imageUrl);
            saveQuote(quote);
        });
    }

    private Quote createQuote(String quote, String authorName, String category, String imageUrl) {
        Quote quotes = new Quote();
        quotes.quoteText = quote;
        quotes.authorName = authorName;
        quotes.category = category;
        quotes.imgUrl = imageUrl;
        return quotes;
    }

    private void saveQuote(Quote quote) {
        Call<Quote> call = crudService.createQuote(quote);
        call.enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {
                showToast("SuccessFully loaded");
                finish();
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                showToast("Failure");
            }
        });
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void setupViews() {
        editBTn = findViewById(R.id.edit_btn);
        addBtn = findViewById(R.id.add_btn);
        quoteTxt = findViewById(R.id.quote_txt);
        authorNameTxt =findViewById(R.id.authorname_txt);
        categoryTxt = findViewById(R.id.category_txt);
        imageUrlTxt = findViewById(R.id.imageurl_txt);
    }
}