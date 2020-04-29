package com.arithmetic;

import java.util.Arrays;
import java.util.List;

/**
 * 字符串处理
 * 连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
 * 长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
 * 输入描述:
 * 连续输入字符串(输入2次,每个字符串长度小于100)
 * 输出描述:
 * 输出到长度为8的新字符串数组
 * 示例1
 * 输入
 * abc
 * 123456789
 * 输出
 * abc00000
 * 12345678
 * 90000000
 */
public class DealString {


  public static void main(String[] args) {
    deal(Arrays.asList("12345678", "123456789"));
  }

  public static String[] deal(List<String> list) {

    for (String str : list) {
      //如果刚好长度为8，无需处理
      if (str.length() == 8) {
        System.out.println(str);
        continue;
      }
      if (str.length() < 8) {
        System.out.print(str);//输出
        //补0
        for (int i = 0; i < 8 - str.length(); i++) {
          System.out.print("0");
        }
        System.out.println();//换行
      } else {
        int n = str.length() / 8;
        int m = str.length() % 8;
        if (m == 0) {
          for (int k = 0; k < n; k++) {
            System.out.println(str.substring(8 * k, 8 * k + 8));
          }
        } else {
          for (int k = 0; k < n; k++) {
            System.out.println(str.substring(8 * k, 8 * k + 8));
          }
          //最后几位补0
          for (int y = 8 * n; y < str.length(); y++) {
            System.out.print(str.charAt(y));
          }
          for (int x = 0; x < 8 + 8 * n - str.length(); x++) {
            System.out.print("0");
          }
        }
      }
    }
    return null;
  }
}
