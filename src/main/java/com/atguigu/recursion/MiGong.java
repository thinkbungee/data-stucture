package com.atguigu.recursion;

/**
 * 迷宫回溯问题
 */
public class MiGong {

    public static void main(String[] args) {
        //用二维数组模拟迷宫
        int[][] map = new int[8][7];
        //使用1代表墙
        //把第一行 和最后一行 设置成墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //把第一列和最后一列设置成强
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //设置障碍
        map[3][1] = 1;
        map[3][2] = 1;

        //输出地图
        System.out.println("该地图为：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    /**
     * 使用递归回溯给小球找通路
     * （i，j）位置坐标
     *
     * @param map 地图
     * @param i,j 从哪个位置开始走
     * @return 如果找到通路，返回true，否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {

        return false;
    }
}
