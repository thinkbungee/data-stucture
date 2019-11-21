package com.atguigu.sort;

import com.atguigu.util.MyArray;
import com.atguigu.util.PrintNowTime;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * 基数排序（桶排序）
 */
public class RadixSort {

  private int[] arr = {53, 3, 542, 748, 14, 214};


  @Test
  public void testRadixSortAnalyze() {
    radixSortAnalyze(arr);
  }

  @Test
  public void testRadixSort() {
    radixSort(arr);
    System.out.println(Arrays.toString(arr));
  }

  //测试基数排序效率
  //8w  50多毫秒
  //80w 400多毫秒
  //800w  600多毫秒
  //8000w 堆溢出 空间换时间
  @Test
  public void testRadixSortEffiency() {
    int[] ints = MyArray.newAraay(80000000);
    PrintNowTime.printTime();

    radixSort(ints);

    PrintNowTime.printTime();

    //System.out.println(Arrays.toString(ints));
  }

  public void radixSortAnalyze(int arr[]) {

    //1.首先得到数组中最大的数
    int max = arr[0];
    for (int m : arr) {
      if (m > max) {
        max = m;
      }
    }
    //得到最大的是几位数
    int maxLength = (max + "").length();

    //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
    /**
     * 1.二维数组包含10个一维数组
     * 2.为了防止在放入数的时候，数据溢出，长度为arr.length
     * 3.基数排序：空间换时间的经典算法
     */
    int[][] bucket = new int[10][arr.length];

    //每个桶的指针，也用来记录每个桶的个数
    int[] bucketCounts = new int[10];
    //记录元素个位上的值
    int oneOfElement = 0;
    //记录元素十位上的值
    int tenOfElement = 0;
    //记录百位
    int hundredsOfElement = 0;

    //==============第一轮（对每个元素的个位进行处理）=================
    for (int j = 0; j < arr.length; j++) {
      //取出每个元素的个位的值
      oneOfElement = arr[j] % 10;
      //放入对应的桶中:bucketCounts[oneOfElement]记录个数,运算完后需要自增
      bucket[oneOfElement][bucketCounts[oneOfElement]++] = arr[j];
    }

    int index = 0;
    //遍历每个桶，放入到原数组
    for (int k = 0; k < bucketCounts.length; k++) {
      //如果桶中有数据，就放入到原数组
      if (bucketCounts[k] != 0) {
        for (int m = 0; m < bucketCounts[k]; m++) {
          arr[index++] = bucket[k][m];
        }
      }
      //再将每个桶中的个数置空
      bucketCounts[k] = 0;
    }

    System.out.println("第一轮，对个位的排序处理arr:" + Arrays.toString(arr));
    //第一轮，对个位的排序处理arr:[542, 53, 3, 14, 214, 748]

    //==============第二轮（对每个元素的十位进行处理）=================

    //放数据
    for (int j = 0; j < arr.length; j++) {
      tenOfElement = arr[j] / 10 % 10;
      bucket[tenOfElement][bucketCounts[tenOfElement]++] = arr[j];
    }

    index = 0;
    //取数据
    for (int m = 0; m < bucketCounts.length; m++) {
      //桶中有数据
      if (bucketCounts[m] != 0) {
        for (int k = 0; k < bucketCounts[m]; k++) {
          arr[index++] = bucket[m][k];
        }
      }
      //置空桶中的数据
      bucketCounts[m] = 0;
    }
    System.out.println("第二轮，对十位的排序处理arr:" + Arrays.toString(arr));
//    第二轮，对十位的排序处理arr:[3, 14, 214, 542, 748, 53]

    //==============第三轮（对每个元素的百位进行处理）=================
    //放数据
    for (int j = 0; j < arr.length; j++) {
      hundredsOfElement = arr[j] / 100 % 10;
      bucket[hundredsOfElement][bucketCounts[hundredsOfElement]++] = arr[j];
    }
    index = 0;
    //取数据
    for (int m = 0; m < bucketCounts.length; m++) {
      //有数据才取
      if (bucketCounts[m] != 0) {
        for (int k = 0; k < bucketCounts[m]; k++) {
          arr[index++] = bucket[m][k];
        }
      }
      //置空每个桶数据
      bucketCounts[m] = 0;
    }
    System.out.println("第三轮，对百位的排序处理arr:" + Arrays.toString(arr));
//    第三轮，对百位的排序处理arr:[3, 14, 53, 214, 542, 748]
  }

  //根据上述分析过程，写基数排序
  public void radixSort(int arr[]) {
    ///确定arr中的最大值
    int max = arr[0];
    for (int a : arr) {
      if (a > max) {
        max = a;
      }
    }
    //确定最大值的位数
    int length = (max + "").length();

    //10个桶
    int bucket[][] = new int[10][arr.length];
    //每个桶的值的数量
    int bucketCounts[] = new int[10];
    //每个数具体位置的值
    int numberOfElement = 0;
    //取数据时 下标
    int index = 0;
    for (int i = 1; i <= length; i++) {

      //放数据
      for (int j = 0; j < arr.length; j++) {
        numberOfElement = arr[j] / (int) Math.pow(10, i - 1) % 10;//得到具体位置上的值
        bucket[numberOfElement][bucketCounts[numberOfElement]++] = arr[j];
      }
      //取数据
      for (int m = 0; m < bucketCounts.length; m++) {
        //桶中有数据才取
        if (bucketCounts[m] != 0) {
          for (int k = 0; k < bucketCounts[m]; k++) {
            arr[index++] = bucket[m][k];
          }
        }
        //置空每个桶
        bucketCounts[m] = 0;
      }
      //置空index
      index = 0;
    }
  }


}



