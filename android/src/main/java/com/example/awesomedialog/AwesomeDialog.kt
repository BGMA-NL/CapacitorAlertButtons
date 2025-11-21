
package com.example.awesomedialog

import android.app.Activity
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import nl.bgma.capacitor.alertbuttons.R

class AwesomeDialog {

    /***
     * Positions For Alert Dialog
     * */
    enum class POSITIONS {
        CENTER, BOTTOM
    }

    companion object {

        private lateinit var layoutInflater: LayoutInflater

        /***
         * core method For Alert Dialog
         * */
        fun build(
            context: Activity
        ): AlertDialog {
            layoutInflater = LayoutInflater.from(context)
            val alertDialog =
                AlertDialog.Builder(
                    context, R.style.full_screen_dialog
                )
                    .setView(R.layout.awesome_dilaog)
            val alert: AlertDialog = alertDialog.create()
            // Let's start with animation work. We just need to create a style and use it here as follows.
            //Pop In and Pop Out Animation yet pending
//            alert.window?.attributes?.windowAnimations = R.style.SlidingDialogAnimation
            alert.show()
            return alert
        }
    }
}

/***
 * Title Properties For Alert Dialog
 * */
fun AlertDialog.title(
    title: String,
    fontStyle: Typeface? = null,
    titleColor: Int = 0
): AlertDialog {
    val titleView = this.findViewById<TextView>(R.id.title)

    titleView?.let {
        it.text = title.trim()
        if (fontStyle != null) {
            it.typeface = fontStyle
        }

        if (titleColor != 0) {
            it.setTextColor(titleColor)
        }

        it.visibility = View.VISIBLE
    }
    return this
}

/***
 * Dialog Background properties For Alert Dialog
 * */
fun AlertDialog.background(
    dialogBackgroundColor: Int? = null
): AlertDialog {
    if (dialogBackgroundColor != null) {
        val mainLayout = this.findViewById<View>(R.id.mainLayout)
        mainLayout?.setBackgroundResource(dialogBackgroundColor)
    }
    return this
}

/***
 * Positions of Alert Dialog
 * */
fun AlertDialog.position(
    position: AwesomeDialog.POSITIONS = AwesomeDialog.POSITIONS.BOTTOM
): AlertDialog {
    val mainLayout = this.findViewById<View>(R.id.mainLayout)
    val layoutParams = mainLayout?.layoutParams as RelativeLayout.LayoutParams
    if (position == AwesomeDialog.POSITIONS.CENTER) {
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
    } else if (position == AwesomeDialog.POSITIONS.BOTTOM) {
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
    }
    mainLayout.layoutParams = layoutParams
    return this
}

/***
 * Sub Title or Body of Alert Dialog
 * */
fun AlertDialog.body(
    body: String,
    fontStyle: Typeface? = null,
    color: Int = 0
): AlertDialog {
    val subHeading = this.findViewById<TextView>(R.id.subHeading) ?: return this
    subHeading.text = body.trim()
    subHeading.show()
    if (fontStyle != null) {
        subHeading.typeface = fontStyle
    }
    if (color != 0) {
        subHeading.setTextColor(color)
    }
    return this
}

/***
 * Icon of  Alert Dialog
 * */
fun AlertDialog.icon(
    icon: Int,
    animateIcon: Boolean = false
): AlertDialog {
    val image = this.findViewById<ImageView>(R.id.image) ?: return this
    image.setImageResource(icon)
    image.show()
    // Pulse Animation for Icon
    if (animateIcon) {
        val pulseAnimation = AnimationUtils.loadAnimation(context, R.anim.pulse)
        image.startAnimation(pulseAnimation)
    }
    return this
}

/***
 * onPositive Button Properties For Alert Dialog
 *
 * No Need to call dismiss(). It is calling already
 * */
fun AlertDialog.onPositive(
    text: String,
    buttonBackgroundColor: Int? = null,
    textColor: Int? = null,
    action: (() -> Unit)? = null
): AlertDialog {
    val yesButton = this.findViewById<TextView>(R.id.yesButton) ?: return this
    yesButton.show()
    if (buttonBackgroundColor != null) {
        yesButton.setBackgroundResource(buttonBackgroundColor)
    }
    if (textColor != null) {
        yesButton.setTextColor(textColor)
    }
    yesButton.text = text.trim()
    yesButton.setOnClickListener {
        action?.invoke()
        dismiss()
    }
    return this
}

/***
 * onNegative Button Properties For Alert Dialog
 *
 * No Need to call dismiss(). It is calling already
 * */
fun AlertDialog.onNegative(
    text: String,
    buttonBackgroundColor: Int? = null,
    textColor: Int? = null,
    action: (() -> Unit)? = null
): AlertDialog {
    val noButton = this.findViewById<TextView>(R.id.noButton) ?: return this
    noButton.show()
    noButton.text = text.trim()
    if (textColor != null) {
        noButton.setTextColor(textColor)
    }
    if (buttonBackgroundColor != null) {
        noButton.setBackgroundResource(buttonBackgroundColor)
    }
    noButton.setOnClickListener {
        action?.invoke()
        dismiss()
    }
    return this
}

private fun View.show() {
    this.visibility = View.VISIBLE
}
