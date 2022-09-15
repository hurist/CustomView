package com.xiuyuan.customview.chapter1

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.xiuyuan.customview.R

class BasicViewRegion @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val region = Region()
        val region1 = Region(0, 0, 100, 100)
        val region2 = Region(Rect(0, 0, 100, 100))
        val region3 = Region(region)

        region.setEmpty() // 设置为空
        // region.set() //用新的区域替换旧的区域

        drawRegion(canvas, region1)

        region.setPath(Path(), region1) // 根据Path和region的交集创建新的区域
        val ovalPath = Path()
        ovalPath.addOval(RectF(150f, 0f, 250f, 200f), Path.Direction.CCW)
        val clipRegion = Region(Rect(150, 0, 250, 100))
        val ovalRegion = Region().apply {
            setPath(ovalPath, clipRegion)
        }
        drawRegion(canvas, ovalRegion)

        region.union(Rect(0, 0, 100, 100)) // 与执行矩形取并集
        val rRegion = Region(300, 0, 400, 100)
        rRegion.union(Rect(300, 100, 350, 150))
        drawRegion(canvas, rRegion)

        //region.op() // 区域操作函数
        // INTERSECT
        // DIFFERENCE
        // UNION
        // XOR
        // REVERSE_DIFFERENCE
        // REPLACE
        val firstRegion = Region(0, 600, 600, 800)
        val secondRegion = Region(200, 400, 400, 1000)
        //firstRegion.op(secondRegion, Region.Op.DIFFERENCE) // 取firstRegion中不与secondRegion相交的部分，补集
        //firstRegion.op(secondRegion, Region.Op.INTERSECT) // 取firstRegion和secondRegion相交的部分，交集
        //firstRegion.op(secondRegion, Region.Op.REPLACE) // 用secondRegion替换firstRegion
        //firstRegion.op(secondRegion, Region.Op.REVERSE_DIFFERENCE) // 取secondRegion中不与firstRegion相交的部分，反转补集
        //firstRegion.op(secondRegion, Region.Op.UNION) // 取firstRegion和secondRegion的并集
        //firstRegion.op(secondRegion, Region.Op.XOR) // 取firstRegion和secondRegion的不同部分，异或

        val resultRegion = Region().apply {
            op(firstRegion, secondRegion, Region.Op.XOR) // 这种方法可以将结果返回到resultRegion中，不会改变firstRegion和secondRegion的值
        }
        drawRegion(canvas, resultRegion)


    }

    private fun drawRegion(canvas: Canvas, region: Region) {
        val iterator = RegionIterator(region)
        val rect = Rect()
        while (iterator.next(rect)) {
            canvas.drawRect(rect, Paint().apply {
                color = Color.RED
            })
        }
    }
}