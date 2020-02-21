package com.annchar.scalinganimationdroid.demo

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.annchar.scalinganimationdroid.R
import com.annchar.scalinganimationdroid.ScalingAnimationDroid
import com.annchar.scalinganimationdroid.ScalingAnimationType
import kotlinx.android.synthetic.main.activity_demo.*

class DemoActivity : AppCompatActivity() {

    private var durationActionDown: Int = DURATION_DEFAULT
    private var durationActionUp: Int = DURATION_DEFAULT
    private var scalingPadding: Float = SCALING_PADDING_DEFAULT
    private var count = 0

    companion object {
        private const val DURATION_DEFAULT = 400
        private const val SCALING_PADDING_DEFAULT = 0.8f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
        resetProgressBarValue()

        /**
         * Example to use library
         * Kotlin
         */
        /*ScalingAnimationDroid(card_view1).apply {
            setScalingAnimationType(ScalingAnimationType.SCALING_IN)
            setDurationActionDown(400)
            setDurationActionUp(400)
            setScalingPadding(0.8f)
        }*/

        // OR

        /*val animateView = ScalingAnimationDroid(card_view1)
        animateView.setScalingAnimationType(ScalingAnimationType.SCALING_IN)
        animateView.setDurationActionDown(400)
        animateView.setDurationActionUp(400)
        animateView.setScalingPadding(0.8f)*/

        sb_duration_down.apply {
            max = 1000
            progress = durationActionDown
            setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    tv_duration_down_value.text = resources.getString(R.string.content_ms, progress)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {}

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    durationActionDown = seekBar.progress
                }
            })
        }

        sb_duration_up.apply {
            max = 1000
            progress = durationActionUp
            setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    tv_duration_up_value.text = resources.getString(R.string.content_ms, progress)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {}

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    durationActionUp = seekBar.progress
                }
            })
        }

        sb_scaling_padding.apply {
            max = 100
            progress = (scalingPadding * 100 / 1.0f).toInt()
            setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    tv_scaling_padding_value.text =
                        resources.getString(R.string.content_f, progress * 1.0f / 100)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {}

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    scalingPadding = seekBar.progress * 1.0f / 100
                }
            })
        }
    }

    fun onButtonClicked(view: View) {
        if (view is AppCompatButton) {
            when (view.id) {
                R.id.button_reset -> {
                    resetProgressBarValue()
                    recreate()
                }
                R.id.button_submit -> {
                    count++
                    setScalingAnimationDroid()
                }
            }
        }

        if (count >= 1)
            button_submit.isEnabled = false
    }

    private fun resetProgressBarValue() {
        // Set default value
        durationActionDown = DURATION_DEFAULT
        durationActionUp = DURATION_DEFAULT
        scalingPadding = SCALING_PADDING_DEFAULT

        // Set default progress bar value
        sb_duration_down.progress = durationActionDown
        sb_duration_up.progress = durationActionUp
        sb_scaling_padding.progress = (scalingPadding * 100 / 1.0f).toInt()

        tv_duration_down_value.text = resources.getString(R.string.content_ms, durationActionDown)
        tv_duration_up_value.text = resources.getString(R.string.content_ms, durationActionUp)
        tv_scaling_padding_value.text = resources.getString(R.string.content_f, scalingPadding)
    }

    private fun setScalingAnimationDroid() {
        ScalingAnimationDroid(card_view1).apply {
            setScalingAnimationType(ScalingAnimationType.SCALING_IN)
            setDurationActionDown(this@DemoActivity.durationActionDown)
            setDurationActionUp(this@DemoActivity.durationActionUp)
            setScalingPadding(this@DemoActivity.scalingPadding)
        }

        ScalingAnimationDroid(card_view2).apply {
            setScalingAnimationType(ScalingAnimationType.SCALING_OUT)
            setDurationActionDown(this@DemoActivity.durationActionDown)
            setDurationActionUp(this@DemoActivity.durationActionUp)
            setScalingPadding(this@DemoActivity.scalingPadding)
        }
    }
}
