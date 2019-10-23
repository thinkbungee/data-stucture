package com.atguigu.recursion;

/**
 * 迷宫回溯问题
 */
public class Maze {

    public static void main(String[] args) {

        System.out.println("创建一个迷宫(1为墙)：");
        int[][] map = MapOfMaze(8, 7);

        setWay(map, 1, 1);
        System.out.println("小球从map[1][1]的位置出发的通路为：");
        printMaze(map);

    }

    /**
     * 创建大小 i * j 的迷宫
     */
    private static int[][] MapOfMaze(int i, int j) {
        //用二维数组模拟迷宫
        int[][] map = new int[i][j];
        //使用1代表墙
        //把第一行 和最后一行 设置成墙
        for (int m = 0; m < 7; m++) {
            map[0][m] = 1;
            map[7][m] = 1;
        }
        //把第一列和最后一列设置成墙
        for (int n = 0; n < 8; n++) {
            map[n][0] = 1;
            map[n][6] = 1;
        }

        //设置障碍
        map[3][1] = 1;
        map[3][2] = 1;

        printMaze(map);

        return map;
    }

    /**
     * 输出地图
     */
    private static void printMaze(int[][] map) {
        //输出地图
        for (int m = 0; m < 8; m++) {
            for (int n = 0; n < 7; n++) {
                System.out.print(map[m][n] + "   ");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归回溯给小球找通路
     * 说明：
     * 1.（i，j）位置坐标，表示小球从哪个位置开始出发
     * 2.如果小球能到map[6][5]位置，说明通路找到
     * 3.约定：当map[i][j]
     *      为0  表示该点没有走过，
     *      为1  表示有墙
     *      为2  表示通路可以走
     *      为3  表示该点已走过，但走不通
     * 4.在走迷宫时，确定策略：下->右->上->左，如果该点走不通，再回溯
     * @param map 地图
     * @param i,j 从哪个位置开始走
     * @return 如果找到通路，返回true，否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {

        if (map[6][5] == 2) {//通路找到，返回true
            return true;
        } else {
            if (map[i][j] == 0) {
                //如果当前点没有走过
                //按照策略走：下--右--上--左
                map[i][j] = 2;//假定该点能走通

                if (setWay(map, i + 1, j)) {
                    //向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    //向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    //向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    //向左走
                    return true;
                } else {
                    //都走不通，说明此路不通
                    map[i][j] = 3;
                    return false;
                }
            } else {//如果map[i][j]不等于0，可能是1,2,3
                return false;
            }
        }
    }
}
