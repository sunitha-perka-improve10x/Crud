package com.improve10x.crud;

import com.improve10x.crud.messages.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TemplatesService {
    @GET("sunithaTemplates")
    Call<List<Template>> fetchTemplate();
    @POST("sunithaTemplate")
    Call<Template> createTemplate(@Body Template templates);
    @DELETE("sunithaTemplate/{id}")
    Call<Void>deleteMessage(@Path("id")String id);
}
