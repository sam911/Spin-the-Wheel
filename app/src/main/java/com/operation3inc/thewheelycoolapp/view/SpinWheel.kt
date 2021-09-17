package com.operation3inc.thewheelycoolapp.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import com.operation3inc.thewheelycoolapp.R

class SpinWheel : FrameLayout {
    private var wheelView: WheelView? = null

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initComponent()
    }

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initComponent()
    }

    private fun initComponent() {
        inflate(context, R.layout.layout_wheel, this)
        wheelView = findViewById(R.id.wv_main_wheel)
    }

    /**
     * Function to add items to wheel items
     *
     * @param wheelItems Wheel items
     */
    fun addWheelItems(wheelItems: List<String>?) {
        wheelView?.addWheelItems(wheelItems)
    }

    /**
     * Function to rotate wheel to degree
     *
     * @param number Number to rotate
     */
    fun rotateWheelTo(number: Int) {
        wheelView?.resetRotationLocationToZeroAngle(number)
    }
}