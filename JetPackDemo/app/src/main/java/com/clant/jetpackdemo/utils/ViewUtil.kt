package com.clant.jetpackdemo.utils

import android.content.Context
import android.util.TypedValue
import com.clant.jetpackdemo.Base.ApplicationDemo.Companion.context
import java.lang.reflect.TypeVariable

class ViewUtil {

    companion object {
        @JvmStatic
        fun dp2px(dp: Int): Float {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                context.resources.displayMetrics
            )
        }

        fun sp2px(sp: Int): Float {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                sp.toFloat(),
                context.resources.displayMetrics
            )
        }

    }

}