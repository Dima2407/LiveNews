package com.dimazatolokin.livenews.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dimazatolokin.livenews.LiveNewsApp;
import com.dimazatolokin.livenews.R;
import com.dimazatolokin.livenews.model.models.Article;
import com.dimazatolokin.livenews.model.models.Source;

import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Article> articles = LiveNewsApp.getInstanse().getDbManager().getArticles();
                Log.i(TAG, "onClick: ");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        LiveNewsApp.getInstanse().getNetworkService().getNews();
    }
}
