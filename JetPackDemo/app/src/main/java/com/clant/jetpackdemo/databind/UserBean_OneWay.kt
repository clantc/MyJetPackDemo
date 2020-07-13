package com.clant.jetpackdemo.databind

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import com.clant.jetpackdemo.BR

class UserBean_OneWay : BaseObservable() {
    /**
     * BaseObservable存在实现序列化的问题，需要考虑使用
     */

    @Bindable
    var name: String = "deName"
        set(value) {
            field = value
            notifyChange()
        }

    //实现双向绑定
    var age: ObservableField<Int> = ObservableField(999)

    var sex: ObservableField<String> = ObservableField("deSex")
}