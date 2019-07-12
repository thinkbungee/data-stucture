package com.atguigu.queue;

import java.util.Scanner;

/**
 * 使用数组模拟队列
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            //展示菜单
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;

                case 'g'://取出数据
                    try {
                        System.out.printf("取出的数据是%d\n", queue.poll());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 'h':
                    try {
                        System.out.printf("队列头的数据是%d\n", queue.getHead());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 'e':
                    scanner.close();
                    loop = false;
                    break;
            }
        }
    }

}

/**
 * 使用数组编写一个ArrayQueue类
 */
class ArrayQueue {
    private int maxSize;//数组的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;// 模拟队列的数组

    /**
     * 构造器并初始化
     *
     * @param arrMaxSize
     */
    public ArrayQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        this.arr = new int[maxSize];
        this.front = -1;//指向队列头部，初始化时，指向队列头的前一个位置
        this.rear = -1;//指向队列尾，指向队列最后一个位置
    }

    /**
     * 判断队列是否满
     *
     * @return
     */
    public boolean isFull() {
        return this.rear == this.maxSize - 1;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmply() {
        return this.rear == this.front;
    }

    /**
     * 队列入栈
     *
     * @param data
     * @return
     */
    public boolean addQueue(int data) {
        if (isFull()) {
            System.out.println("队列已经满了");
            return false;
        }
        this.rear++;
        this.arr[this.rear] = data;
        return true;
    }

    /**
     * 队列出栈
     *
     * @return
     */
    public Object poll() {
        if (isEmply()) {
            throw new RuntimeException("队列已经为空，无法获取");
        }
        this.front++;
        return this.arr[this.front];
    }

    /**
     * 展示当前队列的所有的数据
     */
    public void showQueue() {
        if (isEmply()) {
            System.out.println("队列为空，没有数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }

    }

    /**
     * 显示队列的头数据,(不是取出数据)
     *
     * @return
     */
    public int getHead() {
        if (isEmply()) {
            throw new RuntimeException("队列为空，没有数据");
        }

        return this.arr[this.front + 1];
    }
}
