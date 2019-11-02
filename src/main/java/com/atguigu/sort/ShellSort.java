package com.atguigu.sort;

import com.atguigu.util.MyArray;
import com.atguigu.util.PrintNowTime;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * 希尔排序
 */
public class ShellSort {

  private int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

  /**
   * 逐步推导编写
   */
  private void shellSortAnalyze(int[] arr) {
    int temp = 0;
    //第一轮排序，将arr分成了 length/2 = 5 组
    for (int i = 5; i < arr.length; i++) {
      //遍历各组中所有的元素（每组有2个元素）,步长为5
      for (int j = i - 5; j >= 0; j -= 5) {
        //如果当前元素大于加上步长后的那个元素，就交换
        if (arr[j] > arr[j + 5]) {
          temp = arr[j];
          arr[j] = arr[j + 5];
          arr[j + 5] = temp;
        }
      }
    }
    System.out.println("第一轮：" + Arrays.toString(arr));

    //第二轮，将arr分成了 5/2 = 2组
    for (int i = 2; i < arr.length; i++) {
      //步长为2
      for (int j = i - 2; j >= 0; j -= 2) {
        if (arr[j] > arr[j + 2]) {
          temp = arr[j];
          arr[j] = arr[j + 2];
          arr[j + 2] = temp;
        }
      }
    }
    System.out.println("第二轮：" + Arrays.toString(arr));

    //第三轮，将arr分成了 2/2 = 1组
    for (int i = 1; i < arr.length; i++) {
      //步长为2
      for (int j = i - 1; j >= 0; j -= 1) {
        if (arr[j] > arr[j + 1]) {
          temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
      }
    }
    System.out.println("第三轮：" + Arrays.toString(arr));

  }

  //希尔排序 之交换
  private void shellSort(int[] arr) {
    int temp = 0;
    //gap是步长
    for (int gap = arr.length / 2; gap > 0; gap /= 2) {
      for (int i = gap; i < arr.length; i++) {
        for (int j = i - gap; j >= 0; j -= gap) {
          if (arr[j] > arr[j + gap]) {
            temp = arr[j];
            arr[j] = arr[j + gap];
            arr[j + gap] = temp;
          }
        }
      }
    }
    //System.out.println("希尔排序后：" + Arrays.toString(arr));
  }

  //希尔排序 之移动
  private void shellSortByInsert(int[] arr) {
    int temp = 0;
    int j = 0;
    //gap是步长
    for (int gap = arr.length / 2; gap > 0; gap /= 2) {
      for (int i = gap; i < arr.length; i++) {
        j = i;
        temp = arr[j];

        if (arr[j] < arr[j - gap]) {
          while (j - gap >= 0 && temp < arr[j - gap]) {
            //移动
            arr[j] = arr[j - gap];
            j -= gap;
          }
          //当退出while后，就是要插入的位置
          arr[j] = temp;
        }
      }
    }
    //System.out.println("希尔排序后：" + Arrays.toString(arr));
  }

  @Test
  public void testShellSortAnalyze() {
    shellSortAnalyze(arr);

  }

  @Test
  public void testShellSort() {
    shellSort(arr);
  }

  //希尔排序 交换法效率测试
  //8万 大概10秒
  //80万 大概20分钟
  @Test
  public void testShellSortEffiency() {
    int[] ints = MyArray.newAraay(80000);
    PrintNowTime.printTime();
    shellSort(ints);
//    System.out.println(Arrays.toString(ints));
    PrintNowTime.printTime();
  }

  //8万   1秒
  //80万   1秒
  //800万  2秒
  @Test
  public void testShellSortByInsertEffiency() {
    int[] ints = MyArray.newAraay(8000000);
    PrintNowTime.printTime();
    shellSortByInsert(ints);
    PrintNowTime.printTime();
    //System.out.println(Arrays.toString(ints));
  }
}
