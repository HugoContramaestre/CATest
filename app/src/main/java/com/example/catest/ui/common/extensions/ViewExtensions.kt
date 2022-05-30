package com.example.catest.ui.common.extensions

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.catest.BuildConfig
import com.example.catest.R
import com.example.catest.ui.common.IMAGE_URL_SUFFIX
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


private const val DEFAULT_ANIMATION_DURATION = 1000L

fun View.show() {
    visibility = View.VISIBLE
}

fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.visible(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

fun View.showSnackbarSimple(message: Int, isError: Boolean = false) {
    showSnackbarSimple(context.getString(message), isError)
}

fun View.showSnackbarSimple(message: String, isError: Boolean = false) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackbar.setActionTextColor(ContextCompat.getColor(context, R.color.white))
    with(snackbar.view) {
        setPadding(
            paddingLeft,
            context.resources.getDimensionPixelSize(R.dimen.snackbar_padding_top),
            paddingRight,
            paddingBottom
        )
        setBackgroundColor(
            ContextCompat.getColor(
                context,
                if (isError) {
                    R.color.error
                } else {
                    R.color.teal_700
                }
            )
        )
        findViewById<TextView>(com.google.android.material.R.id.snackbar_text).apply {
            setTextColor(ContextCompat.getColor(context, R.color.white))
            maxLines = 5
        }
        setOnClickListener {
            snackbar.dismiss()
        }
    }
    snackbar.show()
}

fun ImageView.loadUrl(url: String?, placeholder: Int = -1) {
    val userAgent = "Mozilla/5.0 (Linux; Android 11) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.181 Mobile Safari/537.36"
    val fullUrl = "%s%s%s".format(
        BuildConfig.BASE_IMAGE_URL,
        url,
        IMAGE_URL_SUFFIX
    )
    val glideUrl = GlideUrl(
        if (url != null) fullUrl else "",
        LazyHeaders.Builder().addHeader("User-Agent", userAgent).build()
    )
    if (placeholder > 0)
        Glide.with(context).load(glideUrl).placeholder(placeholder).into(this)
    else
        Glide.with(context).load(glideUrl).into(this)
}

fun <T : ViewDataBinding> ViewGroup.bindingInflate(
    @LayoutRes layoutRes: Int,
    attachToRoot: Boolean = true
): T = DataBindingUtil.inflate(LayoutInflater.from(context), layoutRes, this, attachToRoot)

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

fun TextView.setDate(date: Long){
    text = DateFormat.format("dd/MM/yyyy", Date(date * 1000)).toString()
}

fun TextView.setCompleteDate(date: Long){
    text = DateFormat.format("dd/MM/yyyy HH:mm:ss", Date(date * 1000)).toString()
}

fun TextView.setTemps(min: Float, max: Float){
    text = context.resources.getString(
        R.string.common_min_max_temp,
        min,
        max
    )
}

