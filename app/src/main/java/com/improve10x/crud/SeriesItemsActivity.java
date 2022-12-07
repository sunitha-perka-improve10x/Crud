package com.improve10x.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.improve10x.crud.templates.Template;
import com.improve10x.crud.templates.TemplatesAdapter;

import java.util.ArrayList;

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