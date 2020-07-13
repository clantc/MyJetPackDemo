package com.clant.jetpackdemo.algorithm;

public class BubbleS {

    int[] listArr = {5, 8, 2, 7, 9, 1, 3, 4, 0, 6};

    public void bubbleSort() {
        for (int j = listArr.length - 1; j > 0; j--) {
            for (int i = 0; i < j; i++) {
                if (listArr[i] > listArr[i + 1]) {
                    int temp = listArr[i + 1];
                    listArr[i + 1] = listArr[i];
                    listArr[i] = temp;
                }
            }
        }
        System.out.println("java执行，冒泡排序");
        for (int i = 0; i < listArr.length; i++) {
            System.out.print(listArr[i] + ",");
        }
    }

}
