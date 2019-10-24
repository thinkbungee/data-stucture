package com.atguigu.sort;

import com.atguigu.util.MyArray;
import com.atguigu.util.PrintNowTime;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * 冒泡排序算法
 */
public class BubbleSort {

  //测试冒泡分析过程
  @Test
  public void testBubbleSortAnalyze() {
    int arr[] = {3, 9, -1, 10, 20};

    bubbleSortAnalyze(arr);
    /**
     * 结果：
     * 第一趟排序结果：[3, -1, 9, 10, 20]
     * 第二趟排序结果：[-1, 3, 9, 10, 20]
     * 第三趟排序结果：[-1, 3, 9, 10, 20]
     * 第四趟排序结果：[-1, 3, 9, 10, 20]
     * 发现在第三趟已经没有过交换了，
     * 所以可以优化算法，
     * 发现某一趟排序没有发生交换说明该序列已经有序了，
     * 就不需要再进行以后的排序了，退出循环就行了
     */

  }

  //测试冒泡
  @Test
  public void testBubbleSort() {
    int arr[] = {3, 9, -1, 10, 20};
    bubbleSort(arr);
    System.out.println(Arrays.toString(arr));
  }

  //测试冒泡排序效率
  @Test
  public void testBubbleSortEfficiency() {

    PrintNowTime.printTime();
    int[] arr = MyArray.newAraay(80000);
    bubbleSort(arr);
    PrintNowTime.printTime();

    //效率测试：8万随机数排列，大概需要10~20秒
  }

  // 找bubbleSortAnalyze每次排序的规律，再进行一个循环
  // 从小到大排列
  private static void bubbleSort(int[] arr) {
    int temp = 0;
    //标识变量，是否进行过交换
    boolean flag = false;
    for (int i = 0; i < arr.length - 1; i++) {
      for (int j = 0; j < arr.length - 1 - i; j++) {
        if (arr[j] > arr[j + 1]) {
          temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
          flag = true;
        }
      }
      if (flag) {
        //进行过交换，重置flag，进行下一轮判断
        flag = false;
      } else {
        break;
      }
    }
  }

  //冒泡排序分析
  //一共要进行 arr.length-1次排序
  private static void bubbleSortAnalyze(int[] arr) {
    int temp = 0;
    //第一趟排序，把第一大的数放在最右边
    for (int j = 0; j < arr.length - 1; j++) {
      if (arr[j] > arr[j + 1]) {
        temp = arr[j];
        arr[j] = arr[j + 1];
        arr[j + 1] = temp;
      }
    }
    System.out.println("第一趟排序结果：" + Arrays.toString(arr));

    //第二趟排序，把第二大的数放在倒数第二位
    for (int j = 0; j < arr.length - 1 - 1; j++) {
      if (arr[j] > arr[j + 1]) {
        temp = arr[j];
        arr[j] = arr[j + 1];
        arr[j + 1] = temp;
      }
    }
    System.out.println("第二趟排序结果：" + Arrays.toString(arr));

    //第三趟排序，把第二大的数放在倒数第三位
    for (int j = 0; j < arr.length - 1 - 2; j++) {
      if (arr[j] > arr[j + 1]) {
        temp = arr[j];
        arr[j] = arr[j + 1];
        arr[j + 1] = temp;
      }
    }
    System.out.println("第三趟排序结果：" + Arrays.toString(arr));

    //第四趟排序，把第二大的数放在倒数第四位
    for (int j = 0; j < arr.length - 1 - 2; j++) {
      if (arr[j] > arr[j + 1]) {
        temp = arr[j];
        arr[j] = arr[j + 1];
        arr[j + 1] = temp;
      }
    }
    System.out.println("第四趟排序结果：" + Arrays.toString(arr));
  }
}
