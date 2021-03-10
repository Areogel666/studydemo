package com.lxr.studydemo.algorithm.common;

import org.junit.jupiter.api.Test;

/**
 * @ClassName ArrayAndSort
 * @Description TODO
 * @Author Areogel
 * @Date 2021/3/10 16:50
 * @Version 1.0
 */
public class ArrayAndSort {
    /**
     * 剑指 Offer 04. 二维数组中的查找
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        //暴力；二分法；binaryTree(左下或右上做顶点)
        //以右上做顶点，大于15下移，小于15左移，以此类推 时间复杂度O(m+n)
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int n = matrix.length;
        int m = matrix[0].length;
        int top = 0;
        int left = m - 1;
        while (top >= n || left < 0) {
            int current = matrix[top][left];
            if (target == current) {
                return true;
            } else if (target > current) {
                top++;
            } else if (target < current) {
                left--;
            }
        }
        return false;
    }

    @Test
    public void testFindNumberIn2DArray() {
        int[][] matrix = {{1,   4,  7, 11, 15}
                ,{2,   5,  8, 12, 19}
                ,{3,   6,  9, 16, 22}
                ,{10, 13, 14, 17, 24}
                ,{18, 21, 23, 26, 30}};
        boolean numberIn2DArray = findNumberIn2DArray(matrix, 87);
        System.out.println(numberIn2DArray);
    }
}
