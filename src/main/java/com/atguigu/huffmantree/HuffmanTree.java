package com.atguigu.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Data;
import lombok.ToString;

/**
 * 创建赫夫曼树
 */
public class HuffmanTree {

  static int arr[] = {13, 7, 8, 3, 29, 6, 1};

  public static void main(String[] args) {
    Node huffmanTree = createHuffmanTree(arr);
    huffmanTree.preOrder();
  }


  /**
   * 创建赫夫曼树
   * @param arr 数组
   * @return 返回一颗赫夫曼树
   */
  public static Node createHuffmanTree(int arr[]) {
    //将数组中的值构建成一个list，方便排序
    List<Node> nodes = new ArrayList<>();
    for (int a : arr) {
      nodes.add(new Node(a));
    }

    //构建赫夫曼树
    while (nodes.size() > 1) {//list中只剩下一个元素的时候，该元素就是一颗赫夫曼树
      //排序
      Collections.sort(nodes);
      //取出排序后的第一、第二个元素组成一颗新的二叉树
      Node node1 = nodes.get(0);
      Node node2 = nodes.get(1);
      Node node3 = new Node(node1.getValue() + node2.getValue());
      node3.setLeft(node1);
      node3.setRight(node2);
      //删除原来的最小的和次小的节点，并加入新的节点
      nodes.remove(node1);
      nodes.remove(node2);
      nodes.add(node3);
    }

    return nodes.get(0);
  }


}

/**
 * 节点
 */
@Data
@ToString(exclude = {"left", "right"})
class Node implements Comparable<Node> {

  //节点的权值
  private int value;
  //左节点
  private Node left;
  //右节点
  private Node right;

  public Node(int value) {
    this.value = value;
  }

  //前序遍历
  public void preOrder() {
    System.out.println(this);
    if (this.left != null) {
      this.left.preOrder();
    }
    if (this.right != null) {
      this.right.preOrder();
    }
  }

  @Override
  public int compareTo(Node node) {
    //表示从小到大排序
    return this.value - node.value;
  }
}