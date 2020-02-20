package com.annchar.scalinganimationdroid

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.util.Log
import android.view.MotionEvent
import android.view.View
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
    private var scalingType: ScalingAnimationType? = null
    private var durationActionDown: Int? = null
    private var durationActionUp: Int? = null
    private var scalingPadding: Float? = null

    init {
        this.view = WeakReference(view)
        if (this.view.get() != null) {
            if (!this.view.get()?.hasOnClickListeners()!!) {
                this.view.get()?.setOnClickListener { }
            }
        }

        init()
    }

    fun setScalingAnimationType(scalingAnimationType: ScalingAnimationType) {
        Log.e("Library set scalingType", scalingAnimationType.toString())
        this.scalingType = scalingAnimationType
    }

    fun setDurationActionDown(durationActionDown: Int) {
        this.durationActionDown = durationActionDown
    }

    fun setDurationActionUp(durationActionUp: Int) {
        this.durationActionUp = durationActionUp
    }

    fun setScalingPadding(scalingPadding: Float) {
        this.scalingPadding = scalingPadding
    }

    private fun init() {
        view?.get()?.setOnClickListener {
            resetOriginalXY()
            when (scalingType) {
                ScalingAnimationType.SCALING_IN -> {
                    scalingInAnimation()
                }
                ScalingAnimationType.SCALING_OUT -> {
                    scalingOutAnimation()
                }
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
                        scalingPadding ?: SCALING_PADDING_DEFAULT,
                        durationActionDown?.toLong() ?: DURATION_DEFAULT.toLong()
                    )
                }

                MotionEvent.ACTION_UP -> {
                    animate(
                        v,
                        ORIGINAL_XY,
                        durationActionUp?.toLong() ?: DURATION_DEFAULT.toLong()
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
                        durationActionDown?.toLong() ?: DURATION_DEFAULT.toLong()
                    )
                }

                MotionEvent.ACTION_UP -> {
                    animate(
                        v,
                        scalingPadding ?: SCALING_PADDING_DEFAULT,
                        durationActionUp?.toLong() ?: DURATION_DEFAULT.toLong()
                    )
                }
            }
            true
        }
    }

    private fun resetOriginalXY() {
        view?.get()?.clearAnimation()
        view?.get()?.scaleX = ORIGINAL_XY
        view?.get()?.scaleY = ORIGINAL_XY
    }
}