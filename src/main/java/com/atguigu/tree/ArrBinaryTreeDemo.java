package com.atguigu.tree;

/**
 * 顺序存储二叉树
 */
public class ArrBinaryTreeDemo {


  public static void main(String[] args) {
    int arr[] = {1, 2, 3, 4, 5, 6, 7};
    ArrBinarry arrBinarry = new ArrBinarry(arr);
    arrBinarry.preOrder(0);
  }
}

class ArrBinarry {

  //存储节点的数组
  private int[] arr;

  public ArrBinarry(int[] arr) {
    this.arr = arr;
  }

  /**
   * 完成顺序存储二叉树的前序遍历
   * @param index 数组的下标
   */
  public void preOrder(int index) {
    //如果数组为空，或者length= 0
    if (arr == null || arr.length == 0) {
      System.out.println("数组为空，不能遍历");
    }
    //输出当前元素
    System.out.print(arr[index] + " ");
    //向左递归
    if ((index * 2 + 1) < arr.length) {
      preOrder(index * 2 + 1);
    }
    //向右递归
    if ((index * 2 + 2) < arr.length) {
      preOrder(index * 2 + 2);
    }
  }
}
