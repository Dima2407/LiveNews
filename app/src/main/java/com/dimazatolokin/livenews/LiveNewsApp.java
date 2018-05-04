package com.dimazatolokin.livenews;

import android.app.Application;

import com.dimazatolokin.livenews.model.DbManager;
import com.dimazatolokin.livenews.model.net.NetworkService;

import io.realm.Realm;

/**
 * Created by dimazatolokin on 04.05.18.
 */

public class LiveNewsApp extends Application {

    private NetworkService networkService;
    private static LiveNewsApp instanse;
    private DbManager dbManager;

    public static LiveNewsApp getInstanse() {
        return instanse;
    }

    public LiveNewsApp() {
        instanse = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        networkService = new NetworkService();
        Realm.init(this);
        dbManager = new DbManager();
    }

    public NetworkService getNetworkService() {
        return networkService;
    }

    public DbManager getDbManager() {
        return dbManager;
    }
}
