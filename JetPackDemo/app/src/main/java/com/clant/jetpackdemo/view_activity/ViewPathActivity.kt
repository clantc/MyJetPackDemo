package com.clant.jetpackdemo.view_activity

import android.os.Bundle
import com.clant.jetpackdemo.Base.BaseActivity
import com.clant.jetpackdemo.databinding.ActivityViewPathBinding

class ViewPathActivity : BaseActivity() {

    lateinit var viewBinding: ActivityViewPathBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityViewPathBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

}