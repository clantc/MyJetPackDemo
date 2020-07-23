package com.clant.jetpackdemo.view_pkg

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.clant.jetpackdemo.utils.ViewUtil

class ViewPath : View {
    private var mPaint: Paint
    private var mPath: Path
    private var mRect: RectF
    private var mChangeValue: Float = 0.0f

    private var mPointCircle_1: Point;
    private var mCircleR = ViewUtil.dp2px(30)
    private var mPointCircle_2: Point;

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        mPaint = Paint()
        mPaint.strokeWidth = ViewUtil.dp2px(1)
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.STROKE
        mPaint.color = Color.BLUE

        mPath = Path()
        mRect = RectF()

        var animator = ValueAnimator.ofFloat(0f, 1f)
        animator.setRepeatCount(ValueAnimator.INFINITE)
        animator.addUpdateListener {
            mChangeValue = it.animatedValue as Float
            invalidate()
            Log.i(ViewSpeed.ycq, "$mChangeValue")
        }
        animator.repeatMode = ValueAnimator.REVERSE
        animator.setDuration(2000)
        animator.start()
        mPointCircle_1 = Point()
        mPointCircle_2 = Point()

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPoint(mPointCircle_1.x.toFloat(), mPointCircle_1.y.toFloat(), mPaint)
        canvas.drawCircle(mPointCircle_1.x.toFloat(), mPointCircle_1.y.toFloat(), mCircleR, mPaint)
        canvas.drawPoint(mPointCircle_2.x.toFloat(), mPointCircle_2.y.toFloat(), mPaint)
        canvas.drawCircle(mPointCircle_2.x.toFloat(), mPointCircle_2.y.toFloat(), mCircleR, mPaint)
        canvas.drawLine(
            mPointCircle_1.x.toFloat(),
            mPointCircle_1.y.toFloat(), mPointCircle_2.x.toFloat(),
            mPointCircle_2.y.toFloat(), mPaint
        )

    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                mPointCircle_1.set(event.x.toInt(), event.y.toInt())
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                mPointCircle_2.set(event.x.toInt(), event.y.toInt())
            }
            MotionEvent.ACTION_UP -> {
            }
            else -> {
            }
        }
        return true
    }


}