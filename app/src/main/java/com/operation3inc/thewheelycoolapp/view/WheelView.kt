package com.operation3inc.thewheelycoolapp.view

import android.animation.Animator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.core.content.ContextCompat
import com.operation3inc.thewheelycoolapp.R

internal class WheelView : View {
    private var range = RectF()
    private var archPaint: Paint? = null
    private var textPaint: Paint? = null
    private var padding = 0
    private var radius = 0
    private var center = 0
    private var wheelBackground = 0
    private var wheelItems: List<String>? = null
    private var colors: MutableList<Int> = mutableListOf()

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private fun initComponents() {
        //arc paint object
        archPaint = Paint().apply {
            isAntiAlias = true
            isDither = true
        }

        //text paint object
        textPaint = Paint().apply {
            color = ContextCompat.getColor(context, R.color.white)
            isAntiAlias = true
            isDither = true
            textSize = 36f
        }

        //rect rang of the arc
        range = RectF(
            padding.toFloat(), padding.toFloat(), (padding + radius).toFloat(),
            (padding + radius).toFloat()
        )

        // Declare colors
        colors =
            mutableListOf(
                ContextCompat.getColor(context, R.color.pink),
                ContextCompat.getColor(context, R.color.orange),
                ContextCompat.getColor(context, R.color.green),
                ContextCompat.getColor(context, R.color.pink_dark),
                ContextCompat.getColor(context, R.color.orange_dark)
            )
    }

    /**
     * Get the angele of the target
     *
     * @return Number of angle
     */
    private fun getAngleOfIndexTarget(target: Int): Float {
        wheelItems?.size?.let {
            return (360 / it * target).toFloat()
        }
        return 0f
    }

    /**
     * Function to add wheels items
     *
     * @param wheelItems Wheels model item
     */
    fun addWheelItems(wheelItems: List<String>?) {
        this.wheelItems = wheelItems
        invalidate()
    }

    /**
     * Function to draw wheel background
     *
     * @param canvas Canvas of draw
     */
    private fun drawWheelBackground(canvas: Canvas) {
        val backgroundPainter = Paint().apply {
            isAntiAlias = true
            isDither = true
            color = wheelBackground
        }

        canvas.drawCircle(center.toFloat(), center.toFloat(), center.toFloat(), backgroundPainter)
    }

    /**
     * Function to draw text below image
     *
     * @param canvas     Canvas to draw
     * @param tempAngle  Temporary angle
     * @param sweepAngle current index angle
     * @param text       string to show
     */
    private fun drawText(canvas: Canvas, tempAngle: Float, sweepAngle: Float, text: String) {
        if (!wheelItems.isNullOrEmpty()) {
            wheelItems?.size?.let { numberOfItems ->
                val path = Path().apply {
                    addArc(range, tempAngle, sweepAngle)
                }

                textPaint?.let { tp ->
                    val textWidth = tp.measureText(text)
                    val hOffset = (radius * Math.PI / numberOfItems / 2 - textWidth / 2).toInt()
                    val vOffset = radius / 2 / 3 - 3

                    canvas.drawTextOnPath(text, path, hOffset.toFloat(), vOffset.toFloat(), tp)
                }
            }
        }
    }

    /**
     * Function to rotate wheel to target
     *
     * @param target target number
     */
    fun rotateWheelToTarget(target: Int) {
        if (!wheelItems.isNullOrEmpty()) {
            wheelItems?.size?.let { numberOfItems ->
                val wheelItemCenter = 270 - getAngleOfIndexTarget(target) + 360 / numberOfItems / 2

                animate().setInterpolator(DecelerateInterpolator())
                    .setDuration(DEFAULT_ROTATION_TIME.toLong())
                    .rotation(360 * 15 + wheelItemCenter)
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator) {}
                        override fun onAnimationEnd(animation: Animator) {
                            clearAnimation()
                        }

                        override fun onAnimationCancel(animation: Animator) {}
                        override fun onAnimationRepeat(animation: Animator) {}
                    })
                    .start()
            }
        }
    }

    /**
     * Function to rotate to zero angle
     *
     * @param target target to reach
     */
    fun resetRotationLocationToZeroAngle(target: Int) {
        animate().setDuration(0)
            .rotation(0f).setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    rotateWheelToTarget(target)
                    clearAnimation()
                }

                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawWheelBackground(canvas)

        initComponents()

        if (!wheelItems.isNullOrEmpty()) {
            wheelItems?.let { items ->
                var tempAngle = 0f
                val sweepAngle = (360 / items.size).toFloat()
                for (i in items.indices) {
                    archPaint?.let {
                        it.color = colors[i]
                        canvas.drawArc(range, tempAngle, sweepAngle, true, it)
                        drawText(
                            canvas,
                            tempAngle,
                            sweepAngle,
                            items[i]
                        )
                    }
                    tempAngle += sweepAngle
                }
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = measuredWidth.coerceAtMost(measuredHeight)

        padding = if (paddingLeft == 0) DEFAULT_PADDING else paddingLeft
        radius = width - padding * 2
        center = width / 2
        setMeasuredDimension(width, width)
    }

    companion object {
        const val DEFAULT_ROTATION_TIME = 9000
        const val DEFAULT_PADDING = 5
    }
}