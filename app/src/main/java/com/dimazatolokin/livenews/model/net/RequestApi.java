package com.dimazatolokin.livenews.model.net;

import com.dimazatolokin.livenews.model.net.response.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dimazatolokin on 04.05.18.
 */

public interface RequestApi {

    @GET("top-headlines?sources=google-news")
    Call<NewsResponse> getNews();

    @GET("top-headlines?country=ua&category=business&apiKey=2193e64fdf2346a7ae1461a9f48ac755")
    Call<NewsResponse> getNewsUa();
}
