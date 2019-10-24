package com.atguigu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 打印出当前时间，精确到毫秒
 */
public class PrintNowTime {

  private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
      "yyyy-MM-dd hh:mm:ss.SSS");

  public static void printTime() {
    Date date = new Date();
    System.out.println(simpleDateFormat.format(date));
  }
}
