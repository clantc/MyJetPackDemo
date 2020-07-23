package com.clant.jetpackdemo.view_activity

import android.os.Bundle
import com.clant.jetpackdemo.Base.BaseActivity
import com.clant.jetpackdemo.databinding.ActivityViewFlowBinding

class ViewFlowActivity : BaseActivity() {
    lateinit var binding: ActivityViewFlowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}