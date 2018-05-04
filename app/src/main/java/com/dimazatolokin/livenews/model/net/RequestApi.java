package com.dimazatolokin.livenews.model.net;

import com.dimazatolokin.livenews.model.net.response.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dimazatolokin on 04.05.18.
 */

public interface RequestApi {

    @GET("top-headlines?sources=google-news")
    Call<NewsResponse> getNews();
}
