package com.training.testapp.models;

import com.google.gson.annotations.SerializedName;

public class CommentsData {

    @SerializedName("created_time")
    private String createdTime;

    private String text;
    private FromUser from;
    private String id;

    public String getCreatedTime() {
        return createdTime;
    }

    public String getText() {
        return text;
    }

    public FromUser getFrom() {
        return from;
    }

    public String getId() {
        return id;
    }
}
