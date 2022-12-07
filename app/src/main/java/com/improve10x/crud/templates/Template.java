package com.improve10x.crud.templates;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public class Template {
    @SerializedName("_id")
     String id;
    @SerializedName("messageText")
     String messageText;
}
