package com.atguigu.sparseArray;

/**
 * 稀疏数组demo
 * <p>
 */
public class SparseArray {

    //二维数组转换为稀疏数组案例
    public static void main(String[] args) {
        //创建一个原始的二维数组  11*11
        //0：表示没有棋子  1：表示黑子 2:表示白字
        int chessArr[][] = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组如下：");
        for (int[] row : chessArr) {
            //得到每一行
            for (int data : row) {
                //具体的元素
                System.out.printf("%d\t", data);
            }
            //每打印一行 换行
            System.out.println();
        }
        // 将二维数组转为稀疏数组
        //1.遍历二维数组，得到非0的个数
        int sum = 0;
        for (int[] row : chessArr) {
            for (int data : row) {
                if (data != 0) {
                    sum++;
                }
            }
        }
        System.out.println("该二维数组有效数值是：" + sum);
        //2.创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        //给稀疏数组赋值

        //第一行为该原始二维数组的 行数、列数、总的不为0的个数
        sparseArr[0][0] = chessArr.length;
        sparseArr[0][1] = chessArr[0].length;
        sparseArr[0][2] = sum;

        //其他值
        int sparseRow = 1;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    sparseArr[sparseRow][0] = i;//记录行
                    sparseArr[sparseRow][1] = j;//记录列
                    sparseArr[sparseRow][2] = chessArr[i][j];//记录数值
                    sparseRow++;
                }
            }
        }

        //遍历下稀疏数组 是否符合要求
        System.out.println("得到的稀疏数组如下：");
        for (int[] row : sparseArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

    }

}
