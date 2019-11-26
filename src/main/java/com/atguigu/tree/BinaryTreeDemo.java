package com.atguigu.tree;

import lombok.Data;
import lombok.ToString;

/**
 * 二叉树的前中后序遍历 及前中后序查找
 */
public class BinaryTreeDemo {

  public static void main(String[] args) {
    //手动创建一颗二叉树
    BinaryTree binaryTree = new BinaryTree();
    HeroNode node1 = new HeroNode(1, "宋江");
    HeroNode node2 = new HeroNode(2, "吴用");
    HeroNode node3 = new HeroNode(3, "卢俊义");
    HeroNode node4 = new HeroNode(4, "林冲");
    HeroNode node5 = new HeroNode(5, "关胜");
    node1.setLeft(node2);
    node1.setRight(node3);
    node3.setLeft(node5);
    node3.setRight(node4);

    binaryTree.setRoot(node1);

    //前序遍历 1 2 3 5 4
    System.out.println("前序遍历");
    binaryTree.preOrder();

    //中序遍历 2 1 5 3 4
    System.out.println("中序遍历");
    binaryTree.infixOrder();

    //后序遍历 2 5 4 3 1
    System.out.println("后序遍历");
    binaryTree.postOrder();

    System.out.println("===前序遍历查找===");//比较了4次
    HeroNode node = binaryTree.preOrderSearch(5);
    System.out.println(node);

    System.out.println("===中序遍历查找===");//比较了3次
    node = binaryTree.infixOrderSearch(5);
    System.out.println(node);

    System.out.println("===后序遍历查找===");//比较了2次
    node = binaryTree.postOrderSearch(5);
    System.out.println(node);
  }
}

/**
 * 定义binaryTree二叉树
 */
class BinaryTree {

  //根节点
  private HeroNode root;

  public void setRoot(HeroNode root) {
    this.root = root;
  }

  //前序遍历
  public void preOrder() {
    if (root != null) {
      root.preOrder();
    } else {
      System.out.println("该二叉树为空，无法遍历");
    }
  }

  //前序查找
  public HeroNode preOrderSearch(int no) {
    if (root != null) {
      return root.preOrderSearch(no);
    } else {
      System.out.println("该二叉树为空，无法遍历");
    }
    return null;
  }

  //中序遍历
  public void infixOrder() {
    if (root != null) {
      root.infixOrder();
    } else {
      System.out.println("该二叉树为空，无法遍历");
    }
  }

  //中序查找
  public HeroNode infixOrderSearch(int no) {
    if (root != null) {
      return root.infixOrderSearch(no);
    } else {
      System.out.println("该二叉树为空，无法遍历");
    }
    return null;
  }

  //后序遍历
  public void postOrder() {
    if (root != null) {
      root.postOrder();
    } else {
      System.out.println("该二叉树为空，无法遍历");
    }
  }

  //后序查找
  public HeroNode postOrderSearch(int no) {
    if (root != null) {
      return root.postOrderSearch(no);
    } else {
      System.out.println("该二叉树为空，无法遍历");
    }
    return null;
  }
}

@Data
@ToString(exclude = {"left", "right"})
class HeroNode {

  private int no;
  private String name;
  private HeroNode left;//左节点
  private HeroNode right;//右节点

  public HeroNode(int no, String name) {
    this.no = no;
    this.name = name;
  }

  //前序遍历
  public void preOrder() {
    System.out.println(this);//先输出当前节点
    //再遍历左节点
    if (this.left != null) {
      this.left.preOrder();
    }
    //再遍历右节点
    if (this.right != null) {
      this.right.preOrder();
    }
  }

  //中序遍历
  public void infixOrder() {
    //先遍历左节点
    if (this.left != null) {
      this.left.infixOrder();
    }
    System.out.println(this);//再输出当前节点
    //再遍历右节点
    if (this.right != null) {
      this.right.infixOrder();
    }
  }

  //后序遍历
  public void postOrder() {
    //再遍历左节点
    if (this.left != null) {
      this.left.postOrder();
    }
    //再遍历右节点
    if (this.right != null) {
      this.right.postOrder();
    }
    System.out.println(this);//再输出当前节点
  }

  //==============查找======================//
  //前序查找
  public HeroNode preOrderSearch(int no) {
    HeroNode result = null;
    System.out.println("前序查找比较");
    if (this.getNo() == no) {
      return this;
    }
    if (this.left != null) {
      result = this.left.preOrderSearch(no);
      if (result != null) {
        return result;
      }
    }
    if (this.right != null) {
      result = this.right.preOrderSearch(no);
      if (result != null) {
        return result;
      }
    }
    return result;
  }

  //中序查找
  public HeroNode infixOrderSearch(int no) {
    HeroNode result = null;

    if (this.left != null) {
      result = this.left.infixOrderSearch(no);

      if (result != null) {
        return result;
      }
    }

    System.out.println("后序查找比较");
    if (this.no == no) {
      return this;
    }

    if (this.right != null) {
      result = this.right.infixOrderSearch(no);
      if (result != null) {
        return result;
      }
    }
    return result;
  }

  //后序查找
  public HeroNode postOrderSearch(int no) {
    HeroNode result = null;

    if (this.left != null) {
      result = this.left.postOrderSearch(no);
      if (result != null) {
        return result;
      }
    }
    if (this.right != null) {
      result = this.right.postOrderSearch(no);
      if (result != null) {
        return result;
      }
    }
    System.out.println("后序查找比较");
    if (this.no == no) {
      return this;
    }
    return result;
  }

}