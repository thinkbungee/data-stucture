package com.atguigu.search;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * 二分查找
 */
public class BinarySearch {

  int arr[] = {1, 8, 10, 89, 1000, 1234};

  @Test
  public void testBinarySearch() {
    int i = binarySearch(arr, 0, arr.length, 1000);
    System.out.println(i);
  }

  @Test
  public void testBinarySearchPlus() {
    int arr[] = {1, 8, 10, 89,1000, 1000, 1000, 1234};
    List list = binarySearchPlus(arr, 0, arr.length, 1000);
    System.out.println(list);
  }

  //思考，如果数组中有多个待查找的值{1,8,10,89,1000,1000,1234}
  public List binarySearchPlus(int arr[], int left, int right, int value) {

    if (left > right) {
      return new ArrayList();
    }
    int mid = (left + right) / 2;
    int midValue = arr[mid];
    if (midValue > value) {
      return binarySearchPlus(arr, left, mid - 1, value);
    } else if (midValue < value) {
      return binarySearchPlus(arr, mid + 1, right, value);
    } else {
      List list = new ArrayList();
      list.add(mid);//先加入当前坐标
      //向左边扫描
      int temp = mid - 1;
      while (true) {
        if (temp < 0 || arr[temp] != value) {
          //退出条件
          break;
        }
        list.add(temp--);
      }

      //向右边扫描
      temp = mid + 1;
      while (true) {
        if (temp > arr.length - 1 || arr[temp] != value) {
          break;
        }
        list.add(temp++);
      }
      return list;
    }

  }


  /**
   * @param arr 数组
   * @param left 左边索引
   * @param right 右边索引
   * @param value 查找的值
   */
  public int binarySearch(int[] arr, int left, int right, int value) {

    //说明没有该value值
    if (left > right) {
      return -1;
    }
    int mid = (left + right) / 2;
    int midValue = arr[mid];

    if (midValue > value) {
      //向左递归
      return binarySearch(arr, left, mid - 1, value);
    } else if (midValue < value) {
      //向右递归
      return binarySearch(arr, mid + 1, right, value);
    } else {
      return mid;
    }

  }
}
