package com.atguigu.tree;

import com.atguigu.util.MyArray;
import com.atguigu.util.PrintNowTime;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * 堆排序
 */
public class HeapSort {

  static int arr[] = {4, 1, 2, 3, 6, 8, 5, 9, 7, 1, 3, 2};

  public static void main(String[] args) {
    int arr1[] = {4, 5, 8, 1, 9};

    heapSort(arr);
    System.out.println(Arrays.toString(arr));
  }

  /**
   * 8w 时间 10ms左右
   * 80w 时间 150ms左右
   * 800w 时间 2s左右
   */
  @Test
  public void testEfficiency() {
    int[] ints = MyArray.newAraay(800000);
    PrintNowTime.printTime();
    heapSort(ints);
    PrintNowTime.printTime();
//    System.out.println(Arrays.toString(ints));
  }

  public static void heapSort(int arr[]) {
    int temp = 0;
    System.out.println("堆排序");

    //第一步，构造大顶堆
    for (int i = arr.length / 2 - 1; i >= 0; i--) {
      adjustHeap(arr, i, arr.length);
    }
    //堆顶元素和末尾元素交换，再固定最后一位，再构造大顶堆，反复循环
    for (int j = arr.length - 1; j > 0; j--) {
      //交换
      temp = arr[j];
      arr[j] = arr[0];
      arr[0] = temp;
      adjustHeap(arr, 0, j);
    }

  }

  /**
   * 讲一个数组（二叉树）调整成大顶堆
   * @param arr 数组
   * @param i 表示非叶子节点在数组中的索引
   * @param length 表示对多少个元素继续调整，length是在逐渐减少
   */
  public static void adjustHeap(int arr[], int i, int length) {
    //保存当前i的值
    int temp = arr[i];
    // k = i*2+1 为i的左子节点
    //循环调整当前节点极其子节点
    for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
      //确定左右节点，谁的值更大
      if (k + 1 < length && arr[k] < arr[k + 1]) {
        //左子节点 小于 右子节点 的情况，则将k指向右子节点
        k++;
      }
      if (arr[k] > temp) {
        //子节点 大于 父节点 ，将较大值赋给当前节点
        arr[i] = arr[k];
        i = k;//将i指向k，继续循环
      } else {
        break;
      }
    }
    arr[i] = temp;//将temp放到调整后的位置

    //当for循环结束后，以i为父节点的树，i就是最大值
  }
}
