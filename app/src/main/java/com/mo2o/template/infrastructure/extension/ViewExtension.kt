package com.mo2o.template.infrastructure.extension

import android.support.design.widget.TextInputEditText
import android.text.Editable
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mo2o.template.R
import com.mo2o.template.infrastructure.ui.common.GlideLoader


const val HOME = android.R.id.home
const val OVERVIEW = R.id.bndOverview
const val REPOSITORIES = R.id.bndRepositories
const val STARS = R.id.bndStars
const val FOLLOWING = R.id.bndFollowing

const val PROFILE = R.id.profile

infix fun ViewGroup.inflate(res: Int) = LayoutInflater.from(context).inflate(res, this, false)

fun Action(f: () -> Unit): Boolean {
    f()
    return true
}

fun ImageView.load(url: String) {
    GlideLoader(Glide.with(context)).load(url, this)
}

fun ImageView.loadCircle(url: String) {
    GlideLoader(Glide.with(context)).loadCircle(url, this)
}

fun MenuInflater.make(menuRes: Int, menu: Menu) = Action { inflate(menuRes, menu) }

val TextInputEditText.string: String
    get() = text.toString()

operator inline fun <B> Boolean.invoke(ifEmpty: () -> B, f: () -> B): B = when (this) {
    false -> ifEmpty()
    true -> f()
}

public inline fun <T, R> withNotNull(receiver: T?, block: T.() -> R): R? = receiver?.block()