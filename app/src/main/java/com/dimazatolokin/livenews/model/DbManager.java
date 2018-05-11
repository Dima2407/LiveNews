package com.dimazatolokin.livenews.model;

import com.dimazatolokin.livenews.model.models.Article;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by dimazatolokin on 04.05.18.
 */

public class DbManager {

    public void save(RealmObject object) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(object);
        realm.commitTransaction();
        realm.close();
    }

    public List<Article> getArticles() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Article.class).findAll();
    }

    public RealmResults<Article> getRealmResultArticles() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Article.class).findAllAsync();
    }
}
