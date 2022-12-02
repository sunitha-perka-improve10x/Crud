package com.improve10x.crud;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MessagesService {
    @GET("sunithaMessagesHistory")
    Call<List<Messages>> fetchMessages();

    @POST("sunithaMessagesHistory")
    Call<Messages> createMessages(@Body Messages messages);

}
