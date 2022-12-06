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
    Call<List<Template>> fetchTemplates();

    @POST("sunithaTemplates")
    Call<Template> createTemplate(@Body Template template);

    @DELETE("sunithaTemplates/{id}")
    Call<Void>deleteTemplate(@Path("id")String id);
}
