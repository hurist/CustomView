package com.xiuyuan.customview.chapter1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Region
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorRes

/**
 * Canvas:
 * Canvas可以理解成画布，它并不等于当前屏幕，画布的左上角默认是位于View的左上角，大小和View一样大。
 * 实际上绘制的过程是在画布上进行的，最后再把画布绘制到屏幕上。可以对画布进行平移、缩放、旋转、错切、裁剪等操作。
 * 这些操作也会影响到将canvas绘制到屏幕后的效果
 *
 * 每次调用Canvas的drawXXX()方法时，都会产生一个新的图层，这些图层会按照绘制的顺序依次叠加在一起。
 * 如果在绘制过程中调用了Canvas的类似于translate()的方法，后续产生的新图层都会按变换后的坐标系进行绘制
 * Canvas中绘制的超出View的范围的内容不会显示出来
 */
class BasicViewCanvas @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save() //保存当前画布状态
        canvas.drawColor(Color.GRAY)

        val paint = Paint().apply {
            strokeWidth = 5f
            color = Color.BLUE
            style = Paint.Style.STROKE
        }

        canvas.drawRect(0f, 0f, 200f, 200f, paint)
        canvas.translate(100f, 100f)
        paint.color = Color.RED
        // 左上角的坐标是(100,100)而不是(0,0)， 画布移动了100，导致绘制的矩形左上角坐标相对于View也移动了100
        // 对画布的操作不会影响到之前已经绘制的内容, 但是会影响到之后绘制的内容
        canvas.drawRect(0f, 0f, 200f, 200f, paint)
        paint.color = Color.GREEN
        canvas.drawRect(10f, 10f, 210f, 210f, paint)


        setLayerType(LAYER_TYPE_SOFTWARE, null) //裁剪需要关闭硬件加速
        // Clip裁剪画布。可以让canvas与指定的区域做交，并，补等操作，用来获得一个新的形状的画布clipRect
        // 这个方法废弃了，在28以后只有DIFFERENCE和INTERSECT有效，其他的都失效了
        // 只推荐使用clipRect(交集) 和 clipOutRect(补集)
        canvas.clipRect(0f, 0f, 100f, 100f, Region.Op.INTERSECT) // 不指定op默认取交集
        paint.color = Color.YELLOW
        canvas.drawColor(Color.RED)

        canvas.restore() //恢复画布状态
        paint.color = Color.MAGENTA
        canvas.drawRect(10f, 10f, 210f, 210f, paint) // 画布恢复到了初始状态，所以这个矩形的左上角坐标是(10,10)

    }
}