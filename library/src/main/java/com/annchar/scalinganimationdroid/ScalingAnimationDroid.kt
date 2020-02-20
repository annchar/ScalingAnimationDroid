package com.annchar.scalinganimationdroid

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import com.annchar.scalinganimationdroid.library.R
import java.lang.ref.WeakReference

class ScalingAnimationDroid(view: View) {

    companion object {
        private const val SCALE_X_NAME = "scaleX"
        private const val SCALE_Y_NAME = "scaleY"

        private const val ORIGINAL_XY = 1f
        private const val DURATION_DEFAULT = 400
        private const val SCALING_PADDING_DEFAULT = 0.8f
    }

    private val view: WeakReference<View>?

    var durationActionDown: Long = DURATION_DEFAULT.toLong()
    var durationActionUp: Long = DURATION_DEFAULT.toLong()
    var scalingType: ScalingAnimationType = ScalingAnimationType.SCALING_IN
    var scalingPadding: Float = SCALING_PADDING_DEFAULT

    init {
        this.view = WeakReference(view)
        if (this.view.get() != null) {
            if (!this.view.get()?.hasOnClickListeners()!!) {
                this.view.get()?.setOnClickListener { }
            }
        }

        init()
    }

    private fun init() {
        view?.get()?.setOnClickListener {
            when (scalingType) {
                ScalingAnimationType.SCALING_IN -> scalingInAnimation()
                ScalingAnimationType.SCALING_OUT -> scalingOutAnimation()
            }
        }
    }

    private fun animate(v: View, scalingPadding: Float, duration: Long) {
        val scaleDownX = ObjectAnimator.ofFloat(
            v,
            SCALE_X_NAME,
            scalingPadding
        )
        val scaleDownY = ObjectAnimator.ofFloat(
            v,
            SCALE_Y_NAME,
            scalingPadding
        )
        scaleDownX.duration = duration
        scaleDownY.duration = duration
        val scaleDown = AnimatorSet()
        scaleDown.play(scaleDownX).with(scaleDownY)
        scaleDown.start()
    }

    private fun scalingInAnimation() {
        view?.get()?.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    animate(
                        v,
                        scalingPadding,
                        durationActionDown
                    )
                }

                MotionEvent.ACTION_UP -> {
                    animate(
                        v,
                        ORIGINAL_XY,
                        durationActionUp
                    )
                }
            }
            true
        }
    }

    private fun scalingOutAnimation() {
        view?.get()?.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    animate(
                        v,
                        ORIGINAL_XY,
                        durationActionDown
                    )
                }

                MotionEvent.ACTION_UP -> {
                    animate(
                        v,
                        scalingPadding,
                        durationActionUp
                    )
                }
            }
            true
        }
    }
}