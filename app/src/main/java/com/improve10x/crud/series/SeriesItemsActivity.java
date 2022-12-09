package com.improve10x.crud.series;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.improve10x.crud.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesItemsActivity extends AppCompatActivity {
    public ArrayList<SeriesItem> seriesItems;
    public RecyclerView seriesItemsRv;
    public SeriesItemsAdapter seriesItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_item);
        getSupportActionBar().setTitle("SeriesItems");
        setupData();
        setupSeriesItemsRv();
        fetchSeriesItems();

    }

    private void fetchSeriesItems() {
        SeriesItemsApi seriesItemsApi = new SeriesItemsApi();
        SeriesItemService seriesItemService = seriesItemsApi.createSeriesItemSeries();
        Call <List<SeriesItem>> call = seriesItemService.fetchSeriesItems();
        call.enqueue(new Callback<List<SeriesItem>>() {

            @Override
            public void onResponse(Call<List<SeriesItem>> call, Response<List<SeriesItem>> response) {
                List<SeriesItem> seriesItems = response.body();
                seriesItemsAdapter.setData(seriesItems);
            }

            @Override
            public void onFailure(Call<List<SeriesItem>> call, Throwable t) {
                Toast.makeText(SeriesItemsActivity.this, "Failed Load Data", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void setupSeriesItemsRv() {
        seriesItemsRv = findViewById(R.id.series_items_rv);
        seriesItemsRv.setLayoutManager(new LinearLayoutManager(this));
        seriesItemsAdapter = new SeriesItemsAdapter();
        seriesItemsAdapter.setData(seriesItems);
        seriesItemsRv.setAdapter(seriesItemsAdapter);
    }

    private void setupData() {
        seriesItems = new ArrayList<>();
        SeriesItem seriesItem = new SeriesItem();
        seriesItem.imageUrl = "https://crudcrud.com/api/479dd07f8c1d482e9219f7dcb48e25f4/series";
        seriesItem.title = "Kung Fu Panda Movie Series";
        seriesItems.add(seriesItem);


    }
}