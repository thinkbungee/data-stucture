package com.atguigu.linkedList;

import lombok.Data;

/**
 * 单向环形链表
 * <p>
 * 约瑟夫问题(丢手帕问题)
 */
public class Josepfu {

    public static void main(String[] args) {
        //测试环形链表
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        //加入5个节点
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();


        //测试约瑟夫问题
        circleSingleLinkedList.josepfuCountBoy(1, 2, 25);
    }
}

//创建一个单向 环形链表
class CircleSingleLinkedList {

    //first节点
    private Boy first;

    //添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums) {
        //nums 校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }

        Boy curBoy = null;//辅助指针，帮助构建环形链表

        //使用for来创建
        for (int i = 1; i <= nums; i++) {
            //根据编号，创建节点
            Boy boy = new Boy(i);
            //如果是第一个需要特殊处理
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环状
                curBoy = first;//让cur指向当前位置
            } else {
                //将boy加入到最后
                curBoy.setNext(boy);
                //将boy指向first构成环
                boy.setNext(first);
                //辅助指针后移
                curBoy = boy;
            }
        }
    }

    //遍历链表
    public void showBoy() {
        //判空
        if (first == null) {
            System.out.println("该环形链表为空");
            return;
        }
        //辅助指针，辅助遍历
        Boy curBoy = first;
        while (true) {
            System.out.println("当前boy编号为：" + curBoy.getNo());
            if (curBoy.getNext() == first) {
                //已经遍历完毕
                break;
            }
            //后移
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 约瑟夫问题实现
     *
     * @param startNo  表示从第几个小孩开始报数
     * @param countNum 表示数几下
     * @param nums     表示有多少小孩在圈中
     */
    public void josepfuCountBoy(int startNo, int countNum, int nums) {
        //对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //设置圈中小孩的个数
        addBoy(nums);
        //创建辅助指针
        Boy helper = first;
        //先让helper 指向最后一个节点
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }

        //让first和helper移动k-1次 到k的位置
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        //报数 让first和helper 指针 同时移动 m-1次 出圈
        while (true) {
            if (helper == first) {
                //这时圈中只有一人,出圈
                System.out.println("最后留在圈中小孩的编号：" + first.getNo());
                break;
            }
            //同时移动m-1次
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时 first指向的节点 即出圈的节点
            //打印编号
            System.out.println("小孩的编号：" + first.getNo());

            //出圈
            first = first.getNext();
            helper.setNext(first);
        }

    }
}

//创建节点类，boy
@Data
class Boy {
    //编号
    private int no;
    //指向下一个节点
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }
}


