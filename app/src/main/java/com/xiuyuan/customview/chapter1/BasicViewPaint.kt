package com.xiuyuan.customview.chapter1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class BasicViewPaint @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val paint = Paint()
        /*
        颜色：颜色值由红绿蓝三原色组成，每个原色的取值范围是0~255，共计256种取值，因此颜色值的取值范围是0x00000000~0xFFFFFFFF。
        其中前面两位代表透明度，00代表完全透明，FF代表完全不透明。第三位和第四位代表红色, 00表示红色完全不可见，FF表示完全可见，
        如果其他颜色为00，则当前就为红色，第五位和第六位代表绿色，第七位和第八位代表蓝色。
        PS：直接用16进制表示时，透明度不能省略
         */
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 50f
        canvas.drawCircle(190f, 200f, 100f, paint)

        paint.color = 0xFF00FF00.toInt()
        canvas.drawCircle(190f, 200f, 150f, paint)
    }
}