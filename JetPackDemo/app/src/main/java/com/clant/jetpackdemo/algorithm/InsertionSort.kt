package com.clant.jetpackdemo.algorithm

//插入排序
/**
 * 核心思想：从小到大排序
 * 有点类似倒序的冒泡，冒泡为从前到后交换，插入排序为从后向前比较交换
 * 1.以此取数组中节点向前比较，
 * 2.把每个节点的数作为缓存
 * 3.如果当前节点小于前面节点，则把数组依次移位，一直到缓存数值大于要比较的，然后把要比较的再赋值
 * 内层移位运算，外层做缓存
 */

fun main(args: Array<String>) {
    var listArr = intArrayOf(5, 8, 2, 9, 7, 1, 3, 4, 0, 6)
    var temp = 0;
    for (i in listArr.indices) {
        temp = listArr[i]
        for (j in i downTo 1) {
            if (listArr[j] < listArr[j - 1]) {
                listArr[j] = listArr[j - 1]
                listArr[j - 1] = temp
            }
        }
    }
    println("\nkotlin实现，插入排序后")
    for (i in listArr.indices) {
        print("${listArr[i]},")
    }

    var insertionS = InsertionS()
    insertionS.insertionSort()
}