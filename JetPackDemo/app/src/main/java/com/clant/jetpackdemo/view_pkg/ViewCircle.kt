package com.clant.jetpackdemo.view_pkg

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ViewCircle : ViewGroup {

    companion object {
        val TAG = "fxy"
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
        heightList = ArrayList()
        lineViewCountList = ArrayList()
        var currentHeight = 0
        var currentWidth = 0
        var currentCount = 0;
        for (index in 0..childCount - 1) {

        }
        Log.i(TAG, "---------------onMeasure")
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(p0: Boolean, l: Int, t: Int, r: Int, b: Int) {
//        Log.i(TAG, "${viewList.size} + |||")
        var left = 0
        var top = 0
        var right: Int
        var bottom: Int
        Log.i(TAG, "---------------onlayout")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

//        MeasureSpec.makeMeasureSpec()
        Log.i(TAG, "---------------onDraw")
    }

}