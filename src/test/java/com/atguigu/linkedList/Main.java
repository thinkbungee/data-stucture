package com.atguigu.linkedList;

import java.util.Scanner;

public class Main {

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int[] arr = new int[101];
    while (in.hasNext()) {
      int n = in.nextInt();
      for (int i = 0; i < n; i++) {
        arr[i] = in.nextInt();
      }
      //quickSort(arr, n);
      radixSort(arr, n);
      for (int i = 0; i < n; i++) {
        System.out.print(arr[i] + " ");
      }
      System.out.println();
      break;
    }
  }

  /**
   * 冒泡排序
   *
   * @param arrLength 数组真实数据个数
   */
  public static void quickSort(int arr[], int arrLength) {
    int temp = 0;
    for (int i = 0; i < arrLength - 1; i++) {
      for (int j = 0; j < arrLength - 1 - i; j++) {
        if (arr[j] > arr[j + 1]) {
          temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
      }
    }
  }


  /**
   * 基数排序（桶排序）
   *
   * @param arrLength 数组真实数据个数
   */
  public static void radixSort(int arr[], int arrLength) {
    int bucket[][] = new int[10][arrLength];
    int bucketCounts[] = new int[10];

    //得到数组的最大值
    int max = arr[0];
    for (int a : arr) {
      if (a > max) {
        max = a;
      }
    }
    //得到最高位个数
    int length = (max + "").length();
    int number = 0;
    int index = 0;
    for (int i = 0, n = 1; i < length; i++, n *= 10) {
      //放数据
      for (int j = 0; j < arrLength; j++) {
        number = arr[j] / n % 10;
        bucket[number][bucketCounts[number]++] = arr[j];
      }
      //取数据
      for (int m = 0; m < 10; m++) {
        //有数据才取
        for (int k = 0; k < bucketCounts[m]; k++) {
          if (bucketCounts[m] != 0) {
            arr[index++] = bucket[m][k];
          }
        }
        //置空counts
        bucketCounts[m] = 0;
      }
      index = 0;
    }
  }

  /**
   * 插入排序
   *
   * @param arrLength 数组真实数据个数
   */
  public static void insertSort(int arr[], int arrLength) {
    int insertValue = 0;
    int insertIndex = 0;
    //从数组第二个元素开始找插入位置
    for (int i = 1; i < arrLength; i++) {
      insertValue = arr[i];
      insertIndex = i - 1;
      while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
        //将值后移
        arr[insertIndex + 1] = arr[insertIndex];
        insertIndex--;
      }
      //退出循环时，就找到了插入的位置
      arr[insertIndex + 1] = insertValue;
    }
  }
}