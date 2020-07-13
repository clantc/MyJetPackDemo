package com.clant.jetpackdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
//此行为用application作用域的时候需要继承AndroidViewModel并且传入application
//class MainViewModel(application: Application) : AndroidViewModel(application) {


    var mCount: Int = 0

    val viewLiveDataValue: MutableLiveData<String> = MutableLiveData()
        get() {
            return field
        }


}