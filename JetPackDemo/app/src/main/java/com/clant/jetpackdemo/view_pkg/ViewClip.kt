package com.clant.jetpackdemo.view_pkg

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

class ViewClip : AppCompatTextView {
    private var mPaint: Paint
    private var mPaint2: Paint
    private var mRect: RectF
    private var mRect2: RectF
    private var mChangeValue: Float = 0f
    private var txtShow = "显示内容helloworld"
    private var txtRect: Rect

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        setBackgroundColor(Color.WHITE)
        mPaint = Paint()
        mPaint.strokeCap = Paint.Cap.BUTT
        mPaint.strokeWidth = dp2px(1).toFloat()
        mPaint.isAntiAlias = true
        mPaint.isDither = true
        mPaint.color = Color.CYAN
        mPaint.textSize = dp2px(30)

        mPaint2 = Paint()
        mPaint2.strokeCap = Paint.Cap.BUTT
        mPaint2.strokeWidth = dp2px(1).toFloat()
        mPaint2.isAntiAlias = true
        mPaint2.isDither = true
        mPaint2.color = Color.CYAN
        mPaint2.textSize = dp2px(30)

        mRect = RectF()
        mRect2 = RectF()

        var animator = ValueAnimator.ofFloat(0f, 1f)
        animator.setRepeatCount(ValueAnimator.INFINITE)
        animator.addUpdateListener {
            mChangeValue = it.animatedValue as Float
            invalidate()
            Log.i(ViewSpeed.ycq, "$mChangeValue")
        }
        animator.repeatMode = ValueAnimator.REVERSE
        animator.setDuration(5000)
        animator.start()
        txtRect = Rect()
        mPaint.getTextBounds(txtShow, 0, txtShow.length, txtRect)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mRect.left = txtRect.right * mChangeValue + dp2px(60)
        mRect.top = 0f
//        mRect.right = txtRect.right * (1 - mChangeValue)
        mRect.right = txtRect.right * mChangeValue
        mRect.bottom = dp2px(50) + baseline.toFloat()



        mPaint2.color = Color.BLUE
        canvas.drawText(txtShow, 0f, dp2px(40) + baseline.toFloat(), mPaint2)

        mPaint.color = Color.RED
        canvas.clipRect(mRect)
        canvas.drawColor(Color.GRAY)
        canvas.drawText("灰色部分应该显示点啥东西啊", 0f, dp2px(40) + baseline.toFloat(), mPaint)


    }

    fun dp2px(value: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            context.getResources().getDisplayMetrics()
        )
    }

}