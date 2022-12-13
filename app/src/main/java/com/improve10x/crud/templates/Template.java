package com.improve10x.crud.templates;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public class Template implements Serializable {
    @SerializedName("_id")
     String id;
    @SerializedName("messageText")
     String messageText;
}
