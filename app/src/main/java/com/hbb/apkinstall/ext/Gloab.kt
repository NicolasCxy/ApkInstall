package com.hbb.apkinstall.ext

import android.view.View
import com.hbb.apkinstall.BaseApplication

/**
 * 点击事件批量处理
 */
fun setOnClickListener(vararg v: View?, block: View.() -> Unit) {
    val listener = View.OnClickListener { it.block() }
    v.forEach { view -> view?.setOnClickListener(listener) }
}

/**
 * 点击事件批量处理 （防抖动）
 */
fun setSingleOnClickListener(vararg v: View?, block: View.() -> Unit) {
//    v.forEach { view -> view?.setOnSingleClickListener(delayTime = 500, listener = block) }
}

/**
 * 获取资源中的字符串
 */
fun getString(resId: Int): String = BaseApplication.context.resources.getString(resId)


/**
 * 获取资源中的dp值
 */
fun getDimension(resId: Int): Int = BaseApplication.context.resources.getDimensionPixelOffset(resId)