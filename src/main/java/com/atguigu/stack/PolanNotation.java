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


        /**
         * 中缀表达式转为后缀表达式
         * 测试：
         * 1.将表达式字符串转为list
         * 2.将list转为后缀表达式对应的list
         */
        String expression = "1+((2+3)*4)-5";
        List<String> list = toInfixExpressionList(expression);
        System.out.println(list);

        List<String> parseSuffixExpressionList = parseSuffixExpressionList(list);
        System.out.println(parseSuffixExpressionList);

        //运行该后缀表达式
        System.out.println(calculate(parseSuffixExpressionList));


    }


    /**
     * 把中缀表达式的list 转换为 后缀表达式的list
     */
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义2个zhan
        Stack<String> s1 = new Stack<>();//符号栈

        //Stack s2 = new Stack();
        //s2在整个过程中没有pop操作，最后还需要逆序输出，所以使用list替代
        List<String> list = new ArrayList<>();

        //遍历ls
        for (String item : ls) {
            if (item.matches("\\d+")) {
                //如果是数，加入list
                list.add(item);
            } else if (item.equals("(")) {
                //如果是 左括号 (  入符号栈
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是 右括号 ）操作如下
                while (!s1.peek().equals("(")) {
                    list.add(s1.pop());
                }
                s1.pop();//把左括号消除
            } else {
                //如果是符号，就需要考虑 优先级
                //当item小于s1栈顶的符号优先级
                //当是左括号时 优先级最低
                while (s1.size() != 0 &&
                        Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    list.add(s1.pop());
                }
                //将item压入栈
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出加入到list
        while (s1.size() != 0) {
            list.add(s1.pop());
        }
        return list;
    }


    /**
     * 将中缀表达式转成List
     */
    public static List<String> toInfixExpressionList(String s) {
        //中缀表达式的list
        List<String> ls = new ArrayList<>();

        int i = 0;//指针，辅助遍历
        String str = "";//对多位数的拼接
        char c;//遍历的字符
        do {
            //如果是非数字，就直接加入ls
            //0->48 9->57
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;//i需要后移
            } else {
                //如果是一个数字，需要看下一位是否是数字（考虑多位数）
                str = "";//置空
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }

        } while (i < s.length());

        return ls;
    }


    /**
     * 以空格为分隔的后缀表达式转为list
     */
    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        return new ArrayList<>(Arrays.asList(split));
    }

    /**
     * 完成对逆波兰表达式的计算
     *
     * @param list
     * @return
     */
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

//Operation 可以返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;//加
    private static int SUB = 1;//减
    private static int MUL = 2;//乘
    private static int DIV = 2;//除

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            case "(":
                result = 0;
                break;
            default:
                System.out.println("操作符不存在：" + operation);
                break;
        }
        return result;
    }

}
