package com.clant.jetpackdemo.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


fun main() {
    GlobalScope.launch {
        delay(1000)
        println("-----------")
    }
    println("------1")
    Thread.sleep(500)
    println("------2")

}
