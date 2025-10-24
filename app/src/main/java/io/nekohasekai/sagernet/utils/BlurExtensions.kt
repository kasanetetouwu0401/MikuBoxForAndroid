package io.nekohasekai.sagernet.utils

import android.app.Activity
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import jp.wasabeef.blurry.Blurry

/**
 * Buat efek blur universal untuk Activity dan Dialog.
 */
fun Activity.blurBackground() {
    val rootView: View = window.decorView.findViewById(android.R.id.content)
    Blurry.with(this)
        .radius(20)   // ubah sesuai keinginan (10–25)
        .sampling(2)
        .animate(250)
        .onto(rootView)
}

fun Activity.clearBlur() {
    val rootView: View = window.decorView.findViewById(android.R.id.content)
    Blurry.delete(rootView)
}

/**
 * Tambahkan blur otomatis untuk semua dialog (baik Preference maupun Material).
 */
fun FragmentActivity.enableGlobalDialogBlur() {
    val fm = supportFragmentManager

    fm.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: androidx.fragment.app.Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            if (f is DialogFragment) {
                blurBackground()
                f.dialog?.setOnDismissListener {
                    clearBlur()
                }
            }
        }
    }, true)
}

/**
 * Tambahkan blur otomatis untuk MaterialAlertDialog atau Dialog biasa.
 */
fun Dialog.applyBlur(activity: Activity) {
    // Blur saat dialog muncul
    activity.blurBackground()

    setOnDismissListener {
        activity.clearBlur()
    }

    // Optional: Buat background dialog semi transparan agar blur terlihat
    window?.setBackgroundDrawable(ColorDrawable(0xB3000000.toInt()))
}
