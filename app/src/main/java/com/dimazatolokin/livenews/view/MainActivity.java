package com.dimazatolokin.livenews.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.dimazatolokin.livenews.LiveNewsApp;
import com.dimazatolokin.livenews.R;
import com.dimazatolokin.livenews.model.models.Article;
import com.dimazatolokin.livenews.model.net.eventsBus.NextNewSuccessEvent;
import com.dimazatolokin.livenews.model.net.eventsBus.RxBus;
import com.dimazatolokin.livenews.view.adapter.MainListNewsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.recyclerNewsMain)
    RecyclerView recyclerNewsMain;

    private MainListNewsAdapter adapter;
    private CompositeSubscription subscription;
    private RealmChangeListener<Article> articleRealmChangeListener;
    private RealmResults<Article> realmResults;
    private List<Article> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        subscription = new CompositeSubscription();
        recyclerNewsMain.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainListNewsAdapter(this);
        recyclerNewsMain.setAdapter(adapter);
        items = LiveNewsApp.getInstanse().getDbManager().getArticles();
        //adapter.setItems(items);

        subscription.add(RxBus.getInstance().toObserverable().subscribe(event -> {
            if (event instanceof NextNewSuccessEvent) {
                refreshAdapter();
            }
        }));

        //realmResults = LiveNewsApp.getInstanse().getDbManager().getRealmResultArticles();
        articleRealmChangeListener = new RealmChangeListener<Article>() {
            @Override
            public void onChange(Article article) {
                adapter.notifyDataSetChanged();
                Log.i(TAG, "onChange: items.size = " + items.size());
            }
        };
        adapter.setItems(items);
    }


    private void refreshAdapter() {
       adapter.notifyDataSetChanged();
        Log.i(TAG, "refreshAdapter: items.size = " + items.size());
    }

    @Override
    protected void onResume() {
        super.onResume();

        LiveNewsApp.getInstanse().getNetworkService().getNews();
    }
}
