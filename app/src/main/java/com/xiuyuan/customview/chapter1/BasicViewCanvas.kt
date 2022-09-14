package com.xiuyuan.customview.chapter1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class BasicViewCanvas @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //canvas.drawColor(0xff0000ff.toInt())
        //canvas.drawRGB(255,0,0)
        //canvas.drawRGB(0x00, 0xFF, 0x00)
        //canvas.drawARGB(0x91, 0xFF, 0x00, 0x00)

        val paint = Paint()
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL_AND_STROKE // style跟线条没什么关系，不会有区别
        paint.strokeWidth = 50f

        // 绘制线条
        canvas.drawLine(100f, 100f, 200f, 200f, paint)
        // 绘制点
        canvas.drawPoint(10f, 10f, paint)

    }
}