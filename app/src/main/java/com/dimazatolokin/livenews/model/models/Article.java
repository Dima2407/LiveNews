package com.dimazatolokin.livenews.model.models;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by dimazatolokin on 04.05.18.
 */

public class Article extends RealmObject{

    @SerializedName("source")
    private Source source;
    @SerializedName("author")
    private String author;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("url")
    private String url;
    @SerializedName("urlToImage")
    private String urlToImage;
    @PrimaryKey
    @SerializedName("publishedAt")
    private String publishedAt;

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
/*
    public String getPrettyTime() {
        if (TextUtils.isEmpty(getPublishedAt())) {
            return "";
        }
        String[] strings = getPublishedAt().split("T");
        String[] dates = strings[0].split("-");
        int indexTime = strings[1].lastIndexOf(":");
        String time = strings[1].substring(0, indexTime);
        String date = dates[2].concat(".").concat(dates[1]).concat(".").concat(dates[0]);
        return time.concat("  ").concat(date);
    }*/

    public String getSourceName() {
        return getSource().getName();
    }
}
