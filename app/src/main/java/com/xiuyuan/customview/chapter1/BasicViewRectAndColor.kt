package com.xiuyuan.customview.chapter1

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class BasicViewRectAndColor @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val rect = Rect(100, 100, 200, 200)
        val rectF = RectF(100f, 300f, 200f, 400f)

        val paint = Paint()
        paint.color = 0x88ff0000.toInt()
        paint.strokeWidth = 20f

        paint.style = Paint.Style.FILL
        canvas.drawRect(rect, paint)

        paint.style = Paint.Style.STROKE
        canvas.drawRect(rectF, paint)

        /**
         * 原理参照源码，Alpha值向左移动24位，Red值向左移动16位，Green值向左移动8位，Blue值不变,
         * 然后用或运算符将四个值合并成一个32位的int值
         * 源码：
         *
         *   public static int argb(
         *       @IntRange(from = 0, to = 255) int alpha,
         *       @IntRange(from = 0, to = 255) int red,
         *       @IntRange(from = 0, to = 255) int green,
         *       @IntRange(from = 0, to = 255) int blue) {
         *       return (alpha << 24) | (red << 16) | (green << 8) | blue;
         *   }
         */
        Color.argb(255, 255, 0, 0)

        /**
         * 取颜色值则相反，比如取一个颜色的Red, 则将这个颜色值向右移动16位，然后与0xff进行与运算
         */
        Color.red(Color.RED)
    }

}