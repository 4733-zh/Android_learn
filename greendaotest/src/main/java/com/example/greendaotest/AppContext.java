package com.example.greendaotest;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.greendaotest.greendao.DaoMaster;
import com.example.greendaotest.greendao.DaoSession;

public class AppContext extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initDreenDao();
    }

    private void initDreenDao(){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "demo.db",null);
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        DaoMaster daoMasteraster = new DaoMaster(db);
        daoSession = daoMasteraster.newSession();
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }
}
