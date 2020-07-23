package com.clant.jetpackdemo.view_pkg

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.OverScroller
import kotlin.math.roundToInt

class ViewFlow : ViewGroup {

    companion object {
        val ycq = "ycq"
    }

    lateinit var viewList: ArrayList<View>
    lateinit var viewLineList: ArrayList<ArrayList<View>>

    var currentHeight = 0

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr

    ) {
        setWillNotDraw(false)
        mScroller = OverScroller(context)
//        mScroller.forceFinished(true)
    }

    private var viewHeight = 0
    private var screenHeight = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val modeWidth = MeasureSpec.getMode(widthMeasureSpec)
        val modeHeight = MeasureSpec.getMode(heightMeasureSpec)
        val sizeWidth = MeasureSpec.getSize(widthMeasureSpec)
        screenHeight = MeasureSpec.getSize(heightMeasureSpec)
        viewList = ArrayList()
        viewLineList = ArrayList()
        var widthLine = 0
        viewHeight = 0
        currentHeight = 0
        for (viewCount in 0 until childCount) {
            var viewChild = getChildAt(viewCount)
            var lp = viewChild.layoutParams as MarginLayoutParams
            measureChild(viewChild, widthMeasureSpec, heightMeasureSpec)
            if (widthLine + viewChild.measuredWidth > sizeWidth) {
                viewLineList.add(viewList)
//                viewHeightList.add(currentHeight)
                viewList = ArrayList()
                viewHeight += currentHeight
                currentHeight = 0
                widthLine = 0
            }
            widthLine += viewChild.measuredWidth
            currentHeight = Math.max(currentHeight, viewChild.measuredHeight)
            viewList.add(viewChild)
        }
        viewLineList.add(viewList)
        viewHeight += currentHeight
        setMeasuredDimension(widthMeasureSpec, viewHeight)

    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateLayoutParams(p: LayoutParams?): LayoutParams {
        return MarginLayoutParams(p)
    }


    override fun onLayout(p0: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var left = 0
        var top = 0
        var right = 0
        var bottom = 0
        for (viewList in viewLineList) {
            left = 0
            currentHeight = 0
            for (view in viewList) {
                var lp = view.layoutParams as MarginLayoutParams
                right = left + view.measuredWidth
                currentHeight = Math.max(currentHeight, view.measuredHeight)
                bottom = top + currentHeight
                view.layout(left, top, right, bottom)
                left = right
            }
            top = bottom
        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    private var mScroller: OverScroller
    private var lastY = 0

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        awakenScrollBars()
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (!mScroller.isFinished) {
                    mScroller.abortAnimation()
                }
                lastY = event.y.toInt()
            }
            MotionEvent.ACTION_MOVE -> {
                Log.i(ycq, "move>>>>||${scrollY}")
                mScroller.startScroll(0, mScroller.finalY, 0, (lastY - event.y).toInt());
                invalidate();
                lastY = event.y.toInt()

            }
            MotionEvent.ACTION_UP -> {
                if (scrollY <= 0) {
                    scrollTo(0, 1)
                    invalidate()
                }
            }
            MotionEvent.ACTION_CANCEL -> {
            }
            else -> {
            }
        }
        return true
    }

    override fun computeScroll() {
        super.computeScroll()
        if (mScroller.computeScrollOffset()) {
//            Log.i(ycq, ">>>>||${mScroller.currY}")
            scrollTo(0, mScroller.currY)
            postInvalidate()
        }
    }

    override fun computeVerticalScrollRange(): Int {
        //当前自定义view的高度
        return height
    }

    override fun computeVerticalScrollExtent(): Int {
        //可以理解成scrollbar进度条的长度
        return 100
    }

    override fun computeVerticalScrollOffset(): Int {
        //根据屏幕显示的高度，与view实际的高度，计算显示的位置的比例，然后减去进度条的长度
        //（减去进度条的长度的目的是防止进度条到底部时候全部被隐藏）
        return (scrollY / (viewHeight - screenHeight).toFloat() * screenHeight).roundToInt() - 100
    }

//    private var mDownX = 0f;
//    private var mDownY = 0f;
//    private var mMoveY = 0;
//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        when (event?.action) {
//            MotionEvent.ACTION_DOWN -> {
//                mDownX = event.x
//                mDownY = event.y
//            }
//            MotionEvent.ACTION_MOVE -> {
////                scrollBy(0, ((mDownY - event.y) / 10).toInt())
//                scrollTo(0, mMoveY + ((mDownY - event.y) / 10).toInt())
//                mMoveY = scrollY
//            }
//        }
//        return true
//    }


}


fun main() {
    println(452 / 2631.toFloat())
}


























