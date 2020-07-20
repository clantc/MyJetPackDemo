package com.clant.jetpackdemo.view_pkg

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import java.util.*

class ViewFivePoint : View {
    private var mPaint: Paint

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        mPaint = Paint()
        mPaint.strokeCap = Paint.Cap.BUTT
        mPaint.strokeWidth = dp2px(3)
        mPaint.isAntiAlias = true
        mPaint.isDither = true
        mPaint.color = Color.BLACK
    }

    override fun onDraw(canvas: Canvas) {


        if (isMove) {
            for (i in 0..width step 20) {
                mPaint.color = Color.RED
                canvas.drawLine(i.toFloat(), 0.toFloat(), moveX.toFloat(), moveY.toFloat(), mPaint)
                mPaint.color = Color.BLUE
                canvas.drawLine(
                    i.toFloat(),
                    height.toFloat(),
                    moveX.toFloat(),
                    moveY.toFloat(),
                    mPaint
                )
            }

            for (k in 0..height step 20) {
                mPaint.color = Color.GRAY
                canvas.drawLine(
                    0f,
                    k.toFloat(),
                    moveX.toFloat(),
                    moveY.toFloat(),
                    mPaint
                )
                mPaint.color = Color.DKGRAY
                canvas.drawLine(
                    width.toFloat(),
                    k.toFloat(),
                    moveX.toFloat(),
                    moveY.toFloat(),
                    mPaint
                )
            }
            mPaint.color = Color.BLUE
            mPaint.style = Paint.Style.STROKE
            canvas.drawCircle(moveX.toFloat(), moveY.toFloat(), dp2px(40), mPaint)
        } else {
            mPaint.color = Color.BLUE
            canvas.drawLine(100f, 500f, 700f, 500f, mPaint);
            canvas.translate(100f, 500f);
            canvas.rotate(36f);
            mPaint.color = Color.GRAY
            canvas.drawLine(0f, 0f, 600f, 0f, mPaint);
            canvas.translate(600f, 0f);
            canvas.rotate(36f);
            mPaint.color = Color.RED
            canvas.drawLine(0f, 0f, -600f, 0f, mPaint);
            canvas.translate(-600f, 0f);
            canvas.rotate(36f);
            mPaint.color = Color.YELLOW
            canvas.drawLine(0f, 0f, 600f, 0f, mPaint);
            canvas.translate(600f, 0f);
            canvas.rotate(36f);
            mPaint.color = Color.GREEN
            canvas.drawLine(0f, 0f, -600f, 0f, mPaint);
        }


    }

    fun dp2px(dp: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        )
    }

    private var moveX = 0
    private var moveY = 0

    private var isMove = false

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var downX = 0;

        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                moveX = x.toInt()
            }
            MotionEvent.ACTION_UP -> {
                isMove = false
            }
            MotionEvent.ACTION_MOVE -> {
                moveX = event.getX().toInt()
                moveY = event.getY().toInt()
                isMove = true
                invalidate()
            }
            MotionEvent.ACTION_CANCEL -> {
                isMove = false
            }
            else -> {
            }

        }
        return true
    }


}