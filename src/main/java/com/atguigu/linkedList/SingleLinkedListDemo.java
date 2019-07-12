package com.atguigu.linkedList;

import lombok.Data;

/**
 * 自己实现一个单链表
 * 实现水浒传英雄排名
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //测试
        Hero h1 = new Hero(1, "宋江", "及时雨");
        Hero h2 = new Hero(2, "卢俊义", "玉麒麟");
        Hero h3 = new Hero(3, "吴用", "智多星");
        Hero h4 = new Hero(4, "林冲", "豹子头");
        SingleLinkedList list = new SingleLinkedList();
        //======无序添加=========//
//        list.add(h1);
//        list.add(h2);
//        list.add(h3);
//        list.add(h4);

        //=====有序添加======//
        list.addByOrder(h4);
        list.addByOrder(h2);
        list.addByOrder(h1);
        list.addByOrder(h3);

        //======测试修改操作======//
//        h4.setName(h4.getName() + "%%%%%");
//        h4.setNickName(h4.getNickName() + "%%%%");
//        list.updateHeroByNo(h4);//修改已经有的英雄
//        Hero h5 = new Hero(5, "李逵", "黑旋风");
//        list.updateHeroByNo(h5);//修改没有的英雄

        //=====测试删除操作======//
        list.deleteHeroByNo(9);
        list.deleteHeroByNo(1);
        list.deleteHeroByNo(2);
        list.deleteHeroByNo(3);
        list.deleteHeroByNo(4);

        //显示
        list.list();
    }
}

/**
 * 单链表 管理英雄
 */
class SingleLinkedList {

    /**
     * 先初始化一个头节点，并且这个头节点是固定的，不存放具体的数据
     */
    private Hero head = new Hero(0, "", "");

    /**
     * 删除编号为no的节点
     * 思路：
     * 1.先找到需要删除的这个节点的前一个节点temp
     * 2.temp.next = temp.next.next
     * 3.被删除的节点，将不会有其他引用指向，将会被垃圾回收机制进行回收
     *
     * @param no
     */
    public void deleteHeroByNo(int no) {

        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        Hero temp = head;//需要辅助节点指向 要删除的节点的前一个元素
        boolean flag = false;//标志是否找到
        while (true) {

            if (temp.next == null) {
                break;
            }

            if (temp.next.no == no) {
                //找到的是 要删除的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            //进行删除
            temp.next = temp.next.next;
        } else {
            System.out.println("不存在该编号的英雄：" + no);
        }

    }

    /**
     * 修改节点的信息，根据no编号来修改（即no编号不能修改）
     */
    public void updateHeroByNo(Hero hero) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        //找到要修改的节点
        Hero temp = head.next;
        boolean flag = false;//标志是否找到该节点
        while (true) {
            if (temp == null) {
                break;//已经遍历完该链表
            }

            if (temp.no == hero.no) {
                //找到了该节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = hero.name;
            temp.nickName = hero.nickName;
        } else {
            System.out.println("没有找到该编号的英雄：" + hero.no);
        }
    }

    /**
     * 添加
     * 当不考虑编号顺序时
     * 1.找到当前链表的最后节点
     * 2.将最后这个节点的next 指向新节点
     *
     * @param Hero
     */
    public void add(Hero Hero) {
        //需要一个辅助节点temp遍历链表
        Hero temp = head;
        //遍历链表
        while (true) {
            //判断链表是否为空，为空直接添加
            if (temp.next == null) {
                break;
            }
            //如果不是最后的节点，将temp的next后移
            temp = temp.next;
        }

        //当退出循环是，temp就是最后一个节点
        // 所以直接将新节点添加到最后
        temp.next = Hero;
    }

    /**
     * 第二种添加英雄时，根据排名将英雄插入到指定位置（如果有这个排名则添加失败，并给出提示）
     */
    public void addByOrder(Hero hero) {
        //头结点不能动，仍然使用辅助节点
        //因为单链表，temp是位于添加位置的前一个节点，否则插入不了
        Hero temp = head;
        boolean flag = false;//flag标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {
                //说明temp已经在链表的最后
                break;
            }

            if (temp.next.no > hero.no) {//比较的是temp.next与hero的编号大小
                //位置找到，就在该temp位置后面添加
                break;
            } else if (temp.next.no == hero.no) {
                //希望添加hero 已经存在，所以我们不能继续添加了
                flag = true;
                break;
            }

            temp = temp.next;
        }
        //判断flag的值
        if (flag) {
            //不能添加，编号已经存在
            System.out.printf("准备插入的英雄的编号%d已经存在，不能加入", hero.no);
            System.out.println();
        } else {
            //插入操作
            hero.next = temp.next;
            temp.next = hero;

        }

    }

    /**
     * 显示链表
     */
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //需要一个辅助节点 进行遍历
        Hero temp = head.next;
        while (true) {
            System.out.println(temp);

            //判断是否到链表最后
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
//        //当跳出循环时，temp是最后一个节点，并没有输出
//        System.out.println(temp);
    }

}

/**
 * 英雄节点
 */
@Data
class Hero {
    public int no;//英雄编号
    public String name;//英雄名字
    public String nickName;//英雄昵称
    public Hero next;//指向下一个节点

    //构造器
    public Hero(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
