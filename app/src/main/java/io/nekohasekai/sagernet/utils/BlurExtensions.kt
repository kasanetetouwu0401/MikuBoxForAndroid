package io.nekohasekai.sagernet.utils

import android.app.Activity
import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import jp.wasabeef.blurry.Blurry

fun Activity.blurBackground() {
    val decorView = window.decorView.rootView
    decorView.isDrawingCacheEnabled = true
    val bitmap = Bitmap.createBitmap(decorView.drawingCache)
    decorView.isDrawingCacheEnabled = false

    val blurOverlay = ImageView(this)
    blurOverlay.tag = "BLUR_OVERLAY"
    Blurry.with(this)
        .radius(15)        // intensitas blur
        .sampling(2)       // tingkat kehalusan (semakin kecil = semakin detail)
        .from(bitmap)
        .into(blurOverlay)

    addContentView(
        blurOverlay,
        ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    )
}

fun Activity.clearBlur() {
    val root = window.decorView as ViewGroup
    val overlay = root.findViewWithTag<View>("BLUR_OVERLAY")
    if (overlay != null) {
        (overlay.parent as? ViewGroup)?.removeView(overlay)
    }
}
