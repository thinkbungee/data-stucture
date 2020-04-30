package com.arithmetic;


import java.util.Scanner;

/**
 * 假设有九宫格输入法键盘布局如下：
 * [1,.]  [2abc]  [3def]
 * [4ghi]   [5jkl]  [6mno]
 * [7pqrs]  [8tuv]  [9wxyz]
 *          [0 ]
 *
 *  输入一串字符 0~9 # /
 *  默认先是数字输入法，#代表切换英文  / 代表延迟输入
 *
 *  如输入： 123#222235/56
 *  输出：123adjjm
 */
public class CellPhoneInputPlus {

  public static void main(String[] args) {
    String[] str = {" ", ",.", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      StringBuilder sb = new StringBuilder();
      String string = scanner.nextLine();
      int length = string.length();
      int start = 0;
      int cur = 0;
      boolean isNum = true;//默认第一次都是数字
      while (cur < length) {
        int count = 1;
        char c = string.charAt(cur);
        if (c == '#') {
          //切换输入法
          isNum = !isNum;
          cur++;
          continue;
        }
        if (isNum) {
          //就是数字
          sb.append(c);
          cur++;
        } else {
          //英文输入，判断下一个是否一样数字
          if (cur + 1 < length) {
            char c1 = string.charAt(cur + 1);
            while (c1 == c) {
              cur++;
              count++;
              if (cur + 1 >= length) {
                break;
              }
              c1 = string.charAt(cur + 1);
            }
          }
          if (c == '/') {
            cur++;
            continue;
          }
          int num = Integer.parseInt(String.valueOf(c));
          sb.append(str[num].charAt(Math.max(count % str[num].length() - 1, 0)));//0数字按键需要单独处理，长度为1,1%1-1 = -1
          cur++;
        }
      }
      System.out.println(sb.toString());
    }
  }
}