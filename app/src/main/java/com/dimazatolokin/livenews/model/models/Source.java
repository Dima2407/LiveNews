package com.dimazatolokin.livenews.model.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by dimazatolokin on 04.05.18.
 */

public class Source extends RealmObject{

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
