package com.dimazatolokin.livenews.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dimazatolokin.livenews.LiveNewsApp;
import com.dimazatolokin.livenews.R;
import com.dimazatolokin.livenews.model.models.Article;
import com.dimazatolokin.livenews.view.adapter.MainListNewsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.recyclerNewsMain)
    RecyclerView recyclerNewsMain;

    private MainListNewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerNewsMain.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainListNewsAdapter(this);
        recyclerNewsMain.setAdapter(adapter);
        List<Article> items = LiveNewsApp.getInstanse().getDbManager().getArticles();
        adapter.setItems(items);
    }

    @Override
    protected void onResume() {
        super.onResume();

        LiveNewsApp.getInstanse().getNetworkService().getNews();
    }
}
