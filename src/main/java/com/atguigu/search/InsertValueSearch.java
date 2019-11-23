package com.atguigu.search;

import org.junit.jupiter.api.Test;

/**
 * 插值查找算法
 */
public class InsertValueSearch {

  int arr[] = {1, 8, 10, 89, 1000, 1234};


  @Test
  public void testInsertValueSearch() {
    int i = insertValueSearch(arr, 0, arr.length - 1, 1234);
    System.out.println(i);
  }

  /**
   * 插值算法：arr也需要有序
   *
   * @return 如果找到，返回对应下标，否则返回-1
   */
  public int insertValueSearch(int arr[], int left, int right, int value) {

    //比前面多了2个限制条件
    //需要限制，否则可能得到的mid越界
    if (left > right || value < arr[0] || value > arr[arr.length - 1]) {
      return -1;
    }
    //根据公式得到mid
    int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
    int midValue = arr[mid];

    if (midValue > value) {
      //向左
      return insertValueSearch(arr, left, mid - 1, value);
    } else if (midValue < value) {
      //向右
      return insertValueSearch(arr, mid + 1, right, value);
    } else {
      return mid;
    }
  }
}
