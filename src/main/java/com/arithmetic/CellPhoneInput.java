package com.arithmetic;

/**
 * 9宫格输入法
 *
 * 假设有九宫格输入法键盘布局如下：
 * [1,.?!]  [2ABC]  [3DEF]
 * [4GHI]   [5JKL]  [6MNO]
 * [7PQRS]  [8TUV]  [9WXYZ]
 *          [0空格]
 *  注意：中括号[ ]仅为了表示键盘的分隔，不是输入字符。每个中括号中，位于首位的数字字符即是键盘的按键，按一下即可输入该数字字符。多次按同一个键，则输入的字符依次循环轮流，例如按两次3，则输入D；按5次7，则输入S；按6次2，则输入A。按键0的输入组合是0和空格字符，即按两次0输入空格。
 * 你需要对于给定的按键组合，给出该组合对应的文本
 *
 * 输入样例:  22  5555  22  666 00  88  888 7777  4444  666 44
 * 输出样例： ALAN TURING
 *
 *
 */
public class CellPhoneInput {

  static String[] input = {
      "0 ", "1,.?!", "2ABC",
      "3DEF", "4GHI", "5JKL",
      "6MNO", "7PQRS", "8TUV",
      "9WXYZ"
  };


  public static void main(String[] args) {
    String str = "22 5555 22 666 00 88 888 7777 4444 666 44";
    System.out.println(transfer(str));//ALAN TURING
  }

  public static StringBuilder transfer(String string) {
    StringBuilder sb = new StringBuilder();
    //通过空格分隔
    String[] split = string.split(" ");

    for (String s : split) {
      int len = s.length();
      int num = Integer.parseInt(s.substring(0, 1));

      //i如果为0，则是最后一个字符即 len-1
      //如果不为0，即为i-1
      int i = len % input[num].length();
      sb.append(input[num].charAt(i == 0 ? len - 1 : i - 1));
    }
    return sb;
  }

}
