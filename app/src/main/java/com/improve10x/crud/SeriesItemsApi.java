package com.improve10x.crud;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SeriesItemsApi {
     SeriesItemService createSeriesItemSeries() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SeriesItemService seriesItemService = retrofit.create(SeriesItemService.class);
        return seriesItemService;
    }
}
