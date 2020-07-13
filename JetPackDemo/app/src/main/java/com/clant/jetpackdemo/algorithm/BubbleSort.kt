package com.clant.jetpackdemo.algorithm

/**
 * 核心思想：
 * 1.从小到大排序
 * 2.两个两个比较，从0和1开始比较
 * 3.把最大的数字放到后面
 * 4.嵌套循环外层控制最后放到第几位，内层控制比较交换
 */

fun main(args: Array<String>) {
    var listArr = intArrayOf(5, 8, 2, 7, 9, 1, 3, 4, 0, 6)
    for (j in listArr.lastIndex - 1 downTo 0) {
        for (i in 0..j) {
            if (listArr[i] > listArr[i + 1]) {
                val temp = listArr[i + 1]
                listArr[i + 1] = listArr[i]
                listArr[i] = temp
            }
        }
    }
    println("kotlin执行，冒泡排序")
    for (a in listArr) {
        print("$a, ")
    }

    println()
    var bubbleS = BubbleS()
    bubbleS.bubbleSort()
}