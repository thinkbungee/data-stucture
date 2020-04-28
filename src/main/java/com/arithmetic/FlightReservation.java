package com.arithmetic;

import java.util.Arrays;

/**
 * 航班预订统计题 LeetCode 1109
 * 这里有n个航班，分别从1到n编号
 * 有一份航班预定表，表中第m条预订记录 bookings[m]=[i,j,k]
 * 表示从i到j的每个航班都预订了k个座位
 * 需要让你返回一个长度为n的数组，按航班编号顺序返回每个航班的预订座位数
 * 1 <= bookings.length <= 20000
 * 1 <= bookings[i][0] <= bookings[i][1] <=n <= 20000
 * 1 <= bookings[i][2] <= 10000
 * Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * Output: [10,55,45,25,25]
 */
public class FlightReservation {

  public static void main(String[] args) {
    int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
    int n = 5;
    int[] foreach = foreach(bookings, n);
    System.out.println(Arrays.toString(foreach));
    int[] ints = corpFlightBookings(bookings, n);
    System.out.println(Arrays.toString(ints));
  }


  /**
   *
   * @param bookings 预订座位的数组
   * @param n 一共多少航班
   * @return 返回每个航班的预订数
   */
  //简单 暴力法 ，遍历每个bookings 然后对于每个区间[i,j]将 相应的k加到数组中
  //时间复杂度 0（m*n） 2层for循环嵌套
  public static int[] foreach(int[][] bookings, int n) {
    int[] result = new int[n];
    for (int[] booking : bookings) {
      int one = booking[0];
      int two = booking[1];
      int order = booking[2];
      //开始加
      for (int j = one; j <= two; j++) {
        result[j - 1] += order;
      }
    }
    return result;
  }

  //时间复杂度0（n）一层for循环

  /**
   * 0(m*n) 怎么将 内层一直重复的 [i,j]之间加上k 变成 0（1），是关键
   *
   * 分析：
   * 设answer[i]表示第i个航班预订的座位数。定义一个差分数组d[],
   * d[i]表示第i个航班与第i-1个航班预订座位的差值，即d[i] = answer[i]-answer[i-1]
   * 这样，每次遍历bookings[i] = [i,j,k]时，只需要将d[i]增加k,d[j+1]减少k即可，因为i到j之间
   * 航班预订数量是没有变化的
   * 最后，计算answer[i] = anser[i-1] + d[i]即可
   * 实例分析：
   * 初始预订数组 answer = [0,0,0,0,0] 差分数组 d = [0,0,0,0,0]
   * bookings[0]= [1,2,10]时，差分数组第一位加10，第3位减10，变成 d = [10,0,-10,0,0]
   * bookings[1]= [2,3,20]时，差分数组第二位加20，第4位减20，变成 d = [10,20,-10,-20,0]
   * bookings[2]= [2,5,25]时，差分数组第二位加25，第6位减25，变成 d = [10,45,-10,-20,0]
   * 最后计算answer的值
   * answer[0] = d[0] = 10
   * answer[1] = d[1] +answer[0] = 45+10 = 55
   * answer[2] = d[2] +answer[1] = -10+55=45
   * ....
   *
   */
  public static int[] corpFlightBookings(int[][] bookings, int n) {
    int[] answer = new int[n];
    for (int[] booking : bookings) {
      answer[booking[0] - 1] += booking[2];
      //防止数组越界
      if (booking[1] < n) {
        answer[booking[1]] -= booking[2];
      }
    }
    for (int i = 0; i < n; i++) {
      if (i != 0) {
        answer[i] += answer[i - 1];
      }
    }
    return answer;
  }
}
