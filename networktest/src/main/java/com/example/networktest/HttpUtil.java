package com.example.networktest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {
    public static void sendHttpRequest(final String address,final HttpCallbackListenler listenler){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    if (listenler == null) {
                        listenler.onFinish(response.toString());
                    }

                } catch (Exception e) {
                    if (listenler != null) {
                        listenler.onError(e);
                    }
                }  finally {
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
