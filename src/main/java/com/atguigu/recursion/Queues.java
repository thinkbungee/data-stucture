package com.atguigu.recursion;

import java.util.Arrays;

/**
 * 8皇后问题
 */
public class Queues {

  //一共有多少个皇后
  private int max = 8;
  //保存皇后存放位置的结果
  private int[] array = new int[max];

  private int count = 0;
  private int judgeCount = 0;

  public static void main(String[] args) {
    Queues queues = new Queues();
    queues.check(0);

    System.out.println("judge次数：" + queues.judgeCount);
    System.out.println("总共解法：" + queues.count);
  }

  /**
   * 放置皇后, n从0开始
   *
   * @param n 第几个皇后
   */
  private void check(int n) {
    if (n == max) {//n等于8时说明8个皇后都放好了
      count++;
      System.out.println(Arrays.toString(array));
      return;
    }
    //依次放入皇后，并判断是否冲突
    for (int i = 0; i < max; i++) {
      //先从第一列开始放
      array[n] = i;
      //判断是否冲突
      if (judge(n)) {//不冲突
        //接着放置下一个
        check(n + 1);
      }
      //如果冲突，就继续循环，放置下一列位置
    }
  }

  /**
   * 查看当我们放置第n个皇后，是否和前面的皇后冲突
   *
   * @param n 第n个皇后
   */
  private boolean judge(int n) {
    judgeCount++;
    //循环判断与前面的皇后是否冲突
    for (int i = 0; i < n; i++) {
      /**
       * 冲突条件：
       * 1.在同一列上:array[i] == array[n]
       * 2.在一条斜线上:Math.abs(n - i) == Math.abs(array[n] - array[i])
       * 3.在同一行上（没有必要，因为n肯定是在递增）
       */
      if (array[i] == array[n] ||
          Math.abs(n - i) == Math.abs(array[n] - array[i])) {
        return false;
      }
    }
    return true;
  }

}
