package com.atguigu.util;

/**
 * 数组工具
 */
public class MyArray {

  public static int[] newAraay(int number) {
    int[] arr = new int[number];
    for (int i = 0; i < number; i++) {
      //生成一个0--8000000的随机数
      arr[i] = (int) (Math.random() * 8000000);
    }
    return arr;
  }
}
