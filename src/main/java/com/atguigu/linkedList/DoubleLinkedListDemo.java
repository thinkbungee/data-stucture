package com.atguigu.linkedList;

import lombok.Data;

/**
 * 双向链表
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        System.out.println("双向链表的测试：");
        //创建一些节点
        Hero2 hero1 = new Hero2(1, "宋江", "及时雨");
        Hero2 hero2 = new Hero2(2, "卢俊义", "玉麒麟");
        Hero2 hero3 = new Hero2(3, "吴用", "智多星");
        Hero2 hero4 = new Hero2(4, "林冲", "豹子头");
        //创建双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.add(hero2);
        System.out.println("显示双向链表");
        doubleLinkedList.list();

        //修改
        Hero2 newHero = new Hero2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHero);
        System.out.println("修改后的链表：");
        doubleLinkedList.list();

        //删除
        doubleLinkedList.delete(3);
        System.out.println("删除后的链表：");
        doubleLinkedList.list();

        //测试第二种添加方式
        Hero2 hero11 = new Hero2(1, "宋江", "及时雨");
        Hero2 hero21 = new Hero2(2, "卢俊义", "玉麒麟");
        Hero2 hero31 = new Hero2(3, "吴用", "智多星");
        Hero2 hero41 = new Hero2(4, "林冲", "豹子头");

        System.out.println("==========");
        System.out.println("按照编号顺序添加");
        DoubleLinkedList doubleLinkedList2 = new DoubleLinkedList();
        doubleLinkedList2.addByNo(hero11);
        doubleLinkedList2.addByNo(hero31);
        doubleLinkedList2.addByNo(hero21);
        doubleLinkedList2.addByNo(hero41);
        doubleLinkedList2.list();

    }

}

class DoubleLinkedList {

    //初始化头结点
    private Hero2 head = new Hero2(0, "", "");

    public Hero2 getHead() {
        return head;
    }

    //删除节点
    //对于双向链表，可以直接找到要删除的这个节点，找到后自我删除即可
    public void delete(int no) {
        //判空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        Hero2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            //双向链表删除
            temp.pre.next = temp.next;
            //这里代码会有问题，如果是最后的节点会抛npe,所以要加判断
            // 如果是最后节点，不需要执行
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }

            //单向链表删除
            //temp.next = temp.next.next;

        } else {
            System.out.println("没有找到要删除的节点:" + no);
        }
    }

    //修改节点
    public void update(Hero2 hero) {
        //判空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        Hero2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == hero.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = hero.name;
            temp.nickName = hero.nickName;
        } else {
            System.out.println("没有找到该节点，不能修改" + hero.no);
        }
    }

    //第二种添加方式(按照编号顺序添加)
    public void addByNo(Hero2 hero) {

        Hero2 temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > hero.no) {
                //位置找到
                break;
            } else if (temp.next.no == hero.no) {
                //编号重复了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("准备插入的英雄编号已经存在，编号：" + hero.no + "不能进行添加");
        } else {
            if (temp.next == null) {
                //表示 直接添加到最后
                temp.next = hero;
                hero.pre = temp;
            } else {
                //插入hero
                hero.next = temp.next;
                hero.pre = temp;
                //修复hero两边的关系
                temp.next = hero;
                hero.next.pre = hero;

            }


        }


    }

    //添加（默认添加到最后）
    public void add(Hero2 hero) {
        Hero2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = hero;
        hero.pre = temp;
    }

    //遍历双向链表
    public void list() {
        //判空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        Hero2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

//Hero2

/**
 * 英雄节点
 */
@Data
class Hero2 {
    public int no;//英雄编号
    public String name;//英雄名字
    public String nickName;//英雄昵称
    public Hero2 next;//指向下一个节点，默认为null
    public Hero2 pre;//指向前一个节点，默认为null

    //构造器
    public Hero2(int no, String name, String nickName) {
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
