package io.nekohasekai.sagernet.utils

import android.app.Activity
import android.view.ViewGroup
import jp.wasabeef.blurry.Blurry

/**
 * Efek blur sederhana untuk background Activity.
 * Gunakan di Activity atau dipanggil otomatis dari ThemedActivity.
 */
fun Activity.blurBackground() {
    val rootView = window.decorView.findViewById<ViewGroup>(android.R.id.content)
    if (rootView == null) return

    try {
        Blurry.with(this)
            .radius(20)     // intensitas blur
            .sampling(2)    // optimasi performa
            .animate(250)   // animasi halus
            .onto(rootView)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 * Menghapus efek blur dari background Activity.
 */
fun Activity.clearBlur() {
    val rootView = window.decorView.findViewById<ViewGroup>(android.R.id.content)
    if (rootView == null) return

    try {
        Blurry.delete(rootView)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
