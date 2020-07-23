package com.clant.jetpackdemo.Base

import android.app.Application
import android.content.Context
import androidx.lifecycle.ProcessLifecycleOwner
import com.clant.jetpackdemo.lifecycle.ApplicationObserver

class ApplicationDemo : Application() {

    companion object {
        lateinit var context: Context

    }

    override fun onCreate() {
        super.onCreate()
        //ProcessLifecycleOwner监听应用程序的声明周期
        ProcessLifecycleOwner.get().getLifecycle().addObserver(ApplicationObserver());
        context = applicationContext
    }

}