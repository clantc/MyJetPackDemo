package com.clant.jetpackdemo.view_pkg

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ViewFlow : ViewGroup {

    companion object {
        val TAG = "ycq"
    }

    lateinit var viewList: ArrayList<View>
    lateinit var lineList: ArrayList<ArrayList<View>>

    lateinit var heightList: ArrayList<Int>
    lateinit var lineViewCountList: ArrayList<Int>

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    init{
        setWillNotDraw(false)
    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val modeWidth = MeasureSpec.getMode(widthMeasureSpec)
        val modeHeight = MeasureSpec.getMode(heightMeasureSpec)
        val sizeWidth = MeasureSpec.getSize(widthMeasureSpec)
        val sizeHeight = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(p0: Boolean, l: Int, t: Int, r: Int, b: Int) {
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

}