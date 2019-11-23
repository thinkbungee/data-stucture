package com.atguigu.search;

import org.junit.jupiter.api.Test;

/**
 * 顺序查找
 */
public class SequentialSearch {

  int arr[] = {1, 9, 11, -1, 34, 89};

  @Test
  public void test() {
    int index = sequentialSearch(arr, 1);
    if (index == -1) {
      System.out.println("没有找到");
    } else {
      System.out.println("找到，下标：" + index);
    }
  }

  /**
   * @param arr 数组
   * @param value 要查找的值
   * @return 如果找到返回下标，否则返回-1
   */
  public int sequentialSearch(int[] arr, int value) {

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == value) {
        return i;
      }
    }
    return -1;
  }

}
