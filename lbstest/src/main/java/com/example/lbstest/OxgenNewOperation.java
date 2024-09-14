//package com.example.lbstest;
//
//import android.text.TextUtils;
//import android.util.Log;
//
//import com.example.baseinstallation.utils.DateUtil;
//import com.example.baseinstallation.utils.FileUtil;
//import com.example.baseinstallation.utils.HexUtil;
//import com.hoho.android.usb.dao.OutputStreams;
//import com.hoho.android.usb.dao.UsbAction;
//import com.hoho.android.usb.dao.UsbConfigBuilder;
//import com.hoho.android.usb.dao.UsbManager;
//import com.hoho.android.usb.driver.UsbSerialPort;
//import com.hoho.android.usb.util.HexDump;
//import com.hoho.android.usb.util.UsbLogUtils;
//import com.sun.jna.platform.mac.SystemB;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Vector;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import static com.hoho.android.usb.driver.UsbSerialPort.PARITY_NONE;
//import static com.hoho.android.usb.driver.UsbSerialPort.PARITY_ODD;
//
///**
// * @项目名称 SimpleOneMachine
// * @类名 name：com.hoho.android.usbserial
// * @类描述 血氧
// * @创建人 hsl20
// * @创建时间 2020-06-09 11:49
// * @修改人
// * @修改时间 time
// * @修改备注 describe
// */
//public class OxgenNewOperation implements UsbAction, OutputStreams {
//  private UsbConfigBuilder usbConfigBuilder;
//  private static final OxgenNewOperation ourInstance = new OxgenNewOperation();
//  private StringBuffer stringBuffer = new StringBuffer();
//  private OxgenWorkflow oxgenWorkflow;
//  public static Vector<Byte> originalData = new Vector();
//  private String date = "";
//
//  public OxgenWorkflow getOxgenWorkflow() {
//    return oxgenWorkflow;
//  }
//
//  public OxgenNewOperation setOxgenWorkflow(OxgenWorkflow oxgenWorkflow) {
//    this.oxgenWorkflow = oxgenWorkflow;
//    return ourInstance;
//  }
//
//  public static OxgenNewOperation getInstance() {
//    return ourInstance;
//  }
//
//  public OxgenNewOperation setNumber_path(String path) {
//    usbConfigBuilder.setNumber_path(path);
//    return this;
//  }
//
//  private OxgenNewOperation() {
//    usbConfigBuilder = new UsbConfigBuilder()
//        .setBaudRate(38400)
//        .setDataBits(8)
//        .setStopBits(UsbSerialPort.STOPBITS_1)
//        .setParity(PARITY_NONE)
//        .build();
//  }
//
//  private FileOutputStream fos = null;
//  private ExecutorService mExecutorService;
//
//  @Override
//  public void Open() {
////    try {
////      if (FileUtil.isSDCardExist()) {
////        File dir = new File(FileUtil.getSaveDir());
////        if (!dir.exists()) {
////          dir.mkdirs();
////        }
////        fos = new FileOutputStream(FileUtil.getSaveDir()
////                + "/oxgenlog.txt", true);
////
////      }
////    } catch (Exception e) {
////      e.printStackTrace();
////    }
////    mExecutorService = Executors.newSingleThreadExecutor();
//
//    originalData.clear();
//    stringBuffer.delete(0, stringBuffer.length());
//    UsbManager.getInstance()
//        .setUsbConfigBuilder(usbConfigBuilder)
//        .setOutputStreams(this)
//        .read(usbConfigBuilder.getNumber_path())
//        .OpenSerial();
//  }
//
//  @Override
//  public void Err() {
//    getOxgenWorkflow().Err();
//  }
//
//  @Override
//  public void Succes() {
//    getOxgenWorkflow().Succes();
//  }
//
//  @Override
//  public void Close() {
////    try {
////      if (null != fos) {
////        fos.close();
////      }
////    } catch (IOException e) {
////      e.printStackTrace();
////    }
////    mExecutorService = null;
//
//    originalData.clear();
//    stringBuffer.delete(0, stringBuffer.length());
//    UsbManager.getInstance().Stop();
//  }
//
//  int size = 0;
//  boolean isHead = false;
//  List<String> checklist = new ArrayList<>();
//
//  @Override
//  public void Receive(byte[] data) {
//    String receiverData = HexUtil.encodeHexStr(data);
//    UsbLogUtils.e("血氧数据接收 接收端收到", receiverData);
//    UsbLogUtils.e("血氧数据接收 拼接数据",date);
//
////    FileUtil.writeLog(HexUtil.encodeHexStr(data),"oxgenlog.txt");
//    if (HexUtil.encodeHexStr(data).contains("aa")) {
//      if (date.length() >= 2) {
//        UsbLogUtils.e("血氧数据接收 包含aa 并且数据长度大于2这里拼接数据",date);
//        Log.d("test", "Receive: data" + date);
//        if (HexUtil.hexStringToBytes(date)[1] == 0xFF) {
//          UsbManager.getInstance().SendCmd(HexUtil.hexStringToBytes("AA55510201C8"));
//        } else if (HexUtil.hexStringToBytes(date)[1] == 0x51) {
//          UsbManager.getInstance().SendCmd(HexUtil.hexStringToBytes("AA555003020127"));
//        } else if (HexUtil.hexStringToBytes(date)[1] == 0x50) {
//        } else if (HexUtil.hexStringToBytes(date)[1] == 0x35) {
//          UsbManager.getInstance().SendCmd(HexUtil.hexStringToBytes("AA55FF0201CA"));
//        } else {
//          byte[] commands = HexUtil.hexStringToBytes(date);
//          Log.d("test", " commands Receive: data" + date);
//          //AA555103020834
//          //AA55530701626B0094003D
//          //AA55520301002B
//          if (date.contains("5553")) {
//            if (commands.length > 9) {
//
//              int index = date.indexOf("55");
//              Log.d("test", "ReceiveKeruikang spo2: " + String.valueOf((commands[4]) & 0xff));
//              Log.d("test", "ReceiveKeruikang PR 1: " + String.valueOf((commands[6] << 4) & 0xff00));
//
//              Log.d("test", "ReceiveKeruikang PR 2 : " + String.valueOf(commands[5] & 0xff));
//              Log.d("test", "ReceiveKeruikang PR 3 : " + ((commands[6] << 4) & 0xff00 + commands[5] & 0xff));
//              int pr1 = (commands[6] << 4) & 0xff00;
//              int pr2 = commands[5] & 0xff;
//
//              Log.d("test", "ReceiveKeruikang PI: " + commands[7]);
//              Log.d("test", "ReceiveKeruikang status: " + commands[8]);
//
//              int spo2 = (commands[4]) & 0xff;
//              int PR = pr1 + pr2;
//              int PI = commands[7] & 0xff;
//              int status = commands[8] & 0xff;
//              Log.d("", "ReceiveKeruikang pr: " + PR);
//              getOxgenWorkflow().Result(String.valueOf(status), spo2, PR);
//            }
//          } else if (date.contains("5552")) {
//            if (date.length() > 11) {
//              int index = HexUtil.encodeHexStr(commands, false).indexOf("5552");
//              int status = commands[4] & 0xff;
//              getOxgenWorkflow().addResult(status);
//            }
//
//          }
//
//        }
//
//        date = "";
//        UsbLogUtils.e("血氧数据接收 ","清空数据");
//      }
////      else {
////        String temp = HexUtil.encodeHexStr(data).substring(HexUtil.encodeHexStr(data).indexOf("aa"));
////        Log.d("test", "Receive substring: " + temp);
////        date = date + HexUtil.encodeHexStr(data);
////
////      }
//    } else {
//      date = date + HexUtil.encodeHexStr(data);
//      UsbLogUtils.e("血氧数据接收 不包含aa 就拼接 ", date);
//    }
//  }
//}
