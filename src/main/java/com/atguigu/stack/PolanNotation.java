package com.atguigu.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 栈 实现逆波兰表达式
 */
public class PolanNotation {
    public static void main(String[] args) {
        /**
         * 定义一个逆波兰表达式
         * （3+4）*5-6 ： 3 4 + 5 * 6 -
         *  为了方便，数字和符合直接使用空格隔开
         */
        String suffixExpression = "3 4 + 5 * 6 -";
        /**
         * 思路
         * 1.将表达式放入List中
         * 2.将List传递，配合栈完成计算
         */
        List<String> rpnList = getListString(suffixExpression);
        System.out.println(rpnList);
        System.out.println(calculate(rpnList));
    }

    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        return new ArrayList<>(Arrays.asList(split));
    }

    //完成对逆波兰表达式的计算
    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();

        //遍历
        for (String item : list) {
            //使用正则表达式取出 数
            if (item.matches("\\d+")) {//匹配多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数进行运算再入栈
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int num = 0;
                switch (item) {
                    case "+":
                        num = num2 + num1;
                        break;
                    case "-":
                        num = num2 - num1;
                        break;
                    case "*":
                        num = num2 * num1;
                        break;
                    case "/":
                        num = num2 / num1;
                        break;
                }
                stack.push(String.valueOf(num));
            }
        }
        return Integer.parseInt(stack.pop());

    }
}
