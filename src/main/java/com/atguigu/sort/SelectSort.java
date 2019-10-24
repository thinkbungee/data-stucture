package com.atguigu.sort;

import com.atguigu.util.MyArray;
import com.atguigu.util.PrintNowTime;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * 选择排序
 */
public class SelectSort {

  int[] arr = {101, 24, 119, 1};

  //选择排序分析
  private void selectSortAnalyze(int[] arr) {
    //原始数组：101, 24, 119, 1
    //第一轮排序后：1,24,119,101
    //第二轮排序后：1,24,119,101
    //第三轮排序后：1,24,101,119

    //第一轮
    //假定arr[0]是最小值
    int minValue = arr[0];
    int minIndex = 0;
    for (int j = 0 + 1; j < arr.length; j++) {
      if (arr[j] < minValue) {
        //发现有比第一个值小的 就重置
        minValue = arr[j];
        minIndex = j;
      }
    }
    //将最小值，放在arr[0]上
    arr[minIndex] = arr[0];
    arr[0] = minValue;
    System.out.println("第一轮排序后：" + Arrays.toString(arr));

    //第二轮
    //假定arr[1]是最小值
    minValue = arr[1];
    minIndex = 1;
    for (int j = 1 + 1; j < arr.length; j++) {
      if (arr[j] < minValue) {
        //发现有比第一个值小的 就重置
        minValue = arr[j];
        minIndex = j;
      }
    }
    //将最小值，放在arr[1]上
    arr[minIndex] = arr[1];
    arr[1] = minValue;
    System.out.println("第二轮排序后：" + Arrays.toString(arr));

    //第三轮
    //假定arr[2]是最小值
    minValue = arr[2];
    minIndex = 2;
    for (int j = 2 + 1; j < arr.length; j++) {
      if (arr[j] < minValue) {
        //发现有比第一个值小的 就重置
        minValue = arr[j];
        minIndex = j;
      }
    }
    //将最小值，放在arr[2]上
    arr[minIndex] = arr[2];
    arr[2] = minValue;
    System.out.println("第二轮排序后：" + Arrays.toString(arr));


  }

  //总结分析，合并成一个循环
  //优化：当发现假定的最小值的下标没有变化时，就可以不用交换
  private void selectSort(int[] arr) {
    int minValue = 0;
    int minIndex = 0;
    for (int i = 0; i < arr.length - 1; i++) {
      //假定当前为最小值
      minValue = arr[i];
      minIndex = i;
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] < minValue) {
          //发现有比假定值小的 就重置假定值为当前值
          minValue = arr[j];
          minIndex = j;
        }
      }
      //将最小值，放在arr[i]上
      //可以做个优化:
      if (minIndex != i) {
        arr[minIndex] = arr[i];
        arr[i] = minValue;
      }
    }
  }

  @Test
  public void testSelectSortAnalyze() {
    selectSortAnalyze(arr);
  }

  @Test
  public void testSelectSort() {
    selectSort(arr);
    System.out.println("选择排序后：" + Arrays.toString(arr));
  }

  //选择排序效率
  @Test
  public void testSelectSortEfficiency() {
    int[] arr = MyArray.newAraay(800000);
    PrintNowTime.printTime();
    selectSort(arr);
    PrintNowTime.printTime();
    //8万个随机数，大概2秒左右
    //80万个随机数，3分钟

  }
}