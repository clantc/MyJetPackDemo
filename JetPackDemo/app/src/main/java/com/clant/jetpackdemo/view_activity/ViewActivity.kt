package com.clant.jetpackdemo.view_activity

import android.os.Bundle
import android.view.MotionEvent
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.clant.jetpackdemo.Base.BaseActivity
import com.clant.jetpackdemo.databinding.ViewActivityBind

class ViewActivity : BaseActivity() {

    private lateinit var viewBinding: ViewActivityBind
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        viewBinding = ViewActivityBind.inflate(layoutInflater)
        setContentView(viewBinding.root)

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

}