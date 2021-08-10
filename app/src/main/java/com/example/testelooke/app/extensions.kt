package com.example.testelooke.app

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun ImageView.load(url: Int?) {
    url?.let {
        Glide.with(context).load(it).into(this)
    }
}