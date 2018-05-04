package com.dimazatolokin.livenews.model.net;

import android.util.Log;

import com.dimazatolokin.livenews.LiveNewsApp;
import com.dimazatolokin.livenews.model.models.Article;
import com.dimazatolokin.livenews.model.net.response.NewsResponse;
import com.dimazatolokin.livenews.util.Constans;

import java.io.IOException;

import io.realm.Realm;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dimazatolokin on 04.05.18.
 */

public class NetworkService {

    private static final String TAG = NetworkService.class.getSimpleName();

    private RequestApi api;

    public NetworkService() {

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        HttpUrl originalHttpUrl = original.url();

                        HttpUrl url = originalHttpUrl.newBuilder()
                                .addQueryParameter("apiKey", Constans.API_KEY)
                                .build();

                        Request.Builder requestBuilder = original.newBuilder().url(url);
                        Request request = requestBuilder.build();

                        return chain.proceed(request);
                    }
                });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constans.BASE_URL)
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(RequestApi.class);
    }

    public void getNews() {
        api.getNews().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                Log.i(TAG, "onResponse: ");
                for (Article article : response.body().getArticles()) {
                    LiveNewsApp.getInstanse().getDbManager().save(article);
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t);
            }
        });
    }

}
