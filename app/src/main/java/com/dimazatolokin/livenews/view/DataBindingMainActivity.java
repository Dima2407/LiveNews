package com.dimazatolokin.livenews.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.dimazatolokin.livenews.LiveNewsApp;
import com.dimazatolokin.livenews.R;
import com.dimazatolokin.livenews.model.models.Article;

import java.util.List;

import io.realm.RealmChangeListener;

public class DataBindingMainActivity extends AppCompatActivity {

    private RealmChangeListener<Article> articleRealmChangeListener;
    private ViewDataBinding binding;
    private Article value;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.ctivity_data_binding_main);
       /* Article article = new Article();
        article.setAuthor("Petya");
        article.setTitle("Best new!!!");
        binding.setVariable(1, article);*/

        LiveNewsApp.getInstanse().getNetworkService().getNews();
        List<Article> articles = LiveNewsApp.getInstanse().getDbManager().getArticles();
        value = articles.get(0);
        binding.setVariable(1, value);
        binding.setVariable(2, new MainPresenter());

        articleRealmChangeListener = new RealmChangeListener<Article>() {
            @Override
            public void onChange(Article article) {
                binding.setVariable(1, article);
            }
        };
    }

    public class MainPresenter {
        public void showInfoDialog() {
            AlertDialog dialog = new AlertDialog.Builder(DataBindingMainActivity.this).create();
            dialog.setTitle(value.getTitle());
            dialog.setMessage(value.getAuthor());
            dialog.show();
        }
    }
}
