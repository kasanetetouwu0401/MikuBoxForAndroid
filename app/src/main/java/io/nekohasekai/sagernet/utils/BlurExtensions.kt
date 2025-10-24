package io.nekohasekai.sagernet.utils

import android.app.Activity
import android.view.View
import jp.wasabeef.blurry.Blurry

/**
 * Efek blur sederhana untuk Activity background.
 * Dipanggil otomatis oleh ThemedActivity saat dialog muncul.
 */
fun Activity.blurBackground() {
    val rootView: View = window.decorView.findViewById(android.R.id.content)
    try {
        Blurry.with(this)
            .radius(20)     // tingkat blur (10–25 bagus)
            .sampling(2)    // turunkan untuk performa
            .animate(250)   // animasi muncul 250ms
            .onto(rootView)
    } catch (_: Exception) {
        // abaikan error (misal: window belum siap)
    }
}

/**
 * Menghapus blur dari Activity background.
 */
fun Activity.clearBlur() {
    val rootView: View = window.decorView.findViewById(android.R.id.content)
    try {
        Blurry.delete(rootView)
    } catch (_: Exception) {
        // aman walau dipanggil sebelum blur dibuat
    }
}
