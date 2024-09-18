package com.example.networktest;

public interface HttpCallbackListenler {
    void onFinish(String response);
    void onError(Exception e);
}
