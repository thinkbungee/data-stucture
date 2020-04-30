package com.arithmetic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    String input;
    while ((input = bufferedReader.readLine()) != null) {
      int count = Integer.parseInt(input);
      StringBuilder result = new StringBuilder();
      for(int i = 0 ; i < count ; i ++) {
        input = bufferedReader.readLine();

        int start = 0;
        int end = 8;
        int length = input.length() / 8;

        if(input.length() % 8 > 0) {
          ++ length;
        }

        for(int j = 0 ; j < length ; j ++) {
          end = Math.min(end, input.length());
          String current = input.substring(start, end);
          result.append(current);
          if(current.length() < 8) {
            for(int k = 0 ; k < 8 - current.length() ; k ++) {
              result.append("0");
            }
          }
          result.append("\n");
          start += 8;
          end += 8;
        }
      }
      System.out.println(result.toString().trim());
    }
  }

}
