package com.atguigu.stack;

/**
 * 使用栈来实现计算器的功能
 * 中缀表达式
 */
public class Calculator {

    public static void main(String[] args) {
        /**
         *
         * 思路
         * a。通过一个index索引，来遍历输入的表达式
         * b。如果是数字 就入数栈
         * c。如果是符号，就分如下情况
         *  c.1。如果发现当前的符号栈为空，就直接入栈
         *  c.2。如果符号栈有操作符，就需要比较优先级
         *      c.2.1。如果当前的操作符的优先级小于等于栈中的操作符，就从数栈中pop出两个数，再从符号栈pop出一个符号，进行运算，将得到的结果入数栈，再将当前的操作符入符号栈
         *      c.2.2。如果当前操作符优先级大于栈中的操作符，就直接入栈
         * d。当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行
         * e。最后在数栈只有一个数字，就是该表达式中的结果
         */
        String expression = "3+2*6-2+5*6";
        calculator(expression);//问题：只能是个位数的运算


        /**
         * 分析
         * 1.多处理多位数时，在处理数时，需要知道index后一位，如果是数就继续扫描否则就入栈
         * 2.定义一个变量字符串，用于拼接
         */
        String expression2 = "1+2+3+4+5+6+7+8+9+10";
        calculator2(expression2);
    }

    /**
     * 支持多位数运算
     *
     * @param expression
     */
    private static void calculator2(String expression) {
        //创建2个栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        //定义扫描索引
        int index = 0;
        char a = ' ';//每次扫描保存到a中

        int num1 = 0;
        int num2 = 0;
        int oper = 0;

        int result = 0;

        String keepNum = "";//用于拼接多位数

        //开始扫描表达式
        while (true) {
            //取出每一个字符
            a = expression.substring(index, index + 1).charAt(0);

            if (operStack.isOper(a)) {
                //如果是符号
                if (operStack.isEmpty()) {
                    //符号栈 如果为空直接入栈
                    operStack.push(a);
                } else {
                    //如果不为空，再判断符号的优先级
                    if (operStack.priority(a) <= operStack.priority(operStack.peek())) {
                        //当前符号优先级 小于 符号栈 栈顶的符号
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();

                        //运算
                        result = numStack.cal(num1, num2, oper);

                        //将运算结果入数栈
                        numStack.push(result);
                        //将当前的操作符 入符号栈
                        operStack.push(a);
                    } else {
                        //优先级大直接入符号栈
                        operStack.push(a);
                    }
                }
            } else {
                /*//如果是数 直接入数栈
                numStack.push(Integer.parseInt(String.valueOf(a)));
                 */
                //多位数处理
                keepNum += a;

                if (index == expression.length() - 1) {
                    //如果a已经是最后一位，直接入栈
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //是操作符 才入数栈，否则继续扫描，并用keepNum拼接起来
                        numStack.push(Integer.parseInt(keepNum));
                        //入栈后 清空keepNum
                        keepNum = "";
                    }
                }
            }

            //index自增
            index++;
            if (index >= expression.length()) {
                //扫描结束
                break;
            }
        }

        //表达式 扫描完毕，开始计算
        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            oper = operStack.pop();
            num1 = numStack.pop();
            num2 = numStack.pop();
            result = operStack.cal(num1, num2, oper);
            numStack.push(result);
        }

        System.out.printf("表达式%s=%d \n", expression, numStack.pop());

    }


    /**
     * 计算一个表达式的结果
     *
     * @param expression 表达式
     * @return 结果
     */
    private static void calculator(String expression) {
        //创建2个栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        //定义扫描索引
        int index = 0;
        char a = ' ';//每次扫描保存到a中

        int num1 = 0;
        int num2 = 0;
        int oper = 0;

        int result = 0;

        //开始扫描表达式
        while (true) {
            //取出每一个字符
            a = expression.substring(index, index + 1).charAt(0);

            if (operStack.isOper(a)) {
                //如果是符号
                if (operStack.isEmpty()) {
                    //符号栈 如果为空直接入栈
                    operStack.push(a);
                } else {
                    //如果不为空，再判断符号的优先级
                    if (operStack.priority(a) <= operStack.priority(operStack.peek())) {
                        //当前符号优先级 小于 符号栈 栈顶的符号
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();

                        //运算
                        result = numStack.cal(num1, num2, oper);

                        //将运算结果入数栈
                        numStack.push(result);
                        //将当前的操作符 入符号栈
                        operStack.push(a);
                    } else {
                        //优先级大直接入符号栈
                        operStack.push(a);
                    }
                }
            } else {
                //如果是数 直接入数栈
                numStack.push(Integer.parseInt(String.valueOf(a)));
            }

            //index自增
            index++;
            if (index >= expression.length()) {
                //扫描结束
                break;
            }
        }

        //表达式 扫描完毕，开始计算
        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            oper = operStack.pop();
            num1 = numStack.pop();
            num2 = numStack.pop();
            result = operStack.cal(num1, num2, oper);
            numStack.push(result);
        }

        System.out.printf("表达式%s=%d \n", expression, numStack.pop());

    }
}

class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//栈
    private int top = -1;//top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //入栈 push
    public void push(int value) {
        //判断是否栈满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈 pop
    public int pop() {
        //判断是否栈空
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈 (从栈顶开始遍历)
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }


    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //查看栈顶的值（并不是真正的出栈）
    public int peek() {
        return stack[top];
    }

    //返回运算符的优先级，优先级是我们确定的
    public int priority(int oper) {

        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是否是运算符号
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //提供计算的方法
    //num2是栈中后弹出的数
    public int cal(int num1, int num2, int oper) {
        int result = 0;
        switch (oper) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }
}

