package com.atguigu.tree.Threadedbinary;

import lombok.Data;
import lombok.ToString;

/**
 * 线索化二叉树
 * （中序）
 */
public class ThreadedBinaryTreeDemo {

  public static void main(String[] args) {
    //测试中序线索化二叉树的功能
    HeroNode root = new HeroNode(1, "1");
    HeroNode node3 = new HeroNode(3, "3");
    HeroNode node6 = new HeroNode(6, "6");
    HeroNode node8 = new HeroNode(8, "8");
    HeroNode node10 = new HeroNode(10, "10");
    HeroNode node14 = new HeroNode(14, "14");
    root.setLeft(node3);
    root.setRight(node6);
    node3.setLeft(node8);
    node3.setRight(node10);
    node6.setLeft(node14);
    //中序遍历{8,3,10,1,14,6}
    //线索化
    ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
    threadedBinaryTree.setRoot(root);
    threadedBinaryTree.threadedNode(root);

    //10的前驱是 3  后继是1
    System.out.println(node10.getLeft());
    System.out.println(node10.getRight());

    //线索化二叉树遍历
    System.out.println("线索化二叉树遍历");
    threadedBinaryTree.threadedList();
  }

}

/**
 * 实现了线索化的二叉树
 */
class ThreadedBinaryTree {

  private HeroNode root;

  //为了实现线索化，需要创建要指向当前节点的前驱节点的指针
  private HeroNode pre;

  public void setRoot(HeroNode root) {
    this.root = root;
  }

  /**
   * 线索化二叉树遍历（中序）
   * 1.从root一直在左子树找到leftType为1的节点，即为开始节点
   * 2.如果当前节点指针是指向后继节点就一直遍历
   * 3.如果不是后继节点，就后移到右节点
   */
  public void threadedList() {
    HeroNode node = root;
    while (node != null) {
      //找到leftType=1的节点
      while (node.getLeftType() == 0) {
        node = node.getLeft();
      }
      System.out.println(node);

      //当节点的右指针指向的是后继节点，就可以持续遍历
      while (node.getRightType() == 1) {
        node = node.getRight();
        System.out.println(node);
      }
      //如果不是后继节点，就需要移动到右节点
      node = node.getRight();
    }
  }


  /**
   *  中序线索化二叉树
   */
  public void threadedNode(HeroNode node) {
    //节点为空时，结束线索化
    if (node == null) {
      return;
    }
    //线索化左子树
    threadedNode(node.getLeft());

    //====================//
    //重点线索化当前节点
    if (node.getLeft() == null) {
      //当左指针为空，需要指向前驱节点
      node.setLeft(pre);
      node.setLeftType(1);
    }

    //处理前驱节点
    if (node.getLeft() == null) {
      //让当前指针指向前驱节点
      node.setLeft(pre);
      node.setLeftType(1);
    }
    //处理后继节点
    if (pre != null && pre.getRight() == null) {
      pre.setRight(node);
      pre.setRightType(1);
    }
    //每处理一个节点就让当前节点为下一个节点的前驱节点
    pre = node;

    //====================//
    //线索化右子树
    threadedNode(node.getRight());
  }


  public void delNode(int no) {
    if (root != null) {
      if (root.getNo() == no) {
        root = null;
      } else {
        root.delNode(no);
      }
    } else {
      System.out.println("空树");
    }
  }

  public void preOrder() {
    if (root != null) {
      root.preOrder();
    } else {
      System.out.println("空树");
    }
  }

  public void infixOrder() {
    if (root != null) {
      root.infixOrder();
    } else {
      System.out.println("空树");
    }
  }

  public void postOrder() {
    if (root != null) {
      root.postOrder();
    } else {
      System.out.println("空树");
    }
  }

  public HeroNode preOrderSearch(int no) {
    if (root != null) {
      return root.preOrderSearch(no);
    }
    return null;
  }

  public HeroNode infixOrderSearch(int no) {
    if (root != null) {
      return root.infixOrderSearch(no);
    }
    return null;
  }

  public HeroNode postOrderSearch(int no) {
    if (root != null) {
      return root.postOrderSearch(no);
    }
    return null;
  }
}

@Data
@ToString(exclude = {"left", "right", "leftType", "rightType"})
class HeroNode {

  private int no;
  private String name;
  private HeroNode left;
  private HeroNode right;
  /**
   * leftType
   * 1.leftType=0：说明left指向的是左子树
   * 2.leftType=1：说明left指向的前驱节点
   */
  private int leftType;
  /**
   * rightType
   * 1.rightType=0：说明rightType指向的是右子树
   * 2.rightType=1：说明rightType指向的是后继节点
   */
  private int rightType;

  public HeroNode(int no, String name) {
    this.no = no;
    this.name = name;
  }

  //删除节点
  public void delNode(int no) {
    if (this.left != null && this.left.no == no) {
      this.left = null;
      return;
    }
    if (this.right != null && this.right.no == no) {
      this.right = null;
      return;
    }

    if (this.left != null) {
      this.left.delNode(no);
    }
    if (this.right != null) {
      this.right.delNode(no);
    }
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

  //后序遍历
  public void postOrder() {
    if (this.left != null) {
      this.left.postOrder();
    }
    if (this.right != null) {
      this.right.postOrder();
    }
    System.out.println(this);
  }

  //前序查找
  public HeroNode preOrderSearch(int no) {
    if (this.no == no) {
      return this;
    }
    HeroNode result = null;
    if (this.left != null) {
      result = this.left.preOrderSearch(no);
    }
    if (result != null) {
      return result;
    }
    if (this.right != null) {
      result = this.right.preOrderSearch(no);
    }
    return result;
  }

  //中序查找
  public HeroNode infixOrderSearch(int no) {
    HeroNode result = null;
    if (this.left != null) {
      result = this.left.infixOrderSearch(no);
    }
    if (result != null) {
      return result;
    }
    if (this.no == no) {
      return result;
    }
    if (this.right != null) {
      result = this.right.infixOrderSearch(no);
    }
    return result;
  }

  //后序遍历查找
  public HeroNode postOrderSearch(int no) {
    HeroNode result = null;
    if (this.left != null) {
      result = this.left.postOrderSearch(no);
    }
    if (result != null) {
      return result;
    }
    if (this.right != null) {
      result = this.right.postOrderSearch(no);
    }
    if (result != null) {
      return result;
    }
    if (this.no == no) {
      return this;
    }
    return result;
  }
}