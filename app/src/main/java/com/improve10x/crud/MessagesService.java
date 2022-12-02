package com.improve10x.crud;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;

public interface MessagesService {
    @GET("messagesHistory")
    Call<List<Messages>> fetchMessages();

}
