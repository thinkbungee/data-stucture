package com.atguigu.search;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * 斐波那契查找算法
 */
public class FibonacciSearch {

  int arr[] = {1, 8, 10, 89, 1000, 1234};


  @Test
  public void test(){
    int i = FSearch(arr, 1);
    System.out.println(i);
  }

  /**
   * 创建一个斐波那契数列
   */
  public int[] createF() {
    int[] f = new int[arr.length];
    f[0] = 1;
    f[1] = 1;
    for (int i = 2; i < arr.length; i++) {
      f[i] = f[i - 1] + f[i - 2];
    }
    return f;
  }

  /**
   * 斐波那契查找
   */
  public int FSearch(int arr[], int value) {
    int low = 0;
    int high = arr.length - 1;
    int k = 0;//表示斐波那契分割数值的下标
    int mid = 0;//存放mid值
    int[] f = createF();//获得一个斐波那契数列
    //获取到斐波那契分割数值的下标
    while (high > f[k] - 1) {
      k++;
    }
    //因为f[k]值可能大于arr的长度，因此需要构造一个新的数组，不足部分将会用0填充
    int[] temp = Arrays.copyOf(arr, f[k]);

    //将不足部分用最大值填充
    for (int i = high + 1; i < temp.length; i++) {
      temp[i] = arr[high];
    }

    //使用while来循环处理，找到我们的数 value
    while (low <= high) {
      mid = low + f[k - 1] - 1;

      if (value < temp[mid]) {
        //向左继续查找
        high = mid - 1;
        /**
         * 说明k--
         * 1.全部元素 = 前面元素 + 后边元素
         * 2. f[k] = f[k-1] + f[k-2]
         * 3.是在前面元素继续查找即下一次就是变成k-1
         */
        k--;
      } else if (value > temp[mid]) {
        //向右边继续查找
        low = mid + 1;
        /**
         * 说明 k= k-2;
         * 1.全部元素 = 前面元素 + 后边元素
         * 2. f[k] = f[k-1] + f[k-2]
         * 3.是在后边元素继续查找即下一次就是变成k-2
         */
        k -= 2;
      } else {
        //找到
        //需要确定，返回的是哪个下标
        if (mid <= high) {
          return mid;
        } else {
          return high;
        }
      }
    }
    return -1;
  }
}
