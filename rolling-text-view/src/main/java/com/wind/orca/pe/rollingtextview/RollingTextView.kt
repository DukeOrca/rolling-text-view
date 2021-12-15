package com.wind.orca.pe.rollingtextview

import android.animation.Animator
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.Gravity
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.ColorInt

class RollingTextView : FrameLayout {
    private lateinit var currentTextView: TextView
    private lateinit var nextTextView: TextView

    var currentItem: Int = 0
        set(value) {
            with(currentTextView) {
                if (text.isBlank()) {
                    text = "$value"
                } else {
                    when {
                        field > value -> rollUp(value)
                        field < value -> rollDown(value)
                    }
                }
            }

            field = value
        }

    private var duration = 200L

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs, defStyleAttr)
    }

    private fun init() {
        val layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT,
            Gravity.CENTER
        )

        currentTextView = TextView(context).apply {
            gravity = Gravity.CENTER
            text = null
        }

        nextTextView = TextView(context).apply {
            gravity = Gravity.CENTER
            text = null
        }

        addView(currentTextView, layoutParams)
        addView(nextTextView, layoutParams)
    }

    private fun init(attrs: AttributeSet, defStyleAttr: Int = 0) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RollingTextView)

        duration = typedArray.getInt(R.styleable.RollingTextView_duration, 200).toLong()

        val layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT,
            Gravity.CENTER
        )

        currentTextView = TextView(context, attrs, defStyleAttr).apply {
            gravity = Gravity.CENTER
            text = null
        }

        nextTextView = TextView(context, attrs, defStyleAttr).apply {
            gravity = Gravity.CENTER
            text = null
        }

        addView(currentTextView, layoutParams)
        addView(nextTextView, layoutParams)

        typedArray.recycle()
    }

    private fun rollDown(nextItem: Int) {
        currentTextView.animate()
            .translationY(height.toFloat())
            .setDuration(duration)
            .setInterpolator(DecelerateInterpolator())
            .start()

        with(nextTextView) {
            text = "$nextItem"
            translationY = -height.toFloat()
            animate()
                .translationY(0.0F)
                .setDuration(duration)
                .setInterpolator(DecelerateInterpolator())
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator?) {}
                    override fun onAnimationEnd(animation: Animator?) {
                        with(currentTextView) {
                            text = "$nextItem"
                            translationY = 0.0F
                        }
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                        translationY = -height.toFloat()

                        with(currentTextView) {
                            text = "$nextItem"
                            translationY = 0.0F
                        }
                    }

                    override fun onAnimationRepeat(animation: Animator?) {
                    }
                }).start()
        }
    }

    private fun rollUp(nextItem: Int) {
        currentTextView.animate()
            .translationY((-height).toFloat())
            .setDuration(duration)
            .setInterpolator(DecelerateInterpolator())
            .start()

        with(nextTextView) {
            text = "$nextItem"
            translationY = height.toFloat()

            animate()
                .translationY(0.0F)
                .setDuration(duration)
                .setInterpolator(DecelerateInterpolator())
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator?) {
                    }
                    override fun onAnimationEnd(animation: Animator?) {
                        with(currentTextView) {
                            text = "$nextItem"
                            translationY = 0.0F
                        }
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                        translationY = 0.0F

                        with(currentTextView) {
                            text = "$nextItem"
                            translationY = 0.0F
                        }
                    }

                    override fun onAnimationRepeat(animation: Animator?) {
                    }
                })
                .start()
        }
    }

    fun setTextColor(@ColorInt color: Int) {
        currentTextView.setTextColor(color)
        nextTextView.setTextColor(color)
    }

    fun setTextColor(colors: ColorStateList) {
        currentTextView.setTextColor(colors)
        nextTextView.setTextColor(colors)
    }
}