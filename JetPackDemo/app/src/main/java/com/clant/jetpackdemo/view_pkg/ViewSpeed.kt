package com.clant.jetpackdemo.view_pkg

import android.R.attr.path
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.os.Build.VERSION_CODES.O
import android.util.AttributeSet
import android.util.Log
import android.view.View


class ViewSpeed : View {

    companion object {
        val ycq = "ycq"
    }

    private var mRect: RectF
    private var mPaint: Paint
    private var mPaintChange: Paint
    private var mPaintText: Paint
    private var mRadius = 200f
    private lateinit var mPathUse: Path
    private var mChangeValue: Float = 0f

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        mPaint = Paint()
        mPaint.strokeWidth = 20f
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.STROKE
        mPaint.color = Color.BLUE

        mPaintChange = Paint()
        mPaintChange.strokeWidth = 20f
        mPaintChange.strokeCap = Paint.Cap.ROUND
        mPaintChange.isAntiAlias = true
        mPaintChange.style = Paint.Style.STROKE
        mPaintChange.color = Color.RED

        mPaintText = Paint()
        mPaintText.textSize = 50f
        mPaintText.isAntiAlias = true
        mPaintText.style = Paint.Style.STROKE
        mPaintText.color = Color.GRAY



        mRect = RectF()
        mRect.left = -mRadius
        mRect.top = -mRadius
        mRect.right = mRadius
        mRect.bottom = mRadius

        var animator = ValueAnimator.ofFloat(0f, 1f)
        animator.setRepeatCount(ValueAnimator.INFINITE)
        animator.addUpdateListener {
            mChangeValue = it.animatedValue as Float
            invalidate()
            Log.i(ycq, "$mChangeValue")
        }
//        animator.repeatCount = 0
        animator.repeatMode = ValueAnimator.REVERSE
        animator.setDuration(2000)
        animator.start()

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        setMeasuredDimension(500, 500)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate(250f, 250f)
        var mPath = Path()
        mPath.addArc(mRect, 135f, 270f)
        mPathUse = Path()
        var mPathMeasure = PathMeasure(mPath, false)

        canvas.drawArc(mRect, 135f, 270f, false, mPaint)


        mPathMeasure.getSegment(-1f, mPathMeasure.length * mChangeValue, mPathUse, true)
        canvas.drawPath(mPathUse, mPaintChange)
        var percent: Int = (mChangeValue * 10000).toInt()
        canvas.drawText(percent.toString(), -25f, 25f, mPaintText)

    }
}