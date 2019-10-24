package com.atguigu.sort;

import com.atguigu.util.MyArray;
import com.atguigu.util.PrintNowTime;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * 插入排序
 */
public class InsertSort {

  int[] arr = {101, 34, 119, 1};

  public void insertSortAnalyze(int[] arr) {

    /**
     * 分析过程：101,34,119,1
     * 先把数组分别看成
     * 101有序列表 和 34,119,1无序列表
     * 第一轮需要将无序列表中的34即arr[1]插入到有序列表中
     * 即：第一轮==》34,101,119,1
     * 有序列表：34,101   无序列表：119,1
     *
     * 第二轮需要将119即arr[2]插入有序列表
     * 第二轮==》34,101,119,1
     * 如此循环进行第三轮
     * 第三轮==》1,34,101,119
     *
     */
    int insertValue = 0;
    int insertIndex = 0;

    //第一轮
    //要插入的数据是 arr[1]
    insertValue = arr[1];
    //要插入的arr[1]前面的数的下标
    insertIndex = 1 - 1;
    /**
     * 找插入的位置
     * 1.insertIndex >=0 保证在给insertValue找位置，不越界
     * 2.insertValue < arr[insertIndex] 待插入的数，还没有找到插入位置
     * 3.将arr[insertIndex]后移
     */
    while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
      arr[insertIndex + 1] = arr[insertIndex];
      insertIndex--;
    }
    //当退出while时，就说明插入的位置找到:insertIndex+1
    arr[insertIndex + 1] = insertValue;
    System.out.println("第一轮：" + Arrays.toString(arr));

    //第二轮
    insertValue = arr[2];
    insertIndex = 2 - 1;
    while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
      arr[insertIndex + 1] = arr[insertIndex];
      insertIndex--;
    }
    arr[insertIndex + 1] = insertValue;
    System.out.println("第二轮：" + Arrays.toString(arr));

    //第三轮
    insertValue = arr[3];
    insertIndex = 3 - 1;
    while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
      arr[insertIndex + 1] = arr[insertIndex];
      insertIndex--;
    }
    arr[insertIndex + 1] = insertValue;
    System.out.println("第三轮：" + Arrays.toString(arr));

  }


  public void insertSort(int[] arr) {
    int insertValue = 0;
    int insertIndex = 0;
    for (int i = 1; i < arr.length; i++) {
      insertValue = arr[i];
      insertIndex = i - 1;
      while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
        arr[insertIndex + 1] = arr[insertIndex];
        insertIndex--;
      }
      arr[insertIndex + 1] = insertValue;
    }
  }

  //测试插入排序 分析过程
  @Test
  public void testInsertSortAnalyze() {
    insertSortAnalyze(arr);
  }

  //测试插入排序
  @Test
  public void testInsertSort() {
    insertSort(arr);
    System.out.println(Arrays.toString(arr));
  }

  //测试插入排序 效率
  @Test
  public void testInsertSortEfficiency() {
    int[] ints = MyArray.newAraay(800000);
    PrintNowTime.printTime();
    insertSort(ints);
    PrintNowTime.printTime();
    //8万随机数  1秒钟不到
    //80万随机数 1分钟左右

//    System.out.println(Arrays.toString(ints));
  }


}
