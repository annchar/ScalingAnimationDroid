package com.annchar.scalinganimationdroid.library

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class ScalingAnimationDroid @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val SCALE_X_NAME = "scaleX"
        private const val SCALE_Y_NAME = "scaleY"

        private const val ORIGINAL_XY = 1f
        private const val DURATION_DEFAULT = 400
        private const val SCALING_PADDING_DEFAULT = 0.8f
        private const val SCALING_TYPE_DEFAULT = 0
    }

    private var durationActionDown: Long = DURATION_DEFAULT.toLong()
    private var durationActionUp: Long = DURATION_DEFAULT.toLong()
    private var scalingType: ScalingAnimationType = ScalingAnimationType.SCALING_IN
    private var scalingPadding: Float = SCALING_PADDING_DEFAULT

    init {
        val typedArray =
            context.obtainStyledAttributes(
                attrs,
                R.styleable.ScalingAnimationDroid,
                defStyleAttr,
                0
            )

        try {
            durationActionDown = typedArray.getInteger(
                R.styleable.ScalingAnimationDroid_durationActionDown,
                DURATION_DEFAULT
            ).toLong()

            durationActionUp = typedArray.getInteger(
                R.styleable.ScalingAnimationDroid_durationActionUp,
                DURATION_DEFAULT
            ).toLong()

            scalingType = ScalingAnimationType.values()[typedArray.getInt(
                R.styleable.ScalingAnimationDroid_scalingAnimationType,
                SCALING_TYPE_DEFAULT
            )]

            scalingPadding = typedArray.getFloat(
                R.styleable.ScalingAnimationDroid_scalingPadding,
                SCALING_PADDING_DEFAULT
            )
            if (scalingPadding in 0.0..1.0)
                scalingPadding
            else
                SCALING_PADDING_DEFAULT

        } finally {
            typedArray.recycle()
        }

        init()
    }

    private fun init() {
        super.setOnClickListener {
            when (scalingType) {
                ScalingAnimationType.SCALING_IN -> scalingInAnimation()
                ScalingAnimationType.SCALING_OUT -> scalingOutAnimation()
            }
        }
    }

    private fun animation(v: View, scalingPadding: Float, duration: Long) {
        val scaleDownX = ObjectAnimator.ofFloat(
            v,
            SCALE_X_NAME, scalingPadding
        )
        val scaleDownY = ObjectAnimator.ofFloat(
            v,
            SCALE_Y_NAME, scalingPadding
        )
        scaleDownX.duration = duration
        scaleDownY.duration = duration
        val scaleDown = AnimatorSet()
        scaleDown.play(scaleDownX).with(scaleDownY)
        scaleDown.start()
    }

    private fun scalingInAnimation() {
        super.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    animation(v, scalingPadding, durationActionDown)
                }

                MotionEvent.ACTION_UP -> {
                    animation(v, ORIGINAL_XY, durationActionUp)
                }
            }
            true
        }
    }

    private fun scalingOutAnimation() {
        super.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    animation(v, ORIGINAL_XY, durationActionDown)
                }

                MotionEvent.ACTION_UP -> {
                    animation(v, scalingPadding, durationActionUp)
                }
            }
            true
        }
    }

}