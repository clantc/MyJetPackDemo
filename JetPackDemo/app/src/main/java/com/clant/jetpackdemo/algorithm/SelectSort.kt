package com.clant.jetpackdemo.algorithm


fun main(args: Array<String>) {
    var selectArr = intArrayOf(5, 7, 2, 9, 1, 8, 3, 6, 0, 4)
    var targetIndex = 0
    print("原数组：\n")
    for (i in selectArr) {
        print("$i,")
    }
    println("\nkotlin实现，选择排序后")

    for (index in selectArr.indices) {
        targetIndex = index
        for (sel in index + 1..selectArr.lastIndex) {
            if (selectArr[targetIndex] > selectArr[sel]) {
                targetIndex = sel
            }
        }
        var temp = selectArr[targetIndex]
        selectArr[targetIndex] = selectArr[index]
        selectArr[index] = temp
    }

    for (i in selectArr) {
        print("$i,")
    }
    //java实现
    var selectS = SelectS()
    selectS.selectSort()

}


