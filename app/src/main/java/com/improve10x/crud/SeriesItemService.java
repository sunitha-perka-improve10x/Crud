package com.improve10x.crud;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SeriesItemService {
    @GET(Constants.SERIESITEMS_END_POINT)
    Call<List<SeriesItem>> fetchSeriesItems();

    @POST(Constants.SERIESITEMS_END_POINT)
    Call<SeriesItem> createSeriesItems(@Body SeriesItem seriesItem);
}
