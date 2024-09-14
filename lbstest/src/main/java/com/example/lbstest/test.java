package com.example.lbstest;

public class test {
    private String sendData;//发送的数据
    private String receiveData;//接受的数据
    private String concatData =null;//拼接的数据


//    aa5553 12     8位数
//    aa5552 12345  11位数
//
//    aa555212345aa555312aa555312aa555312

    private String Receive(){

         String concatData =null;

        if (receiveData.contains("aa")){
            String[] aas = receiveData.split("aa");

            if (concatData.contains("aa5552")&&concatData.length()==11||concatData.contains("aa5553")&& concatData.length()==8){
                return concatData;
            } else {
                if (receiveData.length()==8||receiveData.length()==11){
                    return concatData = receiveData;
                }
                if (receiveData.length()<8){
                    concatData = receiveData;
                }
            }

        }
        return "0";
    }


}
