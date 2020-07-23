/*
 * Copyright 2013 Blaz Solar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.clant.jetpackdemo.view_pkg;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.OverScroller;
import androidx.core.view.ViewConfigurationCompat;
import com.clant.jetpackdemo.utils.ViewUtil;
import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {

    private static final String TAG = "Zero";

    private List<View> lineViews;//每一行的子View
    private List<List<View>> views;//所有的行 一行一行的存储
    private List<Integer> heights;//每一行的高度

    private boolean scrollable = false;
    private int measureHeight;//代表本身的测量高度
    private int realHeight;//表示内容的高度

    private int mTouchSlop;//用来判断是不是一次滑动
    private float mLastY = 0;

    private OverScroller mScroller;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);//获取最小滑动距离
        Log.i(TAG, "FlowLayout: mTouchSlop= " + mTouchSlop);
        mScroller = new OverScroller(context);
    }


    private void init() {
        views = new ArrayList<>();
        lineViews = new ArrayList<>();
        heights = new ArrayList<>();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {//处理滑动
        if (!scrollable) {
            return super.onTouchEvent(event);
        }
        float currY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                mLastY = currY;
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = mLastY - currY;//本次手势滑动了多大距离
                mScroller.startScroll(0, mScroller.getFinalY(), 0, (int) dy);//mCurrY = oldScrollY + dy*scale;
                invalidate();
                mLastY = currY;
                break;
            case MotionEvent.ACTION_UP:
                if (mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0,
                        (realHeight - measureHeight))) {
                    postInvalidateOnAnimation();
                }
                break;
        }
        return true;
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {//mCurrY = oldScrollY + dy*scale;
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        measureHeight = heightSize;
        //记录当前行的宽度和高度
        int lineWidth = 0;// 宽度是当前行子view的宽度之和
        int lineHeight = 0;// 高度是当前行所有子View中高度的最大值

        //整个流式布局的宽度和高度
        int flowlayoutWidth = 0;//所有行中宽度的最大值
        int flowlayoutHeight = 0;// 所以行的高度的累加

        //初始化参数列表
        init();

        //遍历所有的子View，对子View进行测量，分配到具体的行
        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {

            View child = this.getChildAt(i);
            //测量子View 获取到当前子View的测量的宽度/高度
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            //获取到当前子View的测量的宽度/高度
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
//            Log.i(TAG, "onMeasure: childHeight= " + childHeight);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            //看下当前的行的剩余的宽度是否可以容纳下一个子View,
            // 如果放不下，换行 保存当前行的所有子View,累加行高，当前的宽度，高度 置零
            if (lineWidth + childWidth > widthSize) {//换行
                if (lineViews.size() == 1 && lineViews.get(0).getLayoutParams().height == LayoutParams.MATCH_PARENT) {//如果一行只有一个元素
                    lineHeight = (int) ViewUtil.dp2px(150);
                }
                views.add(lineViews);
                lineViews = new ArrayList<>();//创建新的一行
                flowlayoutWidth = Math.max(flowlayoutWidth, lineWidth);
                flowlayoutHeight += lineHeight;
                heights.add(lineHeight);
                lineWidth = 0;
                lineHeight = 0;
            }
            lineViews.add(child);
            lineWidth += childWidth;
            if (lp.height != LayoutParams.MATCH_PARENT) {//暂时先不处理layout_heigth = match_parent
                lineHeight = Math.max(lineHeight, childHeight);
            }
//            Log.i(TAG, "onMeasure: " + lineHeight);

            if (i == childCount - 1) {//最后一行
                flowlayoutHeight += lineHeight;
                flowlayoutWidth = Math.max(flowlayoutWidth, lineWidth);
                heights.add(lineHeight);
                views.add(lineViews);
            }
        }
        //重新测量一次layout_heigth = match_parent
        remeasureChild(widthMeasureSpec, heightMeasureSpec);

        if (heightMode == MeasureSpec.EXACTLY) {
            flowlayoutHeight = Math.max(heightSize, flowlayoutHeight);
        }

        realHeight = flowlayoutHeight;
        scrollable = realHeight > measureHeight;

        //FlowLayout最终宽高
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : flowlayoutWidth, realHeight);


    }

    private void remeasureChild(int widthMeasureSpec, int heightMeasureSpec) {
        int lineSize = views.size();
        for (int i = 0; i < lineSize; i++) {
            int lineHeight = heights.get(i);//每一行行高
//            Log.i(TAG, "remeasureChild: " + lineHeight);
            List<View> lineViews = views.get(i);//每一行的子View
            int size = lineViews.size();

            for (int j = 0; j < size; j++) {
                View child = lineViews.get(j);
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.height == LayoutParams.MATCH_PARENT) {

                    int childWidthSpec = getChildMeasureSpec(widthMeasureSpec, 0, lp.width);
                    int childHeightSpec = getChildMeasureSpec(heightMeasureSpec, 0, lineHeight);
                    child.measure(childWidthSpec, childHeightSpec);

                }
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int lineCount = views.size();

        int currX = 0;
        int currY = 0;

        for (int i = 0; i < lineCount; i++) {//大循环，所有的子View 一行一行的布局
            List<View> lineViews = views.get(i);//取出一行
            int lineHeight = heights.get(i);// 取出这一行的高度值
            //遍历当前行的子View
            int size = lineViews.size();
            for (int j = 0; j < size; j++) {//布局当前行的每一个view
                View child = lineViews.get(j);
                int left = currX;
                int top = currY;
                int right = left + child.getMeasuredWidth();
                int bottom = top + child.getMeasuredHeight();
                child.layout(left, top, right, bottom);
                //确定下一个view的left
                currX += child.getMeasuredWidth();
            }
            currY += lineHeight;
            currX = 0;
        }
    }

}
