package com.atguigu.sort;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * 归并排序
 */
public class MergeSort {

  int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};


  @Test
  public void testMergeSort() {
    mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
    System.out.println(Arrays.toString(arr));
  }

  public void mergeSort(int[] arr, int left, int right, int[] temp) {

    if (left < right) {
      int mid = (left + right) / 2;
      //向左递归分解
      mergeSort(arr, left, mid, temp);

      //向右递归分解
      mergeSort(arr, mid + 1, right, temp);

      //合并
      mergeAll(arr, left, mid, right, temp);
    }
  }

  /**
   * 合
   *
   * @param arr 原始数组
   * @param left 左索引
   * @param mid 中间索引
   * @param right 右索引
   * @param temp 中转数组
   */
  private void mergeAll(int[] arr, int left, int mid, int right, int[] temp) {
    int i = left;//初始化i，左边有序序列的初始索引
    int j = mid + 1;//初始化j，右边有序序列的初始索引
    int t = 0;//指向temp数组的当前索引

    //1.先把左右两边（有序）的数据按照规则填充到temp数组
    //直到左右两边的有序序列，有一边处理完毕为止。
    while (i <= mid && j <= right) {
      //如果左边的有序序列的当前元素小于等于右边有序序列的当前元素
      //就将左边的当前元素填充到temp数组中
      if (arr[i] <= arr[j]) {
        temp[t] = arr[i];
        t++;
        i++;
      } else {//反之就将右边的当前元素填充到temp数组中
        temp[t] = arr[j];
        t++;
        j++;
      }
    }

    //2.把有剩余数据的一边的数据一次全部填充到temp
    while (i <= mid) {
      //左边的有序序列还有剩余的元素，就全部填充到temp
      temp[t] = arr[i];
      t++;
      i++;
    }
    while (j <= right) {
      //右边的有序序列还有剩余的元素，全部填充到temp
      temp[t] = arr[j];
      t++;
      j++;
    }

    //3.将temp数组的元素拷贝到arr
    //注意：不是每次都拷贝所有
    t = 0;
    int tempLeft = left;
    while (tempLeft <= right) {
      arr[tempLeft] = temp[t];
      t++;
      tempLeft++;
    }
  }
}
