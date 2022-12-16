package com.improve10x.crud.quotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.improve10x.crud.R;
import com.improve10x.crud.api.CrudApi;
import com.improve10x.crud.api.CrudService;
import com.improve10x.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddQuoteActivity extends BaseActivity {
    private CrudService crudService;
    private EditText quoteTxt;
    private EditText authorNameTxt;
    private EditText categoryTxt;
    private EditText imageUrlTxt;
    private Button editBTn;
    private Button addBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote);
        setupViews();
        setupApiService();
        handleAdd();

    }

    private void handleAdd() {
        addBtn.setOnClickListener(view -> {
            String quote = quoteTxt.getText().toString();
            String authorName = authorNameTxt.getText().toString();
            String category = categoryTxt.getText().toString();
            String imageUrl = imageUrlTxt.getText().toString();
            createQuote(quote,authorName,category,imageUrl);

        });
    }

    private void createQuote(String quote, String authorName, String category, String imageUrl) {
        Quote quotes = new Quote();
        quotes.quoteText = quote;
        quotes.authorName = authorName;
        quotes.category = category;
        quotes.imgUrl = imageUrl;

        Call<Quote> call = crudService.createQuote(quotes);
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