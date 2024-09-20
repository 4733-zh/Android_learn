package com.example.bluetoothtest;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.UUID;

public class ConnectThread extends Thread {

    private static final String TAG = "ConnectThread";
    private final BluetoothAdapter mBluetoothAdapter;
    private BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;

    public ConnectThread(BluetoothAdapter bluetoothAdapter, BluetoothDevice bluetoothDevice, String uuid) {
        this.mmDevice = bluetoothDevice;
        this.mBluetoothAdapter = bluetoothAdapter;

        BluetoothSocket tmp = null;

        if (mmSocket != null) {
            Log.e(TAG, "ConnectThread-->mmSocket!=null先去释放");
            try {
                mmSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, "ConnectThread-->mmSocket!=null已经释放");
        try {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, 1);
            }
            tmp = mmDevice.createRfcommSocketToServiceRecord(UUID.fromString(uuid));
        } catch (IOException e) {
            Log.e(TAG, "ConnectThread-->获取BluetoothSocket异常！" + e.getMessage());
        }
        mmSocket = tmp;
        if (mmSocket != null) {
            Log.w(TAG, "ConnectThread-->已获取BluetoothSocket");
        }
    }

    @Override
    public void run() {
        if (mBluetoothAdapter == null) {
            Log.e(TAG, "ConnectThread:run-->mBluetoothAdapter  == null");
            return;
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);
        }else if (mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
        }

        if (mmSocket == null) {
            Log.e(TAG, "ConnectThread:run-->mmSocket==null");
            return;
        }
        try {
            Log.d(TAG, "ConnectThread:run-->去连接...");
            if (onBluetoothConnectListener != null) {
                onBluetoothConnectListener.onStartConn();
            }
            mmSocket.connect();
            if (onBluetoothConnectListener != null) {
                onBluetoothConnectListener.onConnSuccess(mmSocket);  //连接成功回调
                Log.w(TAG, "ConnectThread:run-->连接成功");
            }

        } catch(IOException e)

        {
            Log.e(TAG, "ConnectThread:run-->连接异常！" + e.getMessage());

            if (onBluetoothConnectListener != null) {
                onBluetoothConnectListener.onConnFailure("连接异常：" + e.getMessage());
            }

            //释放
            cancel();
        }
    }


    public void cancel() {
        try {
            if (mmSocket != null && mmSocket.isConnected()) {
                Log.d(TAG,"ConnectThread:cancel-->mmSocket.isConnected() = " + mmSocket.isConnected());
                mmSocket.close();
                mmSocket = null;
                return;
            }

            if (mmSocket != null) {
                mmSocket.close();
                mmSocket = null;
            }

            Log.d(TAG,"ConnectThread:cancel-->关闭已连接的套接字释放资源");

        } catch (IOException e) {
            Log.e(TAG,"ConnectThread:cancel-->关闭已连接的套接字释放资源异常!" + e.getMessage());
        }
    }

    private OnBluetoothConnectListener onBluetoothConnectListener;

    public void setOnBluetoothConnectListener(OnBluetoothConnectListener onBluetoothConnectListener) {
        this.onBluetoothConnectListener = onBluetoothConnectListener;
    }

    //连接状态监听者
    public interface OnBluetoothConnectListener{
        void onStartConn();  //开始连接
        void onConnSuccess(BluetoothSocket bluetoothSocket);  //连接成功
        void onConnFailure(String errorMsg);  //连接失败
    }

}
