package io.nekohasekai.sagernet.utils

import android.app.Activity
import android.view.ViewGroup
import jp.wasabeef.blurry.Blurry

fun Activity.blurBackground() {
    val rootView = window.decorView as? ViewGroup ?: return
    try {
        Blurry.with(this)
            .radius(25)
            .sampling(2)
            .animate(200)
            .onto(rootView)
    } catch (_: Exception) { }
}

fun Activity.clearBlur() {
    val rootView = window.decorView as? ViewGroup ?: return
    try {
        Blurry.delete(rootView)
    } catch (_: Exception) { }
}
