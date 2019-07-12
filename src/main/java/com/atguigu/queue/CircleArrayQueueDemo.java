package com.atguigu.queue;

import java.util.Scanner;

/**
 * 使用数组模拟队列
 * 环形数组 提高数组的利用率
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(3);
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
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
 * 使用数组编写一个CircleArrayQueue类
 */
class CircleArrayQueue {
    private int maxSize;//数组的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;// 模拟队列的数组

    /**
     * 构造器并初始化
     *
     * @param arrMaxSize
     */
    public CircleArrayQueue(int arrMaxSize) {
        //该数组会预留一个空间，所以在构造器需要多增加一位
        this.maxSize = arrMaxSize + 1;
        this.arr = new int[maxSize];
        //front、rear默认就是0
//        this.front = 0;
//        this.rear = 0;
    }

    /**
     * 判断队列是否满
     *
     * @return
     */
    public boolean isFull() {
        return (this.rear + 1) % maxSize == front;
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
            System.out.println("队列已经满了,无法再进行添加");
            return false;
        }

        this.arr[this.rear] = data;
        //环形队列，考虑取余
        this.rear = (this.rear + 1) % this.maxSize;
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

        //front 是直接指向队列第一个元素
        int value = this.arr[this.front];
        //front 后移
        this.front = (this.front + 1) % this.maxSize;
        return value;
    }

    /**
     * 展示当前队列的所有的数据
     */
    public void showQueue() {
        if (isEmply()) {
            System.out.println("队列为空，没有数据");
            return;
        }

        //思路：从 front开始遍历，遍历多少个元素？
        for (int i = this.front; i < this.front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % this.maxSize, arr[i % this.maxSize]);
        }

    }

    /**
     * 求出当前队列中有效数据的个数
     *
     * @return
     */
    public int size() {
        //需要思考
        return (this.rear + this.maxSize - this.front) % this.maxSize;
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

        return this.arr[this.front];
    }
}
