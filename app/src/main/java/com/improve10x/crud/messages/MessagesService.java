package com.improve10x.crud.messages;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MessagesService {
    @GET("sunithaMessagesHistory")
    Call<List<Messages>> fetchMessages();

    @POST("sunithaMessagesHistory")
    Call<Messages> createMessages(@Body Messages messages);

    @DELETE("sunithaMessagesHistory/{id}")
    Call<Void> deleteMessages(@Path("id")String id);

}
