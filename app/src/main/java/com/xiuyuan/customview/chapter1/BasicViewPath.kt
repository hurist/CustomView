package com.xiuyuan.customview.chapter1

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class BasicViewPath @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val paint = Paint()
        paint.color = Color.RED
        paint.strokeWidth = 10f
        paint.style = Paint.Style.STROKE

        // 画直线
        val pathLine = Path()

        // moveTo 只会移动下一次的起，不会和之前的点连起来
        pathLine.moveTo(100f, 100f)
        pathLine.lineTo(200f, 100f)
        canvas.drawPath(pathLine, paint)

        pathLine.lineTo(200f, 200f)
        // close 可以将起点和终点连接起来，注意中间不能断开
        pathLine.close()
        canvas.drawPath(pathLine, paint)

        // 画弧线
        val pathArc = Path()
        pathArc.moveTo(100f, 300f)
        val archRect = RectF(100f, 300f, 200f, 400f)
        pathArc.arcTo(
            archRect,  // 生成弧线的椭圆所在的矩形，画弧线本质上是再椭圆上截取一条线
            0f, // 起始角度，0度为水平向右
            90f, // 扫过的角度
            true // 是否将起点强制移动到弧线上，false的话弧线会和圆心相连
        )
        canvas.drawPath(pathArc, paint)

        paint.color = Color.BLUE
        // 也可以直接用Canvas画
        canvas.drawArc(archRect, 180f, 90f, false, paint)

    }
}