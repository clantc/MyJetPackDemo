package com.clant.jetpackdemo.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class ApplicationObserver : LifecycleObserver {

    /**
     * 切换到后台：onPause>onStop
     * 切换到前台：onStart>onResume
     * 杀死任务：没有回调，不会调用onDestroy
     * 第一次启动任务：onCreat>onstart>onResume(只有第一次启动时调用onCreate)
     */

    //onCreate 在整个应用的声明周期中只调用一次
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.i("fxy", "ApplicationObserver---->>Lifecycle.Event.ON_CREATE")
    }

    //应用程序切换到前台时调用
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.i("fxy", "ApplicationObserver---->>Lifecycle.Event.ON_START")
    }

    //应用程序切换到前台时调用
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.i("fxy", "ApplicationObserver---->>Lifecycle.Event.ON_RESUME")
    }

    //应用程序退出到后台时调用
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.i("fxy", "ApplicationObserver---->>Lifecycle.Event.ON_PAUSE")
    }

    //应用程序退出到后台时调用
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.i("fxy", "ApplicationObserver---->>Lifecycle.Event.ON_STOP")
    }

    //永远不会被调用到，系统不会分发调用ON_DESTROY事件
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Log.i("fxy", "ApplicationObserver---->>Lifecycle.Event.ON_DESTROY")
    }


}