package com.example.dicodingjetpackpro.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun ImageView.loadImage(url: String?) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun Context.showMsg(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImage(url: String, placeholder: Int) {
    Glide.with(this)
        .load(url)
        .placeholder(placeholder)
        .into(this)
}

val localId = Locale("IN")
fun String.formatDate(oldPattern: String, newPattern: String): String? {
    val date = SimpleDateFormat(oldPattern, localId).parse(this)
    val formatter = SimpleDateFormat(newPattern, localId)
    return date?.let { formatter.format(it) }
}