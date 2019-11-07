package com.atguigu.sort;

import com.atguigu.util.MyArray;
import com.atguigu.util.PrintNowTime;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * 快速排序
 */
public class QuickSort {

  int[] arr = {6, 3, 5, 7, 0, 1, 2, 9, 8, 4};

  @Test
  public void testQuickSort() {
    quickSort(arr, 0, arr.length - 1);
    System.out.println(Arrays.toString(arr));
  }

  //测试快速排序效率
  //8w随机数   40多毫秒
  //80w随机数  200多毫秒
  //800w随机数  2秒
  @Test
  public void testQuickSortEffciency() {
    int[] ints = MyArray.newAraay(8000000);
    PrintNowTime.printTime();
    quickSort(ints, 0, ints.length - 1);
    PrintNowTime.printTime();
//    System.out.println(Arrays.toString(ints));
  }


  /**
   * @param left 左下标
   * @param right 右下标
   */
  public void quickSort(int arr[], int left, int right) {
    int l = left;
    int r = right;
    //中轴值
    int pivot = arr[(left + right) / 2];
    int temp = 0;

    //比中轴值大就放在右边
    while (l < r) {
      //在pivot的左边一直找，找到大于等于pivot值，才退出
      while (arr[l] < pivot) {
        l++;
      }
      //在pivot的右边一直找，找到小于pivot值，才退出
      while (arr[r] > pivot) {
        r--;
      }

      //如果l >= r说明pivot左右两边的值，
      // 已经按照左边小于等于pivot，
      // 右边大于等于pivot
      if (l >= r) {
        break;
      }

      //将2者值交换
      temp = arr[r];
      arr[r] = arr[l];
      arr[l] = temp;

      //因为很有可能找到了是自己中轴值，所以需要将r--或者l++

      //如果交换完后，发现arr[l] == pivot值 r--
      if (arr[l] == pivot) {
        r--;
      }
      //如果交换完后，发现arr[r] == pivot值 l++
      if (arr[r] == pivot) {
        l++;
      }
    }

    //如果l==r，必须l++,r--，否则就会栈溢出
    if (l == r) {
      l++;
      r--;
    }

    //向右递归
    if (right > l) {
      quickSort(arr, l, right);
    }
    //向左递归
    if (left < r) {
      quickSort(arr, left, r);
    }
  }
}
