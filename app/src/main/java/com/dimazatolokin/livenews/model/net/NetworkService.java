package com.dimazatolokin.livenews.model.net;

import android.util.Log;

import com.dimazatolokin.livenews.LiveNewsApp;
import com.dimazatolokin.livenews.model.models.Article;
import com.dimazatolokin.livenews.model.net.eventsBus.NextNewSuccessEvent;
import com.dimazatolokin.livenews.model.net.eventsBus.RxBus;
import com.dimazatolokin.livenews.model.net.response.NewsResponse;
import com.dimazatolokin.livenews.util.Constans;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        api = retrofit.create(RequestApi.class);
    }

    public void getNews() {

        try {

            Observable<NewsResponse> newsUa = api.getNewsUa();
            newsUa.subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .flatMap(new Func1<NewsResponse, Observable<Article>>() {
                        @Override
                        public Observable<Article> call(NewsResponse newsResponse) {
                            List<Article> articles = newsResponse.getArticles();
                            return Observable.from(articles);
                        }
                    })
                    .doOnNext(new Action1<Article>() {
                        @Override
                        public void call(Article article) {
                            LiveNewsApp.getInstanse().getDbManager().save(article);
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(new Action1<Article>() {
                        @Override
                        public void call(Article article) {
                            RxBus.getInstance().post(new NextNewSuccessEvent());
                        }
                    })
                    .doOnError(throwable -> {
                        throwable.printStackTrace();
                    })
                    .subscribe();
        } catch (Exception e) {
            Log.e(TAG, "getNews: ", e);
        }

    }

}
