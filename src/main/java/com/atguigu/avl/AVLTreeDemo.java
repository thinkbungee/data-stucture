package com.atguigu.avl;

import lombok.Data;
import lombok.ToString;

/**
 * 平衡二叉树
 */
public class AVLTreeDemo {

  public static void main(String[] args) {
    //测试 平衡二叉树
    int[] arr = {10, 11, 7, 6, 8, 9};
    AVLTree avlTree = new AVLTree();
    for (int i : arr) {
      avlTree.add(new Node() {{
        setValue(i);
      }});
    }
    System.out.println("树的高度" + avlTree.getRoot().height());
    System.out.println("树的左子树高度" + avlTree.getRoot().leftHeight());
    System.out.println("树的右子树高度" + avlTree.getRoot().rightHeight());
    System.out.println("当前根节点：" + avlTree.getRoot());

  }
}

class AVLTree {

  private Node root;

  public Node getRoot() {
    return root;
  }

  public void add(Node node) {
    if (root == null) {
      root = node;
    } else {
      root.addByBalance(node);
    }

  }
}

@Data
@ToString(exclude = {"left", "right"})
class Node {

  int value;
  Node left;
  Node right;

  /**
   * 返回以该节点为根节点的树的高度
   */
  public int height() {
    return Math.max(left == null ? 0 : left.height(),
        right == null ? 0 : right.height()) + 1;
  }

  /**
   * 返回右子树的高度
   */
  public int rightHeight() {
    if (right == null) {
      return 0;
    }
    return right.height();
  }

  public int leftHeight() {
    if (left == null) {
      return 0;
    }
    return left.height();
  }

  /**
   * 左旋转
   */
  public void leftRotate() {
    //创建新的节点，将当前的节点的值赋给新节点
    Node newNode = new Node() {{
      setValue(value);
    }};
    //把新节点的左子树设置成当前节点的左子树
    newNode.left = left;
    //把新的节点的右子树设置成当前节点的右子树的左子树
    newNode.right = right.left;
    //把当前节点的值替换成右子节点的值
    value = right.value;
    //把当前节点的右子树设置成当前节点的右子树的右子树
    right = right.right;
    //把当前节点的左子树（左子节点）设置成新的节点
    left = newNode;
  }

  /**
   * 右旋转
   */
  public void rightRotate() {
    Node newNode = new Node() {{
      setValue(value);
    }};
    newNode.right = right;
    newNode.left = left.right;

    value = left.value;
    left = left.left;
    right = newNode;

  }

  /**
   * 添加节点方法，使其满足平衡二叉树的添加方法
   */
  public void addByBalance(Node node) {
    if (node == null) {
      return;
    }

    if (node.value < this.value) {
      if (this.left == null) {
        this.left = node;
      } else {
        this.left.addByBalance(node);
      }
    } else {
      if (this.right == null) {
        this.right = node;
      } else {
        this.right.addByBalance(node);
      }
    }

    //当添加完节点后，需要看是否满足平衡二叉树
    if (rightHeight() - leftHeight() > 1) {
      //右子树高度 - 左子树高度 > 1 满足左旋转条件
      if (right != null && right.leftHeight() > right.rightHeight()) {
        //右子树的左子树 大于 右子树的右子树的高度，
        // 1.先对右子树右旋转
        right.rightRotate();
        //2.再左旋转
        leftRotate();
      } else {
        leftRotate();
      }
      return;
    }

    if (leftHeight() - rightHeight() > 1) {
      //左子树高度 - 右子树高度 > 1 满足右旋转条件
      if (left != null && left.rightHeight() > left.leftHeight()) {
        //左子树的右子树 大于左子树的左子树的高度
        //1.先对左子树左旋转
        left.leftRotate();
        //2.再右旋转
        rightRotate();
      } else {
        rightRotate();
      }
    }


  }

}
