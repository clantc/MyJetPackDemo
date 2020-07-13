package com.clant.jetpackdemo.algorithm;

/**
 * 插入排序
 */

public class InsertionS {
    int[] listArr = {5, 8, 2, 9, 7, 1, 3, 4, 0, 6};
    int temp = 0;
//    int[] listArr = {8, 5, 2, 1};

    public void insertionSort() {
        int temp = 0;
        for (int i = 1; i < listArr.length; i++) {
            temp = listArr[i];
            for (int j = i; j > 0 && listArr[j] < listArr[j - 1]; j--) {
                listArr[j] = listArr[j - 1];
                listArr[j - 1] = temp;
            }
        }

        System.out.println("\njava实现，插入排序后");
        for (int i = 0; i < listArr.length; i++) {
            System.out.print(listArr[i] + ", ");
        }
    }

}
