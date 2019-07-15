package com.atguigu.linkedList;

import java.util.Stack;

/**
 * 练习题
 */
public class SingleLinkedListExercise {


    public static void main(String[] args) {
        //测试
        Hero h1 = new Hero(1, "宋江", "及时雨");
        Hero h2 = new Hero(2, "卢俊义", "玉麒麟");
        Hero h3 = new Hero(3, "吴用", "智多星");
        Hero h4 = new Hero(4, "林冲", "豹子头");


        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(h1);
        singleLinkedList.addByOrder(h2);
        singleLinkedList.addByOrder(h3);
        singleLinkedList.addByOrder(h4);

        //1.获取单链表的节点的个数(如果是有头结点的链表，就不统计头结点)
        System.out.println("该链表的有效节点个数：" + getLength(singleLinkedList.getHead()));

        //2.查找单链表中的倒数第K个节点（新浪面试题）
        System.out.println("该链表的倒数第K个节点是：" + findLastIndexNode(singleLinkedList.getHead(), 1));

        //3.单链表的反转（腾讯面试题，有难度）
        System.out.println("==原链表：");
        singleLinkedList.list();
        reverseList(singleLinkedList.getHead());
        System.out.println("==反转后的链表：");
        singleLinkedList.list();

        //4.从尾到头打印单链表(百度面试题,使用栈的特性)
        System.out.println("逆序打印链表(没有改变原链表的结构)");
        reversePrint(singleLinkedList.getHead());

    }

    //4.从尾到头打印单链表(百度面试题,使用栈的特性)
    public static void reversePrint(Hero head) {
        if (head.next == null) {
            return;
        }
        //创建 栈，将各个节点压入栈中
        Stack<Hero> heroStack = new Stack<>();
        Hero cur = head.next;
        //遍历，并压栈
        while (cur != null) {
            heroStack.push(cur);
            cur = cur.next;
        }
        //出栈，并打印
        while (heroStack.size() > 0) {
            System.out.println(heroStack.pop());
        }

    }


    //3.单链表的反转（腾讯面试题，有难度）
    public static void reverseList(Hero head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助的指针，帮助我们遍历原来的链表
        Hero cur = head.next;
        //指向当前[cur]节点的下一个节点
        Hero next = null;
        //每遍历一个节点，就将其摘下并加入到该reverseHead的第一个位置
        Hero reverseHead = new Hero(0, "", "");

        while (cur != null) {
            //保存cur的下一个节点
            next = cur.next;

            //将cur添加到reverse链表的最前端
            cur.next = reverseHead.next;
            reverseHead.next = cur;

            //将cur后移
            cur = next;
        }

        //将head.next 指向reverseHead.next
        head.next = reverseHead.next;


    }

    //2.查找单链表中的倒数第K个节点（新浪面试题）
    public static Hero findLastIndexNode(Hero head, int index) {
        if (head.next == null) {
            //说明这是一个带头结点的空链表
            return null;
        }
        //第一次遍历得到链表长度
        int length = getLength(head);
        //第二次遍历  length-index 位置 即倒数第k个节点
        //校验index
        if (index <= 0 || index > length) {
            return null;
        }
        //定义辅助变量
        Hero cur = head.next;
        for (int i = 0; i < length - index; i++) {
            cur = cur.next;
        }
        return cur;

    }

    /**
     * 获取单链表的节点的个数(没有统计头结点)
     *
     * @param head 链表的头结点
     * @return 返回有效节点的个数
     */
    //1.获取单链表的节点的个数(如果是有头结点的链表，就不统计头结点)
    public static int getLength(Hero head) {
        if (head.next == null) {
            //说明这是一个带头结点的空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助变量
        Hero cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }


}
