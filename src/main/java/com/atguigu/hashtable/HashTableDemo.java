package com.atguigu.hashtable;

import java.security.PublicKey;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

/**
 * 哈希表的Google上机题
 */
public class HashTableDemo {


  public static void main(String[] args) {
    //创建哈希表
    MyHashTable hashTable = new MyHashTable(7);
    //简单的菜单
    String key = "";
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("add:添加雇员");
      System.out.println("list:显示雇员");
      System.out.println("find:查找雇员");
      System.out.println("exit:退出系统");

      key = scanner.next();

      switch (key) {
        case "add":
          System.out.println("输入id");
          int id = scanner.nextInt();
          System.out.println("输入name");
          String name = scanner.next();
          Emp emp = new Emp(id, name);
          hashTable.add(emp);
          break;
        case "list":
          hashTable.list();
          break;
        case "find":
          System.out.println("输入要查找的雇员id");
          id = scanner.nextInt();
          hashTable.findEmpById(id);
          break;
        case "exit":
          scanner.close();
          System.exit(0);
        default:
          break;
      }
    }
  }
}

class MyHashTable {

  private EmpLinkedList[] empLinkedLists;
  private int size;//表示有多少条链表

  public MyHashTable(int size) {
    this.size = size;
    //初始化table
    empLinkedLists = new EmpLinkedList[size];
    //初始化每条链表
    for (int i = 0; i < size; i++) {
      empLinkedLists[i] = new EmpLinkedList();
    }
  }

  public void add(Emp emp) {
    int no = hashFun(emp.id);
    empLinkedLists[no].add(emp);
  }

  public void list() {
    for (int i = 0; i < size; i++) {
      empLinkedLists[i].list(i);
    }
  }

  public void findEmpById(int id) {
    int no = hashFun(id);
    Emp empById = empLinkedLists[no].findEmpById(id);
    if (empById != null) {
      System.out.println("在第" + (no + 1) + "条链表中找到该雇员");
    } else {
      System.out.println("没有找到该雇员");
    }
  }

  /**
   * 散列函数
   */
  public int hashFun(int id) {
    return id % size;

  }

}

/**
 * 员工类
 */
class Emp {

  public int id;
  public String name;
  public Emp next;

  public Emp(int id, String name) {
    this.id = id;
    this.name = name;
  }
}

/**
 * 链表
 */
class EmpLinkedList {

  //头指针，直接指向第一个emp
  private Emp head;//默认为null

  /**
   * 添加 默认直接添加到链表最后
   */
  public void add(Emp emp) {
    if (head == null) {
      //添加的第一个雇员
      head = emp;
      return;
    }

    Emp curEmp = head;
    while (true) {
      if (curEmp.next == null) {
        break;
      }
      curEmp = curEmp.next;//后移
    }
    curEmp.next = emp;//添加雇员
  }

  /**
   * 遍历链表的雇员信息
   */
  public void list(int no) {
    if (head == null) {
      System.out.println("第" + (no + 1) + "链表为空");
      return;
    }

    System.out.println("第" + (no + 1) + "链表信息为：");
    Emp curEmp = head;
    while (true) {
      System.out.println("id=" + curEmp.id + "，name=" + curEmp.name);
      if (curEmp.next == null) {
        break;
      }
      curEmp = curEmp.next;
    }

  }

  /**
   * 通过id查找雇员
   */
  public Emp findEmpById(int id) {
    if (head == null) {
      System.out.println("链表为空");
      return null;
    }
    Emp curEmp = head;
    while (true) {
      if (curEmp.id == id) {
        break;
      }
      if (curEmp.next == null) {
        curEmp = null;
        break;
      }
      curEmp = curEmp.next;
    }
    return curEmp;
  }
}