package com.arithmetic;

import java.util.ArrayList;
import java.util.List;

/**
 * 报数游戏
 * 100个人围成一圈，每个人有一个编码，编号从1开始到100.他们从1开始依次报数，
 * 报到为M的人自动退出圈圈，然后下一个人接着从1开始报数，直到剩余的人数小于M。
 * 请问最后剩余的人在原先的编号为多少？
 * 例如输入M=3时，输出为：“58，91”，输入M=4时，输出为： “34，45， 97”。
 * 如果m小于等于1， 则输出“ERROR!”;
 * 如果m大于等于100，则输出“ERROR!”；
 */
public class CountGame {

  public static void main(String[] args) {
    System.out.println(joseph(100, 3));
    System.out.println(joseph(100, 4));
  }

  /**
   *
   * @param totalNum 初始化人数
   * @param countNum 编号出列
   */
  public static List joseph(int totalNum, int countNum) {
    //初始化人数
    List<Integer> list = new ArrayList<>();
    for (int i = 1; i <= totalNum; i++) {
      list.add(i);
    }
    //从第m个开始计数
    int m = 0;
    while (list.size() >= countNum) {
      m += countNum;//计数
      m = m % list.size() - 1; //减一 是因为list下标从0开始
      if (m < 0) {
        //到了队尾
        list.remove(list.size() - 1);
        m = 0;
      } else {
        list.remove(m);
      }
    }
    return list;
  }


}

