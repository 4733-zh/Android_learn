package com.example.greendaotest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.greendaotest.greendao.DaoSession;
import com.example.greendaotest.greendao.UserDao;

import java.util.List;

public class TestActivity extends Activity {
    AppContext myApp;
    DaoSession daoSession;
    UserDao userDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        myApp = (AppContext) getApplication();
        daoSession = myApp.getDaoSession();
        userDao = daoSession.getUserDao();

        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setAge(22);

        //userDao.insert(user);//如果id重复，会报错，
        userDao.insertOrReplace(user);
        List list = selectAll();
        Log.d("info",list.get(0).toString());

        System.out.println(list.toString());

    }

    public List selectAll(){
        return userDao.loadAll();// 查询所有记录
    }
}
