package com.atguigu.binarysorttree;

import lombok.Data;
import lombok.ToString;
import org.junit.jupiter.api.Test;

/**
 * 二叉排序树
 * 设定：
 * 左子节点小于父节点
 * 右子节点大于等于父节点
 */
public class BinarySortTreeDemo {

  //测试Node添加和遍历方法
  @Test
  public void test1() {
    BinarySortTree binarySortTree = new BinarySortTree();
    int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
    //循环添加
    for (int i : arr) {
      binarySortTree.add(new Node(i));
    }
    binarySortTree.infixOrder();
  }

  //测试node删除方法
  @Test
  public void test2() {
    BinarySortTree binarySortTree = new BinarySortTree();
    int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
    //循环添加
    for (int i : arr) {
      binarySortTree.add(new Node(i));
    }

    binarySortTree.deleteNode(12);
    binarySortTree.deleteNode(5);
    binarySortTree.deleteNode(10);
    binarySortTree.deleteNode(2);
    binarySortTree.deleteNode(3);
    binarySortTree.deleteNode(9);
    binarySortTree.deleteNode(1);
    binarySortTree.deleteNode(7);

    binarySortTree.infixOrder();
  }

}

class BinarySortTree {

  //根节点
  private Node root;


  /**
   * 删除节点
   * 分三种情况
   * @param value 要删除节点的值
   */
  public void deleteNode(int value) {
    if (root == null) {
      return;
    } else {
      //1.先找到要删除的节点 targetNode
      Node targetNode = search(value);
      //如果没有该节点就直接结束
      if (targetNode == null) {
        return;
      }
      //如果这颗二叉树只有root节点
      if (root.left == null && root.right == null) {
        root = null;
        return;
      }

      //找targetNode的父节点parentNode
      Node parentNode = searchParent(value);

      //删除的节点是叶子节点
      if (targetNode.left == null && targetNode.right == null) {

        //看是parentNode的哪边节点
        if (parentNode.left != null && parentNode.left.value == value) {
          //是parentNode的左子节点
          parentNode.left = null;
        } else if (parentNode.right != null && parentNode.right.value == value) {
          //是parentNode的右子节点
          parentNode.right = null;
        }
      } else if (targetNode.left != null && targetNode.right != null) {//删除的是有2颗子树的节点
        //有2颗子树，找到右子树最小的节点,记录下最小的值，并删除掉该最小的节点
        int minValue = returnAndDeleteRightTreeMinNode(targetNode.right);
        //把该最小的值 赋给 targetNode
        targetNode.value = minValue;
      } else {//删除的是有1颗子树的节点
        if (targetNode.left != null) {//targetNode 只有左子节点
          if (parentNode != null) {//这个情况 需要考虑
            if (parentNode.left.value == value) {//如果要删除的节点是parentNode的
              parentNode.left = targetNode.left;
            } else {
              parentNode.right = targetNode.left;
            }
          } else {
            root = targetNode.left;
          }
        } else {//targetNode 只有右子节点
          if (parentNode != null) {
            if (parentNode.left.value == value) { //targetNode是parentNode的左子节点
              parentNode.left = targetNode.right;
            } else {//targetNode是parentNode的右子节点
              parentNode.right = targetNode.right;
            }
          } else {
            root = targetNode.right;
          }
        }
      }
    }
  }

  /**
   * 返回以 right为根节点的二叉排序树的最小值，并且删除该最小的节点
   * @param right 传入的二叉排序树
   * @return 返回二叉排序树最小的值
   */
  private int returnAndDeleteRightTreeMinNode(Node right) {
    Node target = right;
    //找最小的值
    while (target.left != null) {
      target = target.left;
    }
    //循环结束，target就指向了最小的节点
    deleteNode(target.value);
    return target.value;
  }

  //查找
  Node search(int value) {
    if (root == null) {
      return null;
    } else {
      return root.search(value);
    }
  }

  //查找父节点
  Node searchParent(int value) {
    if (root == null) {
      return null;
    } else {
      return root.searchParent(value);
    }
  }

  //添加
  void add(Node node) {
    if (root == null) {
      root = node;
    } else {
      root.add(node);
    }
  }

  //中序遍历
  void infixOrder() {
    if (root == null) {
      System.out.println("二叉排序树为空");
    } else {
      root.infixOrder();
    }
  }

}

@Data
@ToString(exclude = {"left", "right"})
class Node {

  int value;
  Node left;
  Node right;

  public Node(int value) {
    this.value = value;
  }

  /**
   * 查找值为value的节点
   * @param value 值
   * @return 如果找到返回该节点，否则返回null
   */
  public Node search(int value) {
    if (this.value == value) {
      return this;
    } else if (this.value > value) {
      //判断是否为空
      if (this.left == null) {
        return null;
      }
      //查找的值 大于当前节点，就向左子树递归查找
      return this.left.search(value);
    } else {
      //判断是否为空
      if (this.right == null) {
        return null;
      }
      //查找的值 小于当前节点，就向右子树递归查找
      return this.right.search(value);
    }
  }

  /**
   * 查找要删除节点的父节点
   * @param value 值
   * @return 返回找到的节点的父节点，如果没有 就返回null
   */
  public Node searchParent(int value) {
    //如果当前节点就是要删除节点的父节点，直接返回
    if ((this.left != null && this.left.value == value)
        || (this.right != null && this.right.value == value)) {
      return this;
    } else {
      if (value < this.value && this.left != null) {
        //如果查找的值 小于当前节点，就往左子树递归查找
        return this.left.searchParent(value);
      } else if (value >= this.value && this.right != null) {
        //如果查找的值 大于等于当前节点，就往右子树递归查找
        return this.right.searchParent(value);
      } else {
        //没有找到父节点
        return null;
      }
    }
  }

  /**
   * 添加节点
   * 递归的形式添加节点
   * @param node
   */
  public void add(Node node) {
    if (node == null) {
      return;
    }
    //判断传入的节点的值和当前节点的关系
    if (node.value < this.value) {
      if (this.left == null) {
        //如果左子树为空，就挂上
        this.left = node;
      } else {
        this.left.add(node);
      }
    } else {
      //如果传入的节点大于等于当前节点，就往右子树递归添加
      if (this.right == null) {
        this.right = node;
      } else {
        this.right.add(node);
      }
    }
  }

  //中序遍历
  public void infixOrder() {
    if (this.left != null) {
      this.left.infixOrder();
    }
    System.out.println(this);
    if (this.right != null) {
      this.right.infixOrder();
    }
  }

}