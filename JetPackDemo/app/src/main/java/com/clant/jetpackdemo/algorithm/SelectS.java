package com.clant.jetpackdemo.algorithm;

public class SelectS {
    int[] listArr = {5, 7, 2, 9, 1, 8, 3, 6, 0, 4};

    public void selectSort() {
        for (int i = 0; i < listArr.length; i++) {
            int temp = i;
            for (int j = i + 1; j < listArr.length; j++) {
                if (listArr[temp] > listArr[j]) {
                    temp = j;
                }
            }
            int tempValue = listArr[temp];
            listArr[temp] = listArr[i];
            listArr[i] = tempValue;
        }
        System.out.println("\njava实现，选择排序后");
        //java实现
        for (int k = 0; k < listArr.length; k++) {
            System.out.print(listArr[k] + ",");
        }
    }

    public void swap() {

    }

}
